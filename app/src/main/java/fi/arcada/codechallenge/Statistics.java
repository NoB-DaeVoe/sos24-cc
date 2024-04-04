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

    public static  double calcLQ(ArrayList<Double> array) {
        ArrayList<Double> sortedArray = new ArrayList<>(array);
        Collections.sort(sortedArray);

        int n = sortedArray.size();
        double lqIndex = 0.25 * (n + 1); // Index of the lower quartile

        if (n % 4 == 0) {
            // If the number of elements is divisible by 4
            return sortedArray.get((int) lqIndex - 1); // Exact value
        } else {
            // If the number of elements is not divisible by 4
            int k = (int) lqIndex;
            double fraction = lqIndex - k;
            double lowerValue = sortedArray.get(k - 1);
            double upperValue = sortedArray.get(k);
            return lowerValue + (upperValue - lowerValue) * fraction; // Interpolate
        }
    }

    public static  double calcUQ(ArrayList<Double> array) {
        ArrayList<Double> sortedArray = new ArrayList<>(array);
        Collections.sort(sortedArray);

        int n = sortedArray.size();
        double uqIndex = 0.75 * (n + 1); // Index of the upper quartile

        if (n % 4 == 0) {
            // If the number of elements is divisible by 4
            return sortedArray.get((int) uqIndex - 1); // Exact value
        } else {
            // If the number of elements is not divisible by 4
            int k = (int) uqIndex;
            double fraction = uqIndex - k;
            double lowerValue = sortedArray.get(k - 1);
            double upperValue = sortedArray.get(k);
            return lowerValue + (upperValue - lowerValue) * fraction; // Interpolate
        }
    }

    public static  double calcIQR(ArrayList<Double> array) {
        double lq = calcLQ(array);
        double uq = calcUQ(array);
        return uq - lq;
    }
}
