package wooter.hacker.rank;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;

@Deprecated
public class DecryptPassword {

    /*
     * Complete the 'decryptPassword' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String decryptPassword(String str) {
        List<Character> charList = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        int i = charList.size() - 1;
        int k = 0;
        while (i >= 0) {
            if (charList.get(i) == '#') {
                i--;
                continue;
            }
            if (charList.get(i) == '0') {
                charList.set(i, charList.get(k));
                charList.set(0, '#');
                i--;
                k++;
                continue;
            }
            if (charList.get(i) == '*') {
                charList.set(i, '#');
                Character temp = charList.get(i - 1);
                charList.set(i - 1, charList.get(i - 2));
                charList.set(i - 2, temp);
                i = i - 3;
                continue;
            }
            i--;
        }
        String result = charList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        return result.replaceAll("#", "");
    }

    public static void main(String[] args) {
        String result = decryptPassword("51Pa*0Lp*0e");
        System.out.println(result);
    }

}

