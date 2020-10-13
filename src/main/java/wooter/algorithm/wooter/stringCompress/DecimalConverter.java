package wooter.algorithm.wooter.stringCompress;

import wooter.utils.MyTimer;

import java.time.LocalDateTime;
import java.util.*;

public class DecimalConverter {

    private final static List<Character> INDEX_TABLE = Arrays.asList(
            '0','1','2','3','4','5','6','7','8','9',                                                             //10进制
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', //36进制
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', //62进制
            '!','@','#','$','%','^','&','*','(',')',                                                                 //72进制
            '`','-','=','[',']','\\',';','\'',',','.','/',                                                           //83进制
            '~','_','+','{','}','|' ,':','"' ,'<','>','?'                                                            //94进制
    );

    private Stack<Integer> stack = new Stack<>();

    private int sourceRank;
    private int targetRank;

    public DecimalConverter(int sourceRank, int targetRank){
        this.sourceRank = sourceRank;
        this.targetRank = targetRank;
    }

    //num是想要转换的数字，rank是想要转换的进制
    private long getResult(long num) { //获取整数商
        return num/targetRank;
    }
    private int getRemain(long num) { //获取余数
        return (int)(num%targetRank);
    }

    private void run(long num) {
        long result = this.getResult(num);
        if(result==0) {
            stack.push(this.getRemain(num));
        }else {
            stack.push(this.getRemain(num));
            run(result);
        }
    }

    public List<Integer> convertToIndexTable(long num){
        List<Integer> result = new ArrayList<>();
        run(num);
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private String convert(long num){
        StringBuilder result = new StringBuilder();
        this.convertToIndexTable(num).forEach( e -> {
            result.append(INDEX_TABLE.get(e));
        });
        return result.toString();
    }

    public String convert(String num){
        long n = Long.parseLong(num, sourceRank);
        return this.convert(n);
    }

    private long recoverToLong(String numConverted){
        double result = 0;
        char[] charTable = numConverted.toCharArray();
        double figureIndex = charTable.length - 1;
        for (int i = 0; i < charTable.length; i ++){
            int index = INDEX_TABLE.indexOf(charTable[i]);
            result += index * Math.pow(targetRank, figureIndex);
            figureIndex --;
        }
        return new Double(result).longValue();
    }

    public String recoverToStr(String numConverted){
        long n = this.recoverToLong(numConverted);
        return Long.toUnsignedString(n, sourceRank);
    }

    public static void main(String[] args) {
        DecimalConverter decimalChange = new DecimalConverter(16, 94);

        LocalDateTime start = LocalDateTime.now(); //Timer
        String numConverted = decimalChange.convert("2321fa2312d");
        System.out.println(numConverted);
        MyTimer.getDurationToMillis(start); //Timer

        String sourceNum = decimalChange.recoverToStr(numConverted);
        System.out.println(sourceNum);
    }
}
