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

public class FindLowestPrice {

    public static void main(String[] args) {
        int result0 = findLowestPrice(
                Arrays.asList(
                        Arrays.asList("10", "sale", "january-sale"),
                        Arrays.asList("200", "sale", "EMPTY")),
                Arrays.asList(
                        Arrays.asList("sale", "0", "10"),
                        Arrays.asList("january-sale", "1", "10")));
        System.out.println(result0);

        int result1 = findLowestPrice(
                Arrays.asList(
                        Arrays.asList("100", "dcoupon1"),
                        Arrays.asList("50", "dcoupon1"),
                        Arrays.asList("100", "dcoupon2"),
                        Arrays.asList("50", "dcoupon2"),
                        Arrays.asList("30", "dcoupon2"),
                        Arrays.asList("30", "dcoupon1")),
                Arrays.asList(
                        Arrays.asList("dcoupon1", "0", "60"),
                        Arrays.asList("dcoupon1", "1", "30"),
                        Arrays.asList("dcoupon1", "1", "20"),
                        Arrays.asList("dcoupon2", "2", "20"),
                        Arrays.asList("dcoupon2", "1", "85"),
                        Arrays.asList("dcoupon2", "0", "15")));
        System.out.println(result1);
    }

    public static int findLowestPrice0(List<List<String>> products, List<List<String>> discounts) {
        int totalPrice = 0;
        for (List<String> product : products) {
            int price = Integer.parseInt(product.get(0));
            int minPrice = price;
            for (List<String> discount : discounts) {
                if (product.contains(discount.get(0))) {
                    int type = Integer.parseInt(discount.get(1));
                    int amount = Integer.parseInt(discount.get(2));
                    if (type == 0) {
                        minPrice = Math.min(minPrice, amount);
                    } else if (type == 1) {
                        minPrice = Math.min(minPrice, (int) (price * (1 - (amount / 100.0))));
                    } else if (type == 2) {
                        minPrice = Math.min(minPrice, price - amount);
                    }
                }
            }
            totalPrice += minPrice;
        }
        return totalPrice;
    }

    public static int findLowestPrice(List<List<String>> products, List<List<String>> discounts) {
        int allPrice = 0;
        for (List<String> product : products) {
            int rawPrice = Integer.parseInt(product.get(0));
            int currentPrice = rawPrice;
            for (List<String> discount : discounts) {
                if (!product.contains(discount.get(0))) {
                    continue;
                }
                int type = Integer.parseInt(discount.get(1));
                int amount = Integer.parseInt(discount.get(2));
                if (type == 0) {
                    int thisPrice = amount;
                    currentPrice = Math.min(currentPrice, thisPrice);
                } else if (type == 1) {
                    int thisPrice = (int) (rawPrice - rawPrice * (amount / 100D));
                    currentPrice = Math.min(currentPrice, thisPrice);
                } else if (type == 2) {
                    int thisPrice = rawPrice - amount;
                    currentPrice = Math.min(currentPrice, thisPrice);
                }
            }
            allPrice += currentPrice;
        }
        return allPrice;
    }
}
