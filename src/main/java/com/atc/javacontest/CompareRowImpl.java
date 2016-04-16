package com.atc.javacontest;


import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompareRowImpl implements ICompareRow {

    // Минимальное абсолютное значение корреляции
    private static final double LIMIT = 0.3;

    // Содержимое файла
    CSVData csvData;

    public CSVData parseCSV(URI uri) {
        CSVData result = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(uri), StandardCharsets.UTF_8);
            result = new CSVData(lines.size());
            for (String line : lines) {
                String[] values = line.replaceAll(",", ".").split(";");
                result.addIXY(
                        Integer.valueOf(values[0]),
                        Double.valueOf(values[1]),
                        Double.valueOf(values[2])
                );
            }
        } catch (Exception e) {

        }
        return result;
    }

    private CorrelationResultIndex calcMaxAbsCorrelation(URL resource){
        CorrelationResultIndex index = null;
        try {
            csvData = parseCSV(resource.toURI());
            index = Statistic.findMaxAbsCorrelation(
                    new DataStatistic(csvData.getX()),
                    new DataStatistic(csvData.getY())
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(index);
        return  index;
    }

    private List<CorrelationResultIndex> calcAllMaxAbsCorrelations(URL resource,double limit){
        List<CorrelationResultIndex> resultIndices = null;
        try {
            csvData = parseCSV(resource.toURI());
            resultIndices =  Statistic.findAllMaxAbsCorrelations(
                    new DataStatistic(csvData.getX()),
                    new DataStatistic(csvData.getY()),
                    limit
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (CorrelationResultIndex index : resultIndices) {
            System.out.println(index);
        }
        return  resultIndices;
    }

    public CorrelationResultIndex executeTest0(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest1(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest2(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest3(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest4(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest5(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest6(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest7(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest8(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public CorrelationResultIndex executeTest9(URL resource) {
        return calcMaxAbsCorrelation(resource);
    }

    public List<CorrelationResultIndex> executeTest10(URL resource) {
        List<CorrelationResultIndex> resultIndices = calcAllMaxAbsCorrelations(resource, LIMIT);
        return resultIndices;
    }

    public List<CorrelationResultIndex> executeTest11(URL resource) {
        List<CorrelationResultIndex> resultIndices = calcAllMaxAbsCorrelations(resource, LIMIT);
        String spearmansDescription = "Так как выборка теста 11 представляет собой совокупность натуральных чисел," +
                " то возможно применить ранговую оценку Спирмена http://goo.gl/HKKlfd";
        String spearmansIndex =  String.valueOf(Statistic.calcSpearmansCorrelation(csvData.getX(),csvData.getY()));
        System.out.println(spearmansDescription + "\n" + "Spearmans index " + spearmansIndex );
        for (CorrelationResultIndex index : resultIndices){
            index.anotherIndexDesc = spearmansDescription;
            index.anotherIndex = spearmansIndex;
        }
        return resultIndices;
    }


}
