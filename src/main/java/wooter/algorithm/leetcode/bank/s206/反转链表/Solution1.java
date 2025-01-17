package wooter.algorithm.leetcode.bank.s206.反转链表;

import javax.xml.soap.Node;

public class Solution1 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode nextTemp = cur.next;

            cur.next = pre;

            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }
}
