package wooter.hacker.rank;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;


public class Anagram {

    /*
     * Complete the 'stringAnagram' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY dictionary
     *  2. STRING_ARRAY query
     */

    public static List<Integer> stringAnagram(List<String> dictionary, List<String> query) {
        List<Map<Character, Integer>> dictionaryParsed = dictionary.stream().map(Anagram::parseStr).collect(Collectors.toList());
        return query.stream().map(e -> stringAnagramOne(dictionaryParsed, e)).collect(Collectors.toList());
    }

    private static Integer stringAnagramOne(List<Map<Character, Integer>> dictionaryParsed, String str) {
        return (int) dictionaryParsed.stream().filter(e -> compareParsed(e, parseStr(str))).count();
    }

    private static boolean compareParsed(Map<Character, Integer> x, Map<Character, Integer> y) {
        if (x.size() != y.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : x.entrySet()) {
            Integer yCount = y.get(entry.getKey());
            if (yCount == null) {
                return false;
            }
            if (!entry.getValue().equals(yCount)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> parseStr(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            Integer count = map.get(c);
            if (count != null) {
                map.put(c, count + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }

}

