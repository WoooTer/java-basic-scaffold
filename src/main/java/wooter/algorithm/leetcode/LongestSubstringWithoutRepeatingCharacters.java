package wooter.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 时间复杂度：O(n^3)
     */
    public int lengthOfLongestSubstring_0(String s) {
        Set<Character> set = new HashSet<>();
        int sub_i = 0, sub_j = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {

                char c = s.charAt(j);
                if (set.contains(c)) {
                    if (j - i > sub_j - sub_i) {
                        sub_j = j;
                        sub_i = i;
                    }
                    set.clear();
                    break;
                }
                if (j == s.length() - 1) {
                    if (j + 1 - i > sub_j - sub_i) {
                        sub_j = j + 1;
                        sub_i = i;
                    }
                    set.clear();
                    break;
                }
                if (!set.contains(c)) {
                    set.add(c);
                }

            }
        }
        System.out.println(s.substring(sub_i, sub_j));
        return sub_j - sub_i;
    }

    /**
     * 时间复杂度：O(n^2)
     */
    public int lengthOfLongestSubstring_1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int sub_i = 0, sub_j = 0;

        int i = 0, j = 0;
        while (j < s.length()) {

            char c = s.charAt(j);
            if (map.containsKey(c)) {
                if (j - i > sub_j - sub_i) {
                    sub_j = j;
                    sub_i = i;
                }
                i = map.get(c) + 1;
                map.put(c, j);
                _removeMapElement(map, i);
                j++;
                continue;
            }
            if (j == s.length() - 1) {
                if (j + 1 - i > sub_j - sub_i) {
                    sub_j = j + 1;
                    sub_i = i;
                }
                break;
            }
            if (!map.containsKey(c)) {
                map.put(c, j);
                j++;
            }
        }

        System.out.println(s.substring(sub_i, sub_j));
        return sub_j - sub_i;
    }

    void _removeMapElement(Map<Character, Integer> map, Integer i){
        Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Character, Integer> entry = it.next();
            if (entry.getValue() < i){
                it.remove();
            }
        }

        /**
         * 其他写法
         */
//        map.entrySet().removeIf(e -> e.getValue() < i);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int inlen = s.length();
        int result = 0;
        int start = 0;  // 左指针初始位置
        int end = 0;    // 右指针初始位置
        HashSet<Character> subset = new HashSet<>();
        subset.add(s.charAt(0)); // 先把第0个字符放入Set

        while (start < inlen) {
            // 先判断长度，再判断是否包含字符，避免越界
            // end初始位置是0，0号字符已被加入集合，从下一个字符开始计算
            while (end + 1 < inlen && ! subset.contains(s.charAt(end + 1)) ) {
                subset.add(s.charAt(end + 1));
                end++;
            }
            result = Math.max(result, end - start + 1);
            if (end + 1 == inlen) { // 右指针移动到最后，可以终止计算，不需要再循环
                break;
            }
            subset.remove(s.charAt(start));
            start ++;
        }

        return result;
    }

    public static void main(String[] args) {
        // tmmzuxt, abcabcbb, pwwkew
        int r = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew");
        System.out.println(r);
    }

}
