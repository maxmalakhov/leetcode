package max.leetcode.medium;

public class RomanToInt {

    public static void main(String[] args) {

        RomanToInt java = new RomanToInt();

        System.out.println("="+java.intToRoman(2964)); // MMCMLXIV
        System.out.println("="+java.intToRoman(3456)); // MMMCDLVI
    }

    public String intToRoman(int num) {

        // validate
        if (num > 4000) {
            throw new IllegalArgumentException(
                    String.format("Not allowed num '%s'; 3999 is max!", num));
        }

        // split
        int thousands = num - num % 1000;
        int hundreds = num - thousands - num % 100;
        int dozen = num - thousands - hundreds - num % 10;
        int number = num % 10;

        // convert
        StringBuilder result = new StringBuilder();
        result.append(new RomanConverot(1000).convert(thousands));
        result.append(new RomanConverot(100).convert(hundreds));
        result.append(new RomanConverot(10).convert(dozen));
        result.append(new RomanConverot(1).convert(number));

        System.out.println(thousands+"+"+hundreds+"+"+dozen+"+"+number);

        return result.toString();
    }

    class RomanConverot {
        final int base; // 1, 10, 100, 1000
        final char max; // X, C, M, ?
        final char mid; // V, L, D, ?
        final char low; // I, X, C, M

        RomanConverot(int base) {
            this.base = base;
            if (base == 1) {
                max = 'X';
                mid = 'V';
                low = 'I';
            } else if (base == 10) {
                max = 'C';
                mid = 'L';
                low = 'X';
            } else if (base == 100) {
                max = 'M';
                mid = 'D';
                low = 'C';
            } else if (base == 1000) {
                max = '?';
                mid = '?';
                low = 'M';
            } else {
                throw new IllegalArgumentException(
                    String.format("Not allowed base '%s'; 1, 10, 100 and 1000 are allowed!", base));
            }
        }

        StringBuilder convert(int num) {
            final StringBuilder result = new StringBuilder();
            int value = num / base;
            // compute
            if (value == 9) {
                result.append(low).append(max);
            } else if (value > 5) {
                result.append(mid).append(genereate(low, (value - 5)));
            } else if (value == 5) {
                result.append(mid);
            } else if (value == 4) {
                result.append(low).append(mid);
            } else {
                result.append(genereate(low, (value)));
            }
            return result;
        }

        private char[] genereate(char ch, int count) {
            char[] chars = new char[count];
            for (int i = 0; i < count; i++) {
                chars[i] = ch;
            }
            return chars;
        }
    }
}
