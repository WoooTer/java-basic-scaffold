package wooter.algorithm.leetcode.AddTwoNumbers;

public class AddTwoNumbers_1 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return func(l1, l2, 0);
    }

    public ListNode func(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;

        int unit = (v1 + v2 + carry) % 10;
        int nextCarry = (v1 + v2 + carry) / 10;

        ListNode node = new ListNode(unit);
        node.next = func(l1 == null ? null : l1.next, l2 == null ? null : l2.next, nextCarry);
        return node;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode l2 = new ListNode(9, new ListNode(9));
        l1.print();
        l2.print();

        ListNode node = new AddTwoNumbers_1().addTwoNumbers(l1, l2);
        node.print();
    }
}
