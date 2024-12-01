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

public class TurnstileGetTimes {

    public static void main(String[] args) {
        List<Integer> result0 = getTimes(Arrays.asList(0, 0, 1, 5), Arrays.asList(0, 1, 1, 0));
        System.out.println(result0);

        List<Integer> result1 = getTimes(Arrays.asList(0, 1, 1, 3, 3), Arrays.asList(0, 1, 0, 0, 1));
        System.out.println(result1);
    }

    public static List<Integer> getTimes(List<Integer> time, List<Integer> direction) {

        Map<Integer, Integer> personIndexPassTime = new HashMap<>(time.size());

        // 上次通过的方向
        int lastPassDirection = -1;
        // 当前时间
        int currentTime = 0;
        // 通过的人数
        int passedPersonCount = 0;
        // 遍历过的索引
        int index = 0;

        // 总等待人的队列
        List<Person> waitingPersons = new LinkedList<>();

        // 当通过的人数小于总人数时，一直进行循环
        while (passedPersonCount < time.size()) {
            // 获取当时间为time时，新等待人的队列
            CurrentTimeAddingWaitingPersonInfo waitingPersonInfo = getCurrentTimeAddingWaitingPersons(index, time, direction, currentTime);
            index += waitingPersonInfo.count;
            // 新等待人加入到总等待人的队列
            waitingPersons.addAll(waitingPersonInfo.list);
            // 选择可以通过的人
            Person person = choosePersonToPass(waitingPersons, lastPassDirection);
            if (person != null) {
                passedPersonCount++;
                personIndexPassTime.put(person.index, currentTime);
                lastPassDirection = person.direction;
            }
            currentTime++;
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < time.size(); i++) {
            result.add(personIndexPassTime.get(i));
        }
        return result;
    }

    private static CurrentTimeAddingWaitingPersonInfo getCurrentTimeAddingWaitingPersons(int index, List<Integer> time, List<Integer> direction, int currentTime) {
        List<Person> addingWaitingPersons = new LinkedList<>();
        int count = 0;
        for (int i = index; i < time.size(); i++) {
            if (time.get(i) == currentTime) {
                count++;
                addingWaitingPersons.add(new Person(i, direction.get(i)));
            } else {
                return new CurrentTimeAddingWaitingPersonInfo(count, addingWaitingPersons);
            }
        }
        return new CurrentTimeAddingWaitingPersonInfo(count, addingWaitingPersons);
    }

    private static Person choosePersonToPass(List<Person> waitingPersons, int lastPassDirection) {
        if (waitingPersons.size() == 0) {
            return null;
        }
        // 上次为进入，则本次进入的先过
        Person p;
        if (lastPassDirection == 0) {
            p = removeAndGet(waitingPersons, 0);
        } else {
            // 否则，本次离开的先过
            p = removeAndGet(waitingPersons, 1);
        }
        if (p != null) {
            return p;
        }
        // 否则，第一个先过
        p = waitingPersons.get(0);
        waitingPersons.remove(0);
        return p;
    }

    private static Person removeAndGet(List<Person> waitingPersons, int lastPassDirection) {
        Iterator<Person> iterator = waitingPersons.iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();
            if (p.direction == lastPassDirection) {
                iterator.remove();
                return p;
            }
        }
        return null;
    }

    private static class CurrentTimeAddingWaitingPersonInfo {
        public int count;
        public List<Person> list;

        public CurrentTimeAddingWaitingPersonInfo(int count, List<Person> list) {
            this.count = count;
            this.list = list;
        }
    }

    private static class Person {
        public int index;
        public int direction;

        public Person(int index, int direction) {
            this.index = index;
            this.direction = direction;
        }
    }
}
