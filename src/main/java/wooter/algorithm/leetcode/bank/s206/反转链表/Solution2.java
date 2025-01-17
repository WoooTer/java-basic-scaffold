package wooter.algorithm.leetcode.bank.s206.反转链表;

public class Solution2 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = reverseList(head.next);

        head.next.next = head;
        head.next = null;
        return last;
    }
}
