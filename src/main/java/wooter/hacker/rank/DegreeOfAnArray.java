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

public class DegreeOfAnArray {

    public static void main(String[] args) {
        int count = degreeOfArray(Arrays.asList(1, 1, 2, 1, 2, 2));
        System.out.println(count);
    }

    public static int degreeOfArray(List<Integer> arr) {
        Map<Integer, Integer> elementShowCountMap = new HashMap<>(arr.size());
        Map<Integer, List<Integer>> elementShowIndexListMap = new HashMap<>(arr.size());

        for (int i = 0; i < arr.size(); i++) {
            Integer element = arr.get(i);
            elementShowCountMap.merge(element, 1, Integer::sum);
            elementShowIndexListMap.computeIfAbsent(element, k -> new ArrayList<>());
            elementShowIndexListMap.get(element).add(i);
        }

        Integer maxShowCount = Integer.MIN_VALUE;
        List<Integer> maxShowCountElementList = new ArrayList<>(arr.size());
        for (Map.Entry<Integer, Integer> elementShowCountEntry : elementShowCountMap.entrySet()) {
            Integer element = elementShowCountEntry.getKey();
            Integer showCount = elementShowCountEntry.getValue();
            if (showCount.equals(maxShowCount)) {
                maxShowCountElementList.add(element);
                continue;
            }
            if (showCount >= maxShowCount) {
                maxShowCount = showCount;
                maxShowCountElementList = new ArrayList<>(arr.size());
                maxShowCountElementList.add(element);
            }
        }

        Integer shortestSubArrayLength = Integer.MAX_VALUE;
        for (Integer element : maxShowCountElementList) {
            List<Integer> elementShowIndexList = elementShowIndexListMap.get(element);
            Integer firstIndex = elementShowIndexList.get(0);
            Integer lastIndex = elementShowIndexList.get(elementShowIndexList.size() - 1);
            Integer subArrayLength = lastIndex - firstIndex + 1;
            if (subArrayLength < shortestSubArrayLength) {
                shortestSubArrayLength = subArrayLength;
            }
        }

        return shortestSubArrayLength;
    }

    public static int degreeOfArray2(List<Integer> arr) {
        // 使用Map来记录每个元素的出现次数
        Map<Integer, Integer> countMap = new HashMap<>();
        // 使用Map来记录每个元素第一次出现的位置
        Map<Integer, Integer> firstIndexMap = new HashMap<>();
        // 使用Map来记录每个元素最后一次出现的位置
        Map<Integer, Integer> lastIndexMap = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            int num = arr.get(i);
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (!firstIndexMap.containsKey(num)) {
                firstIndexMap.put(num, i);
            }
            lastIndexMap.put(num, i);
        }

        int degree = 0;
        for (int count : countMap.values()) {
            degree = Math.max(degree, count);
        }

        int minLen = arr.size();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (count == degree) {
                int subarrayLen = lastIndexMap.get(num) - firstIndexMap.get(num) + 1;
                minLen = Math.min(minLen, subarrayLen);
            }
        }

        return minLen;
    }
}
