package max.leetcode.medium;

/**
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * 
 * Constrains:
 *  1 <= n <= 500
 *  -500 <= Node.val <= 500
 *  1 <= left <= right <= n
 */
public class ReverseBetween {
    
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode leftNode = null, rightNode = null, first = null, last = null, previous = null, next;
        for (int count = 1; head != null; count++) {
            if (first == null) {
                first = head; // buf (1)
            }
            if (count < left) {
                leftNode = head; // buf left (1)
                head = head.next; // forward (2)
            } else if (count <= right) {
                if (rightNode == null) {
                    rightNode = head; // buf right (2)
                }
                next = head.next; // buf next
                head.next = previous; // set (2)
                previous = head; // set (3) 
                head = next; // reverse
            } else {
                if (last == null) {
                    last = head;
                }
                head = head.next; // forward
            }
        }
        // update left refs
        if (leftNode != null && previous != null) {
            leftNode.next = previous; // set (1)
        }
        // update right refs
        if (rightNode != null && last != null) {
            rightNode.next = last; // set (2)
        }
        return left == 1 ? previous : first;
    }

    public static void printList(String msg, ListNode node) {  
        System.out.print(msg);
        while (node != null) {

            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        ReverseBetween solution = new ReverseBetween();

        ListNode head24 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head14 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head25 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head34 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        printList("init: ", head24); // 1 2 3 4 5

        printList("2, 4: ", solution.reverseBetween(head24, 2, 4)); // -> 1 4 3 2 5
        printList("1, 4: ", solution.reverseBetween(head14, 1, 4)); // -> 4 3 2 1 5
        printList("2, 5: ", solution.reverseBetween(head25, 2, 5)); // -> 1 5 4 3 2
        printList("3, 4: ", solution.reverseBetween(head34, 3, 4)); // -> 1 2 4 3 5
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
