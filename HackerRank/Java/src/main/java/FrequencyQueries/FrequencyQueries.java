package FrequencyQueries;

import java.util.*;

public class FrequencyQueries {

    //https://www.hackerrank.com/challenges/frequency-queries/

    public static List<Integer> freqQuery(int[][] queries) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> occurrences = new HashMap<>();
        List<Integer> results = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int command = query[0];
            int number = query[1];
            if (command == 1) {
                if (counter.get(number) == null) {
                    counter.put(number, 1);
                    occurrences.compute(1, (key, value) -> value != null ? value + 1 : 1);
                } else {
                    int frequency = counter.get(number);
                    counter.put(number, frequency + 1);
                    occurrences.compute(frequency, (key, value) -> value - 1);
                    occurrences.compute(frequency + 1, (key, value) -> value != null ? value + 1 : 1);
                }
            } else if (command == 2) {
                Integer occurrenceToRemove = counter.get(number);
                if (occurrenceToRemove != null && occurrenceToRemove != 0) {
                    counter.compute(number, (key, value) -> value - 1);
                    occurrences.compute(occurrenceToRemove, (key, value) -> value > 0 ? value - 1 : 0);
                    occurrences.compute(occurrenceToRemove - 1, (key, value) -> value != null ? value + 1 : 1);
                }
            } else {
                if (occurrences.get(number) != null && occurrences.get(number) > 0) {
                    results.add(1);
                } else {
                    results.add(0);
                }
            }
        }
        return results;
    }

}
