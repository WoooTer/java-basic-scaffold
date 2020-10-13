package wooter.algorithm.iamshuaidi;

import java.util.ArrayList;
import java.util.List;

/**
 * [https://github.com/iamshuaidi/algo-basic]
 */
public class Recursion {

    public static void main(String[] args) {
        test_step();
        test_reverseList();
    }

    static int factorial(int n) {
        if (n <= 2) {
            return n;
        }
        // 把 f(n) 的等价操作写进去
        return factorial(n - 1) * n;
    }

    /**
     * 1、1、2、3、5、8、13、21、34
     */
    static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 小青蛙跳台阶：
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     */
    static int stepByRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return stepByRecursion(n - 1) + stepByRecursion(n - 2);
    }

    static int stepByDP(int n) {
        if (n <= 2) {
            return n;
        }
        // 先创建一个数组来保存历史数据
        int[] dp = new int[n + 1];
        // 给出初始值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        // 通过关系式来计算出 dp[n]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    static void test_step(){
        int step = 7;
        System.out.println("递归: " + stepByRecursion(7));
        System.out.println("动归: " + stepByDP(7));
    }

    /**
     * 反转单链表：
     */
    static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归反转子链表
        Node newList = reverseList(head.next);
        // 改变 head、head.next 节点的指向，让 head.next 的 next 指向 head
        head.next.next = head;
        // head 的 next 指向 null
        head.next = null;
        // 把调整之后的链表返回
        return newList;
    }

    static void test_reverseList() {
        Node node4 = new Node(4, null);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        node1.printAllNodes();
        Node reverseNode = reverseList(node1);
        reverseNode.printAllNodes();
    }

}

class Node {
    int data;
    Node next;

    Node(int data, Node next){
        this.data = data;
        this.next = next;
    }

    void printAllNodes(){
        List<String> _datas = new ArrayList<>();
        _datas.add(data + "");

        Node _next = next;
        while (_next != null){
            _datas.add(_next.data + "");
            _next = _next.next;
        }
        System.out.println(String.join("->", _datas));
    }
}