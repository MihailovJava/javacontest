package com.atc.javacontest;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечающий за вычисление корреляционных зависимостей
 */
public class Statistic {

    // Ошибка округления
    public static final float ZERO = 1e-8f;

    /**
     * Метод вычисления максимальной по модулю корреляции двух выборок
     *
     * @param x класс выборки один
     * @param y кдасс выборки два
     * @return заполненый класс корреляционной информации
     */
    public static CorrelationResultIndex findMaxAbsCorrelation(DataStatistic x, DataStatistic y) {
        // инициализирцем сдвиг и максимальную корреляцию
        int maxInd = -1;
        double maxCor = Double.MIN_VALUE;

        // поиск коэффициента корреляции и соответствующий ему индекс сдвига
        for (int lag = 0; lag < y.getN(); lag++) {
            double cor = x.cov(y.getWithLag(lag)) / Math.sqrt(x.getDx() * y.getDx());
            if (Math.abs(cor) - Math.abs(maxCor) > ZERO) {
                maxInd = lag;
                maxCor = cor;
            }
        }

        CorrelationResultIndex index = new CorrelationResultIndex();
        index.correlation = maxCor;
        index.correlationLagIndex = maxInd;

        // перед расчетом коэффициента масштаба сдвинем выборку 2 до совпадения согласно коэффициенту сдвига maxInd
        index.correlationMultipleIndex = maxInd == -1 ? Double.MIN_VALUE : calcMeanDiv(x, y.getWithLag(maxInd));

        return index;
    }

    /**
     * Метод расчета всех возможных корреляций между выборками, значений корреляций которых больше limit
     *
     * @param x     выборка один
     * @param y     выборка два
     * @param limit минимальное абсолютное значение корреляции
     * @return
     */
    public static List<CorrelationResultIndex> findAllMaxAbsCorrelations(DataStatistic x, DataStatistic y, double limit) {

        ArrayList<CorrelationResultIndex> resultIndices = new ArrayList<CorrelationResultIndex>();
        int startIndex = -1;
        List<CorrelationResultIndex> foundIndices = new ArrayList<CorrelationResultIndex>();
        boolean isFind = false;

        for (int lag = 0; lag < y.getN(); lag++) {

            // поиск коэффициента корреляции и соответствующий ему индекс сдвига
            double cor = x.cov(y.getWithLag(lag)) / Math.sqrt(x.getDx() * y.getDx());

            if (Math.abs(cor) - limit > ZERO) {
                if (!isFind) {
                    startIndex = lag;
                }
                CorrelationResultIndex index = new CorrelationResultIndex();
                index.correlation = cor;
                index.correlationLagIndex = lag;

                // перед расчетом коэффициента масштаба сдвинем выборку 2 до совпадения согласно коэффициенту сдвига lag
                index.correlationMultipleIndex = calcMeanDiv(x, y.getWithLag(lag));

                resultIndices.add(index);
                foundIndices.add(index);
                isFind = true;
            } else {
                if (isFind) {
                    for (CorrelationResultIndex index :  foundIndices) {
                        index.startIndex = String.valueOf(startIndex);
                        index.endIndex = String.valueOf(lag);
                    }
                    foundIndices.clear();
                }
                isFind = false;
            }

        }
        if(isFind){
            for (CorrelationResultIndex index :  foundIndices) {
                index.startIndex = String.valueOf(startIndex);
                index.endIndex = String.valueOf(y.getN());
            }
        }
        return resultIndices;
    }

    /**
     * Метод расчета среднего коэффициента масштаба двух выборок
     *
     * @param x выборка один
     * @param y выборка два
     * @return
     */
    private static double calcMeanDiv(DataStatistic x, DataStatistic y) {
        double div = 0;
        double count = 0;
        for (int i = 0; i < y.getN(); i++) {
            if (Math.abs(y.getX(i)) > ZERO) {
                div += x.getX(i) / y.getX(i);
                count++;
            }
        }
        return div / count;
    }

    /**
     * Метод вычисления ранговой корреляции Спирмена http://goo.gl/HKKlfd
     * @param x выборка один
     * @param y выборка два
     * @return
     */
    public static double calcSpearmansCorrelation(double[] x, double[] y) {
       return new SpearmansCorrelation().correlation(x, y);
    }
}
