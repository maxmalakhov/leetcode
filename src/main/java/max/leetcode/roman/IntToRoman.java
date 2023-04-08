package max.leetcode.roman;

import java.util.HashMap;
import java.util.Map;

public class IntToRoman {

    public static void main(String[] args) {

        IntToRoman java = new IntToRoman();

        System.out.println("="+java.romanToInt("DXXX")); // 530 
        System.out.println("="+java.romanToInt("LVIII")); // 58
        System.out.println("="+java.romanToInt("MCMXCIV")); // 1994
        System.out.println("="+java.romanToInt("DCXXI")); // 621
    }

    static final Map<Character, Roman> rules = new HashMap<>();

    // specify rules
    IntToRoman() {
        rules.put('I', new Roman('I', 1, 0));
        rules.put('V', new Roman('V', 5, 1));
        rules.put('X', new Roman('X', 10, 2));
        rules.put('L', new Roman('L', 50, 3));
        rules.put('C', new Roman('C', 100, 4));
        rules.put('D', new Roman('D', 500, 5));
        rules.put('M', new Roman('M', 1000, 6));
    }
 
    public int romanToInt(String string) {

         // convert
         char[] data = string.toCharArray();
 
         // compute
         Roman current = null;
         Roman next = null;
         Roman afternext = null;
         Roman validate = null;

         int result = 0;
         for (int i = 0; i < data.length; i++) {  

            // get
            current = checkAndGet(data[i]);
            if (data.length > i+1) {
                next = checkAndGet(data[i+1]);
            }
            if (data.length > i+2) {
                afternext = checkAndGet(data[i+2]);
            }
            if (data.length > i+3) {
                validate = checkAndGet(data[i+3]);
            }

            // validate
            if (next != null && afternext != null && validate != null && 
                current.rank == next.rank && next.rank == afternext.rank && afternext.rank == validate.rank) {

                    throw new IllegalArgumentException(
                    String.format("Not allowed sequence of char '%c' in the string!", current.code));
            }
             
             if (next != null && afternext != null && current.rank == next.rank && next.rank == afternext.rank) {
                 result += current.number + next.number + afternext.number;
                 i +=2; // step over two
             } else if (next != null && current.rank == next.rank) {
                 result += current.number + next.number;
                 i +=1; // step over one
             } else if (next != null && current.rank < next.rank) {
                 result += next.number - current.number;
                 i +=1; // step over one
             } else {
                 result += current.number;
             }
             System.out.println("+"+result);

             next = null;
             afternext = null;
             validate = null;
         }
         return result;
    }

    private Roman checkAndGet(char ch) {
        Roman roman = rules.get(ch);

        if (roman == null) {
            throw new IllegalArgumentException(
                String.format("Not allowed char '%c' in the string!", ch));
        }
        return roman;
    }

    public class Roman {
         
        char code;
        int number;
        int rank;
        
        public Roman(char code, int number, int rank) {
            this.code = code;
            this.number = number;
            this.rank = rank;
        }
    }
}
