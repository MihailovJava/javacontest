package com.atc.javacontest;

/**
 * Класс контейнер информации CSV файла
 */
public class CSVData {
    private int[] indices;
    private double[] x;
    private double[] y;
    private int currentInd = 0;
    private int count;

    public CSVData(int count) {
        this.count = count;
        indices = new int[count];
        x = new double[count];
        y = new double[count];
    }

    public void addIXY(int i, double x,double y){
        if(currentInd < count) {
            this.indices[currentInd] = i;
            this.x[currentInd] = x;
            this.y[currentInd] = y;
            currentInd++;
        }
    }

    public int[] getIndices() {
        return indices;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }
}
