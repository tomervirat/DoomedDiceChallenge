import java.util.HashMap;

public class Doomed_dice_part_1 {

    private int face1 = 0; // number of faces on dice 1.
    private int face2 = 0; // number of faces on dice 2.
    private int dieA[]; // sopts on dice 1.
    private int dieB[]; // sopts on dice 2.
    protected int[][] combinationsDistribution; // 2D array to store the combination of outcomes.

    /*
     * Constructor.
     */
    public Doomed_dice_part_1(int dieA[], int dieB[]) {
        this.dieA = dieA;
        face1 = dieA.length;
        this.dieB = dieB;
        face2 = dieB.length;
    }

    /*
     * Calculate the toatl combination of outcomes rolling two dice.
     * 
     * @return Number of combinations generated.
     */
    protected int totalCombinations() {
        return face1 * face2;
    }

    /*
     * Generate all the combinations can be formed rolling two dice.
     */
    protected void generateCombinationDistribution() {
        combinationsDistribution = new int[face1][face2];

        for (int i = 0; i < face1; i++) {
            for (int j = 0; j < face2; j++) {
                combinationsDistribution[i][j] = dieA[i] + dieB[j];
            }
        }
    }

    /*
     * Print all the generated combinations.
     */
    protected void printCombinationDistribution() {
        System.out.println("Combinations Distribution:");
        for (int i = 0; i < face1; i++) {
            for (int j = 0; j < face2; j++) {
                System.out.print(combinationsDistribution[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /*
     * Calculate the probability of sum of outcomes obtained by the combinations.
     * 
     * @return HasMap containing the sum as key and probability as values.
     */
    protected HashMap<Integer, Double> calculateProbability() {
        HashMap<Integer, Double> probabilitySum = new HashMap<>();
        for (int sumValue = 2; sumValue <= 12; sumValue++) {
            int countOccurrences = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (combinationsDistribution[i][j] == sumValue) {
                        countOccurrences++;
                    }
                }
            }
            probabilitySum.put(sumValue, countOccurrences / (double) (totalCombinations()));
        }
        return probabilitySum;
    }
}
