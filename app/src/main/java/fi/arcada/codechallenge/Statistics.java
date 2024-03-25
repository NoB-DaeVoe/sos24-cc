package fi.arcada.codechallenge;

public class Statistics {

    public static double calcMean(double[] array) {

        double sum = 0;
        for ( int i = 0 ; i < array.length ; i++ ) {
            sum += array[i];
        }

        sum /= array.length;
        return sum;
    }
}
