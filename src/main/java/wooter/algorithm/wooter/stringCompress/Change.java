package wooter.algorithm.wooter.stringCompress;

import java.util.Scanner;
import java.util.Stack;

public class Change {
    Stack<String> stack = new Stack<String>();
    //num是想要转换的数字，rank是想要转换的进制
    public int getResult(int num,int rank) {//获取整数商
        return num/rank;
    }
    public int getRemain(int num,int rank) {//获取余数
        return num%rank;
    }
    public void run(int num,int rank) {
        int result=getResult(num, rank);
        //当整除是将余数添加到栈中，退出递归进程
        if(result==0) {
            stack.push(getRemain(num, rank)+" ");
        }else {
            stack.push(getRemain(num, rank)+" ");
            run(result, rank);//进行递归
        }
    }
    public static void main(String[] args) {
        Change change=new Change();
        System.out.println("请输入一个十进制数字:");
        Scanner sca = new Scanner(System.in);
        int num = sca.nextInt();
        System.out.println("请输入想要转换为几进制的数字:");
        int rank = sca.nextInt();
        change.run(num, rank);
        String Result = "";
        while(!change.stack.isEmpty()) {
            Result += change.stack.pop();
        }
        System.out.println(Result);
    }
}