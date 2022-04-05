package max.leetcode.easy;

public class ClimbStairs {
    
    public int climbStairs(int n) {
        // validate
        if (n < 0 || n > 45) {
            throw new IllegalArgumentException(String.format("Not allowed number: '%s', 0 .. 45 are only allowed", n));
        } else if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        // compute
        int previous = 1, current = 1, buffer = 0;
        for (int i = 2; i < n; i++) {
            buffer = current;
            current += previous;
            previous = buffer;
        }

        return current + previous;
    }

    public static void main(String[] args) {

        ClimbStairs solution = new ClimbStairs();

        System.out.println("0  = " + solution.climbStairs(0)); // 0
        System.out.println("1  = " + solution.climbStairs(1)); // 1 
        System.out.println("2  = " + solution.climbStairs(2)); // 2 
        System.out.println("3  = " + solution.climbStairs(3)); // 3
        System.out.println("4  = " + solution.climbStairs(4)); // 5  (2)
        System.out.println("5  = " + solution.climbStairs(5)); // 8  (3)
        System.out.println("6  = " + solution.climbStairs(6)); // 13 (5)
        System.out.println("7  = " + solution.climbStairs(7)); // 21 (8)
        System.out.println("8  = " + solution.climbStairs(8)); // 34 (13)
        System.out.println("9  = " + solution.climbStairs(9)); // 55 (21)
        System.out.println("10 = " + solution.climbStairs(10)); // 89
        System.out.println("13 = " + solution.climbStairs(13)); // 43
        System.out.println("33 = " + solution.climbStairs(33)); // 273
        System.out.println("45 = " + solution.climbStairs(45)); // 507
    }
}
