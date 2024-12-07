package wooter.hacker.rank.deliveryManagementSystem;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<Integer> order0(int cityNodes, List<Integer> cityFrom, List<Integer> cityTo, int company) {
        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= cityNodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < cityFrom.size(); i++) {
            graph.get(cityFrom.get(i)).add(cityTo.get(i));
            graph.get(cityTo.get(i)).add(cityFrom.get(i));
        }

        // 计算距离
        int[] distance = new int[cityNodes + 1];
        Arrays.fill(distance, -1);
        distance[company] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(company);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int neighbor : graph.get(cur)) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[cur] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // 存储城市及其距离
        List<int[]> cities = new ArrayList<>();
        for (int i = 1; i <= cityNodes; i++) {
            if (distance[i] != -1) {
                cities.add(new int[]{i, distance[i]});
            }
        }

        // 排序城市
        cities.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // 返回排序后的城市编号
        List<Integer> result = new ArrayList<>();
        for (int[] city : cities) {
            result.add(city[0]);
        }
        return result;
    }

    public static List<Integer> order(int cityNodes, List<Integer> cityFrom, List<Integer> cityTo, int company) {
        List<List<Integer>> neighborList = new ArrayList<>();
        for (int i = 0; i <= cityNodes; i++) {
            neighborList.add(new ArrayList<>());
        }
        for (int i = 0; i < cityFrom.size(); i++) {
            neighborList.get(cityFrom.get(i)).add(cityTo.get(i));
            neighborList.get(cityTo.get(i)).add(cityFrom.get(i));
        }

        System.out.println(neighborList);

        List<Integer> distanceList = new ArrayList<>();
        for (int i = 0; i <= cityNodes; i++) {
            if (i == company) {
                distanceList.add(0);
            } else {
                distanceList.add(-1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(company);
        while (!queue.isEmpty()) {
            int nowIndex = queue.poll();
            int nowDistance = distanceList.get(nowIndex);
            for (int neighbor : neighborList.get(nowIndex)) {
                if (distanceList.get(neighbor) == -1) {
                    Integer neighborDistance = nowDistance + 1;
                    distanceList.set(neighbor, neighborDistance);
                    queue.add(neighbor);
                }
            }
        }

        List<City> cityList = new ArrayList<>();
        for (int i = 0; i <= cityNodes; i++) {
            if (distanceList.get(i) != -1) {
                cityList.add(new City(i, distanceList.get(i)));
            }
        }

        cityList.sort((x, y) -> {
            if (!x.distance.equals(y.distance)) {
                return x.distance - y.distance;
            }
            return x.index - y.index;
        });

        List<Integer> orderedCityIndex = cityList.stream().map(e -> e.index).collect(Collectors.toList());
        return orderedCityIndex.subList(1, orderedCityIndex.size());
    }

    public static class City {
        public Integer index;
        public Integer distance;

        public City(Integer index, Integer distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        List<Integer> orders1 = order(4, Arrays.asList(1, 2, 2), Arrays.asList(2, 3, 4), 1);
        System.out.println(orders1);

        List<Integer> orders2 = order(5, Arrays.asList(1, 1, 2, 3, 1), Arrays.asList(2, 3, 4, 5, 5), 1);
        System.out.println(orders2);

        List<Integer> orders3 = order(3, Arrays.asList(1), Arrays.asList(2), 2);
        System.out.println(orders3);

        List<Integer> orders4 = order(12, Arrays.asList(1, 2, 3, 1, 4, 3), Arrays.asList(2, 3, 6, 4, 3, 5), 1);
        System.out.println(orders4);
    }
}
