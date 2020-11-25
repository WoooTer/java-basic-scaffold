package wooter.algorithm.leetcode.StringToIntegerAtoi;

import java.util.HashMap;
import java.util.Map;

public class StringToIntegerAtoi_0 {

    private Map<String, Integer> numMap = new HashMap<>();

    public int myAtoi(String s) {
        _initNumMap();
        String f = _filter(s);
        try {
            return Integer.valueOf(f);
        } catch (Exception e) {
            if (f.contains("-")){
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    private void _initNumMap(){
        for (int i = 0; i <= 9; i++){
            this.numMap.put(Integer.toString(i), i);
        }
    }

    private String _filter(String s){
        String[] arr = s.split("");
        int start = -1;
        int end = arr.length;

        for (int i = 0; i < arr.length; i++){
            String one = arr[i];
            if (" ".equals(one) && start == -1){
                continue;
            }
            if (("-".equals(one) || "+".equals(one) || numMap.containsKey(one)) && start == -1){
                start = i;
                continue;
            }
            if (!numMap.containsKey(one)){
                end = i;
                break;
            }
        }

        if (start == -1){
            return "0";
        }

        String f = s.substring(start, end);
        if ("-".equals(f) || "+".equals(f)){
            return "0";
        } else {
            return f;
        }
    }

    public static void main(String[] args) {
        int r = new StringToIntegerAtoi_0().myAtoi("1230123");
        System.out.println(r);
    }

}
