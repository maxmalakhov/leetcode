package max.leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SuccessfulPairs {

    public static void main(String[] args) {

        SuccessfulPairs java = new SuccessfulPairs();

        System.out.println("# [5,1,3] * [1,2,3,4,5] >= 7 -- " + Arrays.toString(java.successfulPairs(new int[] {5,1,3}, new int [] {1,2,3,4,5}, 7)) + "' == '[4, 0, 3]'");
        System.out.println("# [3,1,2] * [8,5,8] >= 16 -- " + Arrays.toString(java.successfulPairs(new int[] {3,1,2}, new int [] {8,5,8}, 16)) + "' == '[2, 0, 2]'");
        System.out.println("# [15,8,19] * [38,36,23] >= 328 -- " + Arrays.toString(java.successfulPairs(new int[] {15,8,19}, new int [] {38,36,23}, 328)) + "' == '[3, 0, 3]'");
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) { // O(n)
            int left = 0, right = potions.length - 1;
            int idx = Integer.MAX_VALUE;
            while (left <= right) { // O(logn)
                int pos = (left + right) / 2;
                long current = (long) potions[pos] * spells[i];
                System.out.println(". spell = " + spells[i] + ", potions[" + pos + "] = " + potions[pos] + ", current = " + current + ", success = " + success);
                if (current >= success) {
                    idx = Math.min(idx, pos);
                    right = pos - 1;
                } else {
                    left = pos + 1;
                }
            }
            spells[i] = idx < Integer.MAX_VALUE ? potions.length - idx : 0;
//            for (int j = potions.length - 1; j >= 0 && (long) potions[j] * spell >= success; j--) { // O(n/2)
//                System.out.println(". spell = " + spells[i] + ", potion = " + potions[j] + ", sum = " + (long) potions[j] * spell);
//                spells[i]++;
//            }
        }
        return spells;
    }
}
