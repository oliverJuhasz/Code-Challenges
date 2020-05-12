package SeatingStudents;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeatingStudentsExercise {

    public static int SeatingStudents(int[] input) {
        int possibleCombinations = 0;
        int numberOfSeating = input[0];
        Set<Integer> occupiedDesks = new HashSet<>();
        Arrays.stream(input)
                .skip(1L)
                .map(value -> value - 1)
                .forEach(occupiedDesks::add);
        boolean evenDeskNumber = true;
        for (int i = 1; i < numberOfSeating; i += evenDeskNumber ? 3 : 1) {
            boolean isExaminedDeskOccupied = occupiedDesks.contains(i);
            if (!isExaminedDeskOccupied) {
                possibleCombinations += getNumberOfSeatingCombinationForSeat(evenDeskNumber, i, occupiedDesks, numberOfSeating);
            }
            evenDeskNumber = !evenDeskNumber;
        }
        return possibleCombinations;
    }

    private static int getNumberOfSeatingCombinationForSeat(boolean evenDeskNumber, int i, Set<Integer> occupiedDesks, int numberOfSeating) {
        int possibleCombinations = 0;
        if (evenDeskNumber) {
            if (i - 2 >= 0) {
                possibleCombinations += occupiedDesks.contains(i - 2) ? 0 : 1;
            }
            if (i + 1 <= numberOfSeating) {
                possibleCombinations += occupiedDesks.contains(i - 1) ? 0 : 1;
            }
            if (i + 2 <= numberOfSeating) {
                possibleCombinations += occupiedDesks.contains(i + 2) ? 0 : 1;
            }
        } else {
            possibleCombinations += occupiedDesks.contains(i - 2) ? 0 : 1;
            possibleCombinations += occupiedDesks.contains(i + 1) ? 0 : 1;
            if (i + 2 < numberOfSeating) {
                possibleCombinations += occupiedDesks.contains(i + 2) ? 0 : 1;
            }
        }
        return possibleCombinations;
    }


}
