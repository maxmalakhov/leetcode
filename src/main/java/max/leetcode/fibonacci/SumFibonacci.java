package max.leetcode.fibonacci;

/**
 * Fibonacci Numbers are the numbers found in an integer sequence referred to as the Fibonacci sequence. 
 * The sequence is a series of numbers characterized by the fact that every number is the sum of the two numbers preceding it. 
 * The initial two numbers in the sequence are either 1 and 1, or 0 and 1, and each successive number is a sum of the previous two as shown below:
 * 
 *  1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ……….. or 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144…….
 * 
 *                          [ Xn = Xn-1 + xn-2 ]
 */
public class SumFibonacci {

    public static void main(String[] args) {

        SumFibonacci solution = new SumFibonacci();

        System.out.println("2  = " + solution.fib(2)); // 1
        System.out.println("8  = " + solution.fib(8)); // 21
        System.out.println("21 = " + solution.fib(21)); // 10946
        System.out.println("30 = " + solution.fib(30)); // 832040
    }

    public int fib(int n) {

        // validate
        if (n > 30) {
            throw new IllegalArgumentException(String.format("Not allowed number: '%s', 0 .. 30 are only allowed", n));
        }
        if (n <= 0) {
            return 0;
        }

        // compute
        int previous = 0, current = 1, buffer = 0;
        for (int i = 1; i < n-1; i++) {
            buffer = current;
            current += previous;
            previous = buffer;
        }
        
        return current + previous;
    }
}
