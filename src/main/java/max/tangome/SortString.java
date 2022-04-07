package max.tangome;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class SortString {
    
    public String sort(String input, Collection<Character> alpabet) {

        Character[] string = input.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        String order = alpabet.stream().map(String::valueOf).collect(Collectors.joining());

        Arrays.sort(string, (a, b) -> {
            if (order.indexOf(a) > -1 && order.indexOf(b) > -1) {
                return order.indexOf(a) - order.indexOf(b);
            } else if (order.indexOf(a) > -1) {
                return -1;
            } else if (order.indexOf(b) > -1) {
                return 1;
            }
            return a.compareTo(b);
        });

        return Arrays.stream(string).map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {

        SortString solution = new SortString();

        String input1 = "bacaed";
        String result1 = solution.sort(input1, Arrays.asList('a','b','c','d','e'));
        
        System.out.println(input1 + ": " +result1); // aabcde

        String input2 = "abacabax";
        String result2 = solution.sort(input2, Arrays.asList('x','b','f'));
        
        System.out.println(input2 + ": " +result2); // xbbaaaac
    }
}
