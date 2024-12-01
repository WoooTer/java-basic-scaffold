package wooter.hacker.rank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Deprecated
public class MedianAnalysis {

    public static void main(String[] args) {
        List<Integer> result = getMaxSubsequenceLen(Arrays.asList(1, 2, 2, 3, 4, 4, 4, 5, 6, 6, 6, 6));
        System.out.println(result);
    }

    public static List<Integer> getMaxSubsequenceLen(List<Integer> arr) {
        List<Integer> result = new ArrayList<>();
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int maxLen = 1;
            for (int len = 3; len <= n; len += 2) {
                int start = Math.max(0, i - (len - 1) / 2);
                int end = Math.min(n - 1, i + (len - 1) / 2);
                int[] count = new int[3];
                Set<Integer> lessSet = new HashSet<>();
                Set<Integer> greaterSet = new HashSet<>();
                for (int j = start; j <= end; j++) {
                    if (arr.get(j) < arr.get(i)) {
                        if (lessSet.add(arr.get(j))) {
                            count[0]++;
                        }
                    } else if (arr.get(j) > arr.get(i)) {
                        if (greaterSet.add(arr.get(j))) {
                            count[1]++;
                        }
                    } else {
                        count[2]++;
                    }
                }
                if (count[0] == count[1]) {
                    maxLen = Math.max(maxLen, len);
                }
            }
            result.add(maxLen);
        }
        return result;
    }
}
