import java.util.Arrays;

public class Doomed_dice_part_2 {

    private int face1 = 0; // number of faces on dice 1.
    private int face2 = 0; // number of faces on dice 2.
    private int dieA[]; // sopts on dice 1.
    private int dieB[]; // sopts on dice 2.

    /*
     * Constructor.
     */
    public Doomed_dice_part_2(int dieA[], int dieB[]) {
        this.dieA = dieA;
        face1 = dieA.length;
        this.dieB = dieB;
        face2 = dieB.length;
    }

    /*
     * Calculate the values of new dices.
     * 
     * @return 2D array, containing new diceA and new diceB.
     */
    protected int[][] undoomDice() {
        int[] originalOccurrences = calculateOccurrences(dieA, dieB);

        for (int i = 0; i < 6; i++) {
            for (int newFaceValue = 1; newFaceValue <= 4; newFaceValue++) {
                int[] newDieA = Arrays.copyOf(dieA, face1);
                newDieA[i] = newFaceValue;

                if (isValidTransformation(newDieA)) {
                    int[] newOccurrences = calculateOccurrences(newDieA, dieB);

                    if (Arrays.equals(newOccurrences, originalOccurrences)) {
                        int[] newDieB = compensateDieB(newDieA, originalOccurrences);
                        return new int[][] { newDieA, newDieB };
                    }
                }
            }
        }

        return null;
    }

    /*
     * Calculate the toatl combination of outcomes rolling two dice.
     * 
     * @param dieA The diceA containing the values on the faces of dice A.
     * 
     * @param dieB The diceB containing the values on the faces of dice B.
     * 
     * @return Array containing the occurances of counts of sum in the array.
     */
    protected int[] calculateOccurrences(int[] dieA, int[] dieB) {
        int[] occurrences = new int[11]; // Counts for sums 2 to 12
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                occurrences[dieA[i] + dieB[j] - 2]++;
            }
        }
        return occurrences;
    }

    /*
     * Check if the newly formed dice A is valid.
     * 
     * @param dieA The diceA containing the values on the faces of new dice A.
     * 
     * @return True if the transformation is valid, false otherwise.
     */
    protected boolean isValidTransformation(int[] newDieA) {
        for (int face : newDieA) {
            if (face > 4) {
                return false;
            }
        }
        return true;
    }

    /*
     * Create new dice B for the newly obtained dice A.
     * 
     * @param dieA The diceA containing the values on the faces of new dice A.
     * 
     * @param originalOccurrences Array containing the occurrances of sum.
     * 
     * @return Array containing the values for new dice B.
     */
    protected int[] compensateDieB(int[] newDieA, int[] originalOccurrences) {
        int[] newDieB = new int[6];

        // Iterate through possible values for each face of Die B
        for (int i = 0; i < 6; i++) {
            for (int newFaceValue = 1; newFaceValue <= 12; newFaceValue++) { // Allow values up to 12
                // Calculate new occurrences with the potential Die B face
                int[] tentativeOccurrences = calculateOccurrences(newDieA, updatedDieB(newDieB, i, newFaceValue));

                // If occurrences match, set the face value and move to the next face
                if (Arrays.equals(tentativeOccurrences, originalOccurrences)) {
                    newDieB[i] = newFaceValue;
                    break; // Move to the next face
                }
            }
        }

        return newDieB;
    }

    /*
     * Update the new value at a specified index.
     * 
     * @param dieB The diceB containing the values on the faces of new dice B.
     * 
     * @param index Index at which the value has to update.
     * 
     * @param newValue New value to be updated at the given index.
     * 
     * @return Array containing the values for new dice B.
     */
    protected int[] updatedDieB(int[] dieB, int index, int newValue) {
        int[] updatedDieB = Arrays.copyOf(dieB, face2);
        updatedDieB[index] = newValue;
        return updatedDieB;
    }

}
