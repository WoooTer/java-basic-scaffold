package wooter.algorithm.leetcode.AddTwoNumbers;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers_0 {

    public ListNode addTwoNumbers_0(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0; // 进位数

        while (l1 != null || l2 != null || carry > 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;

            int unit = (v1 + v2 + carry) % 10;

            if (tail == null) {
                head = tail = new ListNode(unit);
            } else {
                tail = tail.next = new ListNode(unit);
            }

            carry = (v1 + v2 + carry) / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return head;
    }

    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0; // 进位数

        for (; l1 != null || l2 != null || carry > 0;
             l1 = l1 != null ? l1.next : null, l2 = l2 != null ? l2.next : null) {

            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;

            int unit = (v1 + v2 + carry) % 10;

            if (tail == null) {
                head = tail = new ListNode(unit);
            } else {
                tail = tail.next = new ListNode(unit);
            }

            carry = (v1 + v2 + carry) / 10;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode l2 = new ListNode(9, new ListNode(9));
        ListNode node = new AddTwoNumbers_0().addTwoNumbers_1(l1, l2);
        System.out.println(node);
    }
}
