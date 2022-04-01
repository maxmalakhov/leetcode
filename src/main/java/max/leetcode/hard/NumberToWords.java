package max.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class NumberToWords {

    static final Map<Integer, String> mapping = new HashMap<>();

    // specify mappers
    NumberToWords() {
        //mapping.put(0, "");
        mapping.put(1, "One");
        mapping.put(2, "Two");
        mapping.put(3, "Three");
        mapping.put(4, "Four");
        mapping.put(5, "Five");
        mapping.put(6, "Six");
        mapping.put(7, "Seven");
        mapping.put(8, "Eight");
        mapping.put(9, "Nine");
        mapping.put(10, "Ten");
        mapping.put(11, "Eleven");
        mapping.put(12, "Twelve");
        mapping.put(13, "Thirteen");
        mapping.put(14, "Fourteen");
        mapping.put(15, "Fifteen");
        mapping.put(16, "Sixteen");
        mapping.put(17, "Seventeen");
        mapping.put(18, "Eighteen");
        mapping.put(19, "Nineteen");
        mapping.put(20, "Twenty");
        mapping.put(30, "Thirty");
        mapping.put(40, "Forty");
        mapping.put(50, "Fifty");
        mapping.put(60, "Sixty");
        mapping.put(70, "Seventy");
        mapping.put(80, "Eighty");
        mapping.put(90, "Ninety");
        //
        mapping.put(100, "Hundred");
        mapping.put(1000, "Thousand");
        mapping.put(1000000, "Million");
        mapping.put(1000000000, "Billion");
    }

    public String numberToWords(int num) {

        // validate
        if (num < 0 || num > 2147483647) {
            throw new IllegalArgumentException(
                    String.format("Not allowed num '%s'; from 0 to 2147483647 are allowed!", num));
        }
        if (num == 0) {
            return "Zero";
        }

        // split
        int billions = num - num % 1_000_000_000;
        int millions = num - billions - num % 1_000_000;
        int thousands = num - billions - millions - num % 1_000;
        int hundreds = num - billions - millions - thousands;

        // convert
        StringBuilder result = new StringBuilder();
        result.append(new WordConvertor(1_000_000_000).convert(billions));
        result.append(new WordConvertor(1_000_000).convert(millions));
        result.append(new WordConvertor(1_000).convert(thousands));
        result.append(new WordConvertor(1).convert(hundreds));

        return num + ": " + result.toString().trim();
    }

    class WordConvertor {
        final int base; // 1000_000_0000, 1000_000, 1000, 1
        final String unit; // B, M, T

        WordConvertor(int base) {
            this.base = base;
            this.unit = base != 1 ? mapping.get(base) : "";
            if (unit == null) {
                throw new IllegalArgumentException(
                    String.format("Not allowed base '%s'; 1, 1000, 1000_000 ... are allowed!", base));
            }
        }

        StringBuilder convert(int num) {
            //System.out.println(String.format("+'%s'", num));
            final StringBuilder result = new StringBuilder();
            if (num == 0) {
                return result;
            }
            int number = num / base;
            if (number != 100) {
                build(result, mapping.get(number));
            }
            build(result, mapping.get(number / 100), mapping.get(100));
            int dozens = number > 100 ? number % 100 : (mapping.get(number) == null ? number : 0);
            if (dozens >= 20) {
                int value = dozens % 10;
                build(result, mapping.get(dozens - value));
                build(result, mapping.get(value));
            } else {
                build(result, mapping.get(dozens));
            }
            return result.append(unit).append(" ");
        }

        private void build(StringBuilder result, String word, String unit) {
            if (word != null) {
                result.append(word).append(" ").append(unit).append(" ");
            }
        }
        private void build(StringBuilder result, String word) {
            if (word != null) {
                result.append(word).append(" ");
            }
        }
    }

    public static void main(String[] args) {

        NumberToWords solution = new NumberToWords();

        System.out.println("="+solution.numberToWords(0));
        System.out.println("="+solution.numberToWords(8));
        System.out.println("="+solution.numberToWords(21));
        System.out.println("="+solution.numberToWords(40));
        System.out.println("="+solution.numberToWords(100));
        System.out.println("="+solution.numberToWords(909));
        System.out.println("="+solution.numberToWords(100000));
        System.out.println("="+solution.numberToWords(18909));
        System.out.println("="+solution.numberToWords(30089091));
        System.out.println("="+solution.numberToWords(1847483618));
        System.out.println("="+solution.numberToWords(2147409387)); 
    }
}
