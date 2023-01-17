/*
* This program generates every possible 3x3 Magic Square.
*
* @author  Rodas Nega
* @version 1.0
* @since   2023-01-17
*/

import java.util.ArrayList;

/**
 * This is a Magic Square Finder.
 */
final class MagicSquare {
    /**
     * The lower right index.
     */
    public static final int EIGHT = 8;

    /**
     * The middle right index.
     */
    public static final int FIVE = 5;

    /**
     * The center index.
     */
    public static final int FOUR = 4;

    /**
     * The expected sum of a row/column/diagonal.
     */
    public static final int MAGICNUM = 15;

    /**
     * The maximum index of the array.
     */
    public static final int NINE = 9;

    /**
     * The lower center index.
     */
    public static final int SEVEN = 7;

    /**
     * The lower left index.
     */
    public static final int SIX = 6;

    /**
     * The middle left index.
     */
    public static final int THREE = 3;

    /**
     * Prevent instantiation.
     * Throw an exception IllegalStateException.
     * if this is ever called
     *
     * @throws IllegalStateException
     *
     */
    private MagicSquare() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Function that checks for duplicates in an array.
     *
     * @param sqrArray - array to be checked.
     * @return boolean
     */
    public static boolean hasDuplicates(final int[] sqrArray) {
        final ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int count = 0; count < sqrArray.length; count++) {
            for (int count2 = 1; count2 < sqrArray.length; count2++) {
                if (sqrArray[count] == sqrArray[count2] && count != count2) {
                    arr.add(sqrArray[count]);
                }
            }
        }
        return arr.size() != 0;
    }

    /**
     * Function that generates every possible magic square.
     *
     * @param possibleNum - possible numbers to fill sqrArray with.
     * @param sqrArray - array to be filled and checked for "magic".
     * @param index - current index of sqrArray.
     */
    public static void genSquare(final int[] possibleNum, final int[] sqrArray,
                                 final int index) {
        if (index == NINE && isMagic(sqrArray)) {
            printMagicSquare(sqrArray);
        } else {
            if (index != NINE) {
                for (int count = 0; count < NINE; count++) {
                    sqrArray[index] = possibleNum[count];
                    genSquare(possibleNum, sqrArray, index + 1);
                }
            }
        }
    }

    /**
     * Function that checks if an array is "magic".
     *
     * @param preSquare - array to be checked.
     * @return boolean
     */
    public static boolean isMagic(final int[] preSquare) {
        final boolean returnValue;
        if (hasDuplicates(preSquare)) {
            returnValue = false;
        } else {
            // declare rows
            final int row1 = preSquare[0] + preSquare[1] + preSquare[2];
            final int row2 = preSquare[THREE] + preSquare[FOUR]
                + preSquare[FIVE];
            final int row3 = preSquare[SIX] + preSquare[SEVEN]
                + preSquare[EIGHT];
            // declare columns
            final int col1 = preSquare[0] + preSquare[THREE] + preSquare[SIX];
            final int col2 = preSquare[1] + preSquare[FOUR] + preSquare[SEVEN];
            final int col3 = preSquare[2] + preSquare[FIVE] + preSquare[EIGHT];
            // declare diagonals
            final int diag1 = preSquare[0] + preSquare[FOUR]
                + preSquare[EIGHT];
            final int diag2 = preSquare[2] + preSquare[FOUR] + preSquare[SIX];

            returnValue = row1 == row2 && row2 == row3 && row3 == col1
                && col1 == col2 && col2 == col3 && col3 == diag1
                && diag1 == diag2 && diag2 == MAGICNUM;
        }
        return returnValue;
    }

    /**
     * Function that prints a magic square with an appropriate format.
     *
     * @param outputSquare - array to be printed.
     */
    public static void printMagicSquare(final int[] outputSquare) {
        // prevent java linter error
        final String space = " ";

        for (int count = 0; count < outputSquare.length; count++) {
            if (count == THREE || count == SIX) {
                System.out.println();
                System.out.print(outputSquare[count] + space);
            } else {
                System.out.print(outputSquare[count] + space);
            }
        }
        System.out.println("\n");
    }

    /**
     * The starting main() function.
     *
     * @param args No args will be used
     */
    public static void main(final String[] args) {
        final int[] magicSquare = {
            1, 2, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};
        final int[] extraArray = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println("Generating all 3x3 Magic Squares...\n");
        genSquare(magicSquare, extraArray, 0);

        System.out.println("Done.");
    }
}

