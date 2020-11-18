package wooter.algorithm.leetcode.AddTwoNumbers;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    void print(){
        _print(this);
        System.out.println();
    }

    private void _print(ListNode node){
        if (node == null){
            return;
        }
        System.out.print(" -> " + node.val);
        _print(node.next);
    }

    List<Integer> toList(){
        List<Integer> list = new ArrayList<>();
        _toList(this, list);
        return list;
    }

    private void _toList(ListNode node, List<Integer> list){
        if (node == null){
            return;
        }
        list.add(node.val);
        _toList(node.next, list);
    }
}
