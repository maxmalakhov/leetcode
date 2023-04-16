package max.leetcode.array;

import java.util.Arrays;
import java.util.List;

public class MaximumPilesCoins {

    public static void main(String[] args) {

        MaximumPilesCoins java = new MaximumPilesCoins();

        System.out.println("# piles = [[1,100,3],[7,8,9]], k = 2 -- '" + java.maxValueOfCoins(List.of(List.of(1,100,3), List.of(7,8,9)), 2) + "' == '101'");
        System.out.println("# piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7 -- '" + java.maxValueOfCoins(List.of(List.of(100), List.of(100), List.of(100), List.of(100), List.of(100), List.of(100), List.of(1,1,1,1,1,1,700)), 7) + "' == '706'");
        System.out.println("# piles = [[48,14,23,38,33,79,3,52,73,58,49,23,74,44,69,76,83,41,46,32,28]], k = 10 -- '" + java.maxValueOfCoins(List.of(List.of(48,14,23,38,33,79,3,52,73,58,49,23,74,44,69,76,83,41,46,32,28)), 10) + "' == '421'");
        System.out.println("# piles = [[37,88],[51,64,65,20,95,30,26],[9,62,20],[44]], k = 9 -- '" + java.maxValueOfCoins(List.of(List.of(37,88), List.of(51,64,65,20,95,30,26), List.of(9,62,20), List.of(44)), 9) + "' == '494'");
    }

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int length = piles.size();
        int maxDepth = 0;
        for (List<Integer> pile: piles) {
            maxDepth = Math.max(maxDepth, pile.size());
        }
        int depth = Math.min(k, maxDepth); // no need dive deeply
        // collect
        int[][] original = new int[length][depth];
        int[][] arrays = new int[depth][length];
        int[][] prefixes = new int[depth][length];
        for (int col = 0; col < length; col++) {
            List<Integer> pile = piles.get(col);
            original[col][0] = pile.get(0); // init first row
            arrays[0][col] = pile.get(0); // init first row
            prefixes[0][col] = pile.get(0); // init first row
            for (int row = 1; row < depth && row < pile.size(); row++) {
                original[col][row] = pile.get(row);
                arrays[row][col] = pile.get(row);
                prefixes[row][col] = prefixes[row-1][col] + arrays[row][col];
            }
        }
        printArrays("original", original);
        printArrays("piles", arrays, prefixes);

        // top only
        int topPrefix = 0;
        int[] top = Arrays.copyOf(arrays[0], arrays[0].length);
        Arrays.sort(top); // top elements of each pile
        for (int col = 0; col < depth && col < length; col++) topPrefix = topPrefix + top[top.length-1 - col];
        System.out.println(". top prefix = " + topPrefix);

        // deep piles
        int[] pilePrefixes = new int[depth];
        for (int col = 0; col < piles.size(); col++) {
            List<Integer> pile = piles.get(col);
            pilePrefixes[col] = pile.get(0); // init first element
            for (int row = 1; row < k; row++) {
                int value = row < pile.size() ? pile.get(row) : 0;
                pilePrefixes[col] = pilePrefixes[col] + value;
            }
        }
        System.out.println(". pile prefixes = " + Arrays.toString(pilePrefixes));
        Arrays.sort(pilePrefixes);
        int pilePrefix = pilePrefixes[pilePrefixes.length-1];

        int maxPrefix = Math.max(topPrefix, pilePrefix);

        // find max pile index
        int maxPrefixCol = -1, maxPilePrefix = 0;
        for (int j = 0; j < length; j++) {
            if (prefixes[depth-1][j] > maxPilePrefix) {
                maxPrefixCol = j;
                maxPilePrefix = prefixes[depth-1][j];
            }
        }
        System.out.println(". max pile[" + maxPrefixCol + "] = " + maxPilePrefix + ", depth = " + (depth-1));

        if (depth < 3) return maxPrefix;

        for (int col = 0; col < length; col++) {
            if (col == maxPrefixCol) continue; // skip itself
            for (int row = 0; row < depth && prefixes[row][col] != 0; row++) {
                int probeMaxPrefix = prefixes[depth-1-row-1][maxPrefixCol];
                int probeCurrPrefix = prefixes[row][col];
                if (probeMaxPrefix + probeCurrPrefix > maxPrefix) {
                    maxPrefix = Math.max(probeMaxPrefix + probeCurrPrefix, maxPrefix);
                    System.out.println("! next max prefix = " + maxPrefix + ", curr[" + row + "," + col + "]=" + prefixes[row][col] + ", max[" + (depth-1-row-1) + "," + maxPrefixCol + "]=" + prefixes[depth-1-row-1][maxPrefixCol]);
                }
            }
        }


        return maxPrefix;
    }

    private void printArrays(String name, int[][] arrays) {
        for(int i = 0; i < arrays.length; i++) {
            System.out.print(". " + name + "(" + (i+1) + ")" + " = " );
            printArray(arrays[i]);
            System.out.println();
        }
    }

    private void printArrays(String name, int[][] arrays, int[][] prefixes) {
        for(int i = 0; i < arrays.length; i++) {
            System.out.print(". " + name + "(" + (i+1) + ")" + " = " );
            printArray(arrays[i]);
            System.out.print(" -- ");
            printArray(prefixes[i]);
            System.out.println();
        }
    }

    private void printArray(int[] array) {
        System.out.print("[");
        for(int i = 0; i < array.length; i++) {
            System.out.print(" " + (array[i] > 100 ? array[i] : array[i] > 10 ? (" " + array[i]) : ("  " + array[i]))  + (i != array.length-1 ? "," : ""));
        }
        System.out.print("]");
    }
}
