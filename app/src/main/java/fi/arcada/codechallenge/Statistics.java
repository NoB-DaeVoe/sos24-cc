package fi.arcada.codechallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public static double calcMode(ArrayList<Double> array) {
        ArrayList<Double> mode = new ArrayList<>(array);
        // Create a map to count occurrences of each value
        Map<Double, Double> frequencyMap = new HashMap<>();

        // Iterate over the values and count occurrences
        for (double value : mode) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, Double.valueOf(0)) + 1);
        }

        // Find the most common value
        double mostCommonValue = mode.get(0); // Default to the first value
        double maxFrequency = 0;

        for (Map.Entry<Double, Double> entry : frequencyMap.entrySet()) {
            double value = entry.getKey();
            double frequency = entry.getValue();
            if (frequency > maxFrequency) {
                mostCommonValue = value;
                maxFrequency = frequency;
            }
        }

        return mostCommonValue;
    }
}
