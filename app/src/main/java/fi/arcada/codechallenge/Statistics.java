package fi.arcada.codechallenge;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    public static double calcMean(ArrayList<Double> array) {
        double sum = 0;
        for (int i = 0 ; i < array.size() ; i++) {
            sum += array.get(i);
        }
        return sum /= array.size();
    }

    public static double calcMedian(ArrayList<Double> array) {
        // Vi skapar en kopia av array
        ArrayList<Double> sorted = new ArrayList<>(array);
        // Sedan sorterar vi kopian. Annars sorteras ursprungliga datamängden!
        Collections.sort(sorted);
        // Detta funkar, ännu bättre skulle vara att kolla skilt för jämna datamängder
        // Med medelvärde av de två mittersta
        int midIndex = sorted.size() / 2;
        // Medianen är dett mittersta värdet i vår sorterade datamängd!
        return sorted.get(midIndex);
    }

    public static double calcStdev(ArrayList<Double> array) {
        ArrayList<Double> stdev = new ArrayList<>(array);
        double mean = Statistics.calcMean(array);
        double sumDif = 0;
        for (int i = 0 ; i < array.size() ; i++) {
            sumDif += Math.pow((array.get(i) - mean), 2);
        }
        return Math.sqrt(sumDif / array.size());
    }

    // public static double calcMode(ArrayList<Double> array)
}
