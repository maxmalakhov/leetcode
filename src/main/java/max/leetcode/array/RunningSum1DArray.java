package max.leetcode.array;

import java.util.Arrays;

public class RunningSum1DArray {

    public static void main(String[] args) {

        RunningSum1DArray java = new RunningSum1DArray();

        System.out.println("# nums = [1,2,3,4] -- " + Arrays.toString(java.runningSum(new int[] {1,2,3,4})) + "' == ' [1,3,6,10]'");
        System.out.println("# nums = [1,1,1,1,1] -- " + Arrays.toString(java.runningSum(new int[] {1,1,1,1,1})) + "' == ' [1,2,3,4,5]'");
        System.out.println("# nums = [3,1,2,10,1] -- " + Arrays.toString(java.runningSum(new int[] {3,1,2,10,1})) + "' == ' [3,4,6,16,17]'");
    }

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        return nums;
    }
}
