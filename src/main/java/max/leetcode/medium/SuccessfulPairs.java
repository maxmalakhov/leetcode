package max.leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SuccessfulPairs {

    public static void main(String[] args) {

        SuccessfulPairs java = new SuccessfulPairs();

        System.out.println(" [5,1,3] * [1,2,3,4,5] >= 7 " + Arrays.toString(java.successfulPairs(new int[] {5,1,3}, new int [] {1,2,3,4,5}, 7)) + "' == '[3.0.4]'");
        System.out.println(" [3,1,2] * [8,5,8] >= 16 " + Arrays.toString(java.successfulPairs(new int[] {3,1,2}, new int [] {8,5,8}, 16)) + "' == '[2.0.2]'");
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] pairs = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int count = 0;
            for (int j = 0; j < potions.length; j++) {
                if ((long) spells[i] * potions[j] >= success) {
                    count++;
                }
            }
            pairs[i] = count;
        }
        return pairs;
    }
}
