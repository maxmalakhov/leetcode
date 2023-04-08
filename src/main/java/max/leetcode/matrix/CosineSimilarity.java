package max.leetcode.matrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CosineSimilarity {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int a_keysCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a_keys = IntStream.range(0, a_keysCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int a_valuesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> a_values = IntStream.range(0, a_valuesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(toList());

        int b_keysCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> b_keys = IntStream.range(0, b_keysCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int b_valuesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> b_values = IntStream.range(0, b_valuesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(toList());

        double result = 0.0; //Result.cosine_similarity(a_keys, a_values, b_keys, b_values);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public  double cosine_similarity(List<Integer> a_keys, List<Double> a_values, List<Integer> b_keys, List<Double> b_values) {
        // Write your code here

        double dot_product = 0.0, normalized_a = 0.0, normalized_b = 0.0, current_a, current_b;
        int current_key;

        List<Integer> intersection = a_keys.stream()
                .distinct()
                .filter(b_values::contains)
                .collect(toList());

        for (int i = 0; i < intersection.size(); i++) {

            current_key = intersection.get(i);
            current_a = a_values.get(current_key); // null -> 0.0
            current_b = b_values.get(current_key); // null -> 0.0

            dot_product += current_a * current_b;

            normalized_a += Math.pow(current_a, 2);
            normalized_b += Math.pow(current_b, 2);

        }

        return dot_product / (Math.sqrt(normalized_a) * Math.sqrt(normalized_b));
    }
}
