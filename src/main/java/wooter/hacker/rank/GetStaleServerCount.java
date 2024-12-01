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

public class GetStaleServerCount {

    public static void main(String[] args) {
        List<Integer> result0 = getStaleServerCount(3, Arrays.asList(Arrays.asList(3, 3), Arrays.asList(2, 6), Arrays.asList(1, 5)), Arrays.asList(10, 11), 5);
        System.out.println(result0);

        List<Integer> result1 = getStaleServerCount0(6, Arrays.asList(Arrays.asList(3, 2), Arrays.asList(4, 3), Arrays.asList(2, 6), Arrays.asList(6, 3)), Arrays.asList(3, 2, 6), 2);
        System.out.println(result1);
    }

    public static List<Integer> getStaleServerCount(int n, List<List<Integer>> log_data, List<Integer> query, int x) {
        List<Integer> result = new ArrayList<>();
        for (int q : query) {
            Set<Integer> receivedSet = new HashSet<>();
            Set<Integer> allServersSet = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                allServersSet.add(i);
            }
            for (List<Integer> log : log_data) {
                int serverId = log.get(0);
                int time = log.get(1);
                if (time >= q - x && time <= q) {
                    receivedSet.add(serverId);
                }
            }
            int count = allServersSet.size() - receivedSet.size();
            result.add(count);
        }
        return result;
    }

    public static List<Integer> getStaleServerCount0(int n, List<List<Integer>> log_data, List<Integer> query, int x) {
        List<Integer> notReceiveCountList = new ArrayList<>(n);
        for (int q : query) {
            Map<Integer, Boolean> receiveMap = new HashMap<>(n);
            for (List<Integer> one_log : log_data) {
                if (one_log.get(1) <= q && one_log.get(1) >= q - x) {
                    receiveMap.put(one_log.get(0), true);
                }
            }
            int notReceiveCount = 0;
            for (int i = 1; i <= n; i ++) {
                if (!receiveMap.containsKey(i)) {
                    notReceiveCount++;
                }
            }
            notReceiveCountList.add(notReceiveCount);
        }
        return notReceiveCountList;
    }
}
