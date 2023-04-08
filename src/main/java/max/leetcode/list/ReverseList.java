package max.leetcode.list;

/**
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Constraints: [0, 5000]
 */

public class ReverseList {

    public static void main(String[] args) {

        ReverseList solution = new ReverseList();

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        printList(head); // 1 2 3 4 5

        ListNode reversed = solution.reverseList(head);
        printList(reversed); // 5 4 3 2 1

        ListNode doubleReversed = solution.reverseListRecursively(reversed);
        printList(doubleReversed); // 1 2 3 4 5
    }
    
    public ListNode reverseList(ListNode head) {

        ListNode previous = null, next;
        while (head != null) {

            next = head.next; // buf next
            head.next = previous; // first: null
            previous = head; // reverse
            head = next; // interate
        }

        return previous;
    }

    public ListNode reverseListRecursively(ListNode head) {

        // check first or end
        if (head == null || head.next == null) {  
            return head;
        }

        // interate
        ListNode current = reverseListRecursively(head.next);
        // move last node
        head.next.next = head;
        // reset old head refr
        head.next = null;

        return current;
    }

    public static void printList(ListNode node)  
    {  
        while (node != null) {

            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
