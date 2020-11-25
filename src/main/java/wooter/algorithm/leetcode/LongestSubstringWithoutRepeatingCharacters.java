package wooter.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 时间复杂度：O(n^3)
     * 暴力求解
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
     * 使用了滑动窗口及 Map 数据结构，但每次都要遍历删除掉 Map 的无效值
     */
    public int lengthOfLongestSubstring_1(String s) {
        int count = (int) Arrays.stream(s.split("")).distinct().count(); // 收效甚微
        Map<Character, Integer> map = new HashMap<>(count);
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

    private void _removeMapElement(Map<Character, Integer> map, Integer i) {
        Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Integer> entry = it.next();
            if (entry.getValue() < i) {
                it.remove();
            }
        }

        /**
         * 其他写法
         */
//        map.entrySet().removeIf(e -> e.getValue() < i);
    }

    /**
     * 时间复杂度：O(n)
     * 使用了滑动窗口及 Map 数据结构，并直接通过下标 i 来记录 Map 的无效值
     */
    public int lengthOfLongestSubstring_2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int sub_i = 0, sub_j = 0;

        int i = 0, j = 0;
        while (j < s.length()) {

            char c = s.charAt(j);
            if (_isMapElementValid(map, c, i)) {
                if (j - i > sub_j - sub_i) {
                    sub_j = j;
                    sub_i = i;
                }
                i = map.get(c) + 1;
                map.put(c, j);
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
            if (!_isMapElementValid(map, c, i)) {
                map.put(c, j);
                j++;
            }

        }

        System.out.println(s.substring(sub_i, sub_j));
        return sub_j - sub_i;
    }

    private boolean _isMapElementValid(Map<Character, Integer> map, char element, int window_i) {
        return map.containsKey(element) && map.get(element) >= window_i;
    }

    /**
     * 时间复杂度：O(n^2)，但由于 Set 内部由 Map 实现，所以复杂度降为 O(n)
     * 使用了滑动窗口及 Set 数据结构
     *
     * pwwkew：[p]wwkew --> [pw]wkew --> p[w]wkew --> pww[]kew --> pww[k]ew --> pww[ke]w --> pww[kew]
     */
    public int lengthOfLongestSubstring_3(String s) {
        Set<Character> set = new HashSet<>();
        int sub_i = 0, sub_j = 0;

        int i = 0, j = 0;
        while (j < s.length()){

            while (j < s.length() && !set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
            }
            if (j - i > sub_j - sub_i) {
                sub_j = j;
                sub_i = i;
            }
            set.remove(s.charAt(i));
            i++;

        }
        System.out.println(s.substring(sub_i, sub_j));
        return sub_j - sub_i;
    }

    public static void main(String[] args) {
        // tmmzuxt, abcabcbb, pwwkew, aabaab!bb
        int r = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring_3("tmmzuxt");
        System.out.println(r);
    }

}
