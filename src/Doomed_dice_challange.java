import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Doomed_dice_challange {

    public static void main(String[] args) {

        int[] dieA = { 1, 2, 3, 4, 5, 6 }; // Original dieA.
        int[] dieB = { 1, 2, 3, 4, 5, 6 }; // Original dieB.

        // Part-1
        Doomed_dice_part_1 part1 = new Doomed_dice_part_1(dieA, dieB);

        System.out.println("\nThe total number of combinations are: " + part1.totalCombinations() + "\n");

        part1.generateCombinationDistribution();
        part1.printCombinationDistribution();

        HashMap<Integer, Double> probabilitySum = part1.calculateProbability();

        System.out.println("\nThe probability of all the sums are: \n");
        // Print the probability sum.
        for (Map.Entry<Integer, Double> e : probabilitySum.entrySet()) {
            System.out.println("\tP(sum=" + e.getKey() + ") = " + e.getValue());
        }
        System.out.println(); // Print an empty line.

        // Part-2
        Doomed_dice_part_2 part2 = new Doomed_dice_part_2(dieA, dieB);

        int newDice[][] = part2.undoomDice();

        if (newDice != null) {
            System.out.println("New Die A: " + Arrays.toString(newDice[0]));
            System.out.println("New Die B: " + Arrays.toString(newDice[1]));
        } else {
            System.out.println("No valid transformation of dice found.");
        }

        System.out.println(); // Print an empty line.

    }
}