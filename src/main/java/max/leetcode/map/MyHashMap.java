package max.leetcode.map;

import java.util.Arrays;

/**
 * Input:
 *  ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 *  [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * <p>
 * Output:
 *  [null, null, null, 1, -1, null, 1, null, -1]
 * <p>
 * Constraints:
 *  0 <= key, value <= 10^6
 *  At most 10^4 calls will be made to put, get, and remove.
 */
public class MyHashMap {

    public static void main(String[] args) {

        MyHashMap solution = new MyHashMap();

        solution.put(1, 1);
        solution.put(1000, 1000);
        solution.put(1001, 1001);
        solution.put(4001, 4001);

        solution.remove(1001);

        solution.printout();
    }

    private static final int SIZE = 1000;
    private int[] hashtable = new int[SIZE];

    public MyHashMap() {
        init(0);
    }

    public void put(int key, int value) {
        if (key >= hashtable.length) {
            resize(key);  // length 1000, key == 3000
        }
        hashtable[key] = value;
    }

    public int get(int key) {
        return key < hashtable.length ? hashtable[key] : -1;
    }

    public void remove(int key) {
        if (key < hashtable.length) {
            hashtable[key] = -1;
        }
    }

    public void printout() {
        for (int value : hashtable) {
            System.out.print(value + " ");
        }
    }

    private void resize(int key) {
        int oldSize = hashtable.length;
        int newSize = (key + SIZE) / SIZE * SIZE - oldSize;
        hashtable = Arrays.copyOf(hashtable, oldSize + newSize);
        init(oldSize);
    }

    private void init(int start) {
        for (int i = start; i < hashtable.length; i++) {
            hashtable[i] = -1;
        }
    }
}
