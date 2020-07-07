package wooter.lamda;

import java.util.*;

public class Reduce {

    public static void main(String[] args) {
        Reduce test = new Reduce();
        test.reduceSimulation();
    }

    public void add() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7};
        int r = Arrays.stream(intArray).reduce(0, (a, b) -> a + b);
        System.out.println(r);
    }

    public void min() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7};
        Optional<Integer> r = Arrays.stream(intArray).reduce((a, b) -> a < b ? a : b);
        System.out.println(r.get());
    }

    public void increaseSegment() {
        Integer[] intArray = {9, 6, 8, 1, 4, 9, 10, 2, 3, 9};
        List<Integer> result = new ArrayList<>();
        result.add(intArray[0]);
        Arrays.stream(intArray).reduce((a, b) -> {
            if (a < b) {
                result.add(b);
            } else {
                System.out.println(result);
                result.clear();
                result.add(b);
            }
            return b;
        });
        System.out.println(result);
    }

    public void increaseSegmentAtLeastCertainConsecutive(int certainConsecutive) {
        Integer[] intArray = {9, 6, 8, 1, 4, 9, 10, 2, 3, 9};
        List<Integer> result = new ArrayList<>();
        result.add(intArray[0]);
        Arrays.stream(intArray).reduce((a, b) -> {
            if (a < b) {
                result.add(b);
            } else {
                if (result.size() >= certainConsecutive) {
                    System.out.println(result);
                }
                result.clear();
                result.add(b);
            }
            return b;
        });
        if (result.size() >= certainConsecutive) {
            System.out.println(result);
        }
    }

    public void increaseSegmentAtLeastCertainConsecutiveAndShowOnceConditioned(int certainConsecutive) {
        Integer[] intArray = {9, 6, 8, 1, 4, 9, 10, 2, 3, 9, 9, 9};
        List<Integer> result = new ArrayList<>();
        result.add(intArray[0]);
        Arrays.stream(intArray).reduce((a, b) -> {
            if (a < b) {
                result.add(b);
                if (result.size() >= certainConsecutive) {
                    System.out.println(result);
                }
            } else {
                result.clear();
                result.add(b);
            }
            return b;
        });
    }

    public void reduceSimulation() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> r = Arrays.stream(intArray).reduce(
                new ArrayList<>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                }, (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
        System.out.println(r);
    }
}
