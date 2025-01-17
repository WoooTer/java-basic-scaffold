package wooter.algorithm.leetcode.bank.s206.反转链表;

import java.util.Stack;

public class Solution3 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        ListNode list = ListNode.createList(array);
        reverseList(list);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        Stack<Integer> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        ListNode newHead = new ListNode(-1);
        ListNode c = newHead;
        while (!stack.isEmpty()) {
            c.next = new ListNode(stack.pop());
            c = c.next;
        }
        return newHead.next;
    }
}
