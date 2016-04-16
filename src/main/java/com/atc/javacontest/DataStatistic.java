package com.atc.javacontest;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * Кдасс отвечающий за статистические характеристики массива чисел - выборки
 */
public class DataStatistic {

    // массив чисел
    private double[] x;

    // математическое ожидание х
    private double mx = 0;

    // дисперсчия х
    private double dx = 0;

    // размер х
    private int n;

    public DataStatistic(double[] x) {
        this.x = x;
        n = x.length;
        for (int i = 0; i < n; i++) {
            mx += x[i];
        }
        mx /= n;
        for (int i = 0; i < n; i++) {
            dx += (x[i] - mx) * (x[i] - mx);
        }
        dx /= n;
    }

    public DataStatistic(double[] x, double mx, double dx, int n) {
        this.x = x;
        this.mx = mx;
        this.dx = dx;
        this.n = n;
    }

    /**
     * Метод возвращает массив чисел со сдвигом влево
     * @param lag  величина сдвига. Не может превышать величину размера массива
     * @return массив со сдвигом
     */
    public DataStatistic getWithLag(int lag){
        if(lag < n){
            return new DataStatistic(
                    ArrayUtils.addAll(Arrays.copyOfRange(x,lag,n),Arrays.copyOfRange(x,0,lag)),
                    mx,
                    dx,
                    n);

        } else throw  new IllegalArgumentException("Lag " + lag + " out of range N = " + n);
    }

    /**
     * Метод отыскания ковариации между исходным массивом х и массивом y
     * @param y массив, с которым ищем ковариацию
     * @return коэффициент ковариации
     */
    public double cov(DataStatistic y) {
        if(y.n == n) {
            double cov = 0;
            for (int i = 0; i < y.getN(); i++) {
                cov += (x[i] - mx) * (y.getX(i) - y.getMx());
            }
            return cov / n;
        } else throw new IllegalArgumentException("Arrays must has same size");
    }

    public double getX(int ind) {
        if (ind < n)
            return x[ind];
        else
            throw new IllegalArgumentException("Index " + ind + " out of range N = " + n);
    }

    public double getMx() {
        return mx;
    }

    public double getDx() {
        return dx;
    }

    public int getN() {
        return n;
    }

    public double[] getX() {
        return x;
    }
}
