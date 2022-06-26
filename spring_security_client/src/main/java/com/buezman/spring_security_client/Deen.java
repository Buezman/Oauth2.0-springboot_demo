package com.buezman.spring_security_client;

import java.util.Arrays;

public class Deen {
    public static void main(String[] args) {
        String[] arr = {"coderbyte", "3"};
        System.out.println(test(arr));
    }

    public static String test(String[] arr) {
        int row = Integer.parseInt(arr[1]);
        String word = arr[0];
        String[] lines = new String[row];
        Arrays.fill(lines, "");
        boolean forward = true;
        String[] text = word.split("");

        int count = 0;
        for (String s : text) {
            System.out.println(count);
            lines[count] += s;
            if (forward) {
                count++;
                if (count == row) {
                    forward = false;
                    count--;
                }
            }
            if (!forward) {
                count--;
                if (count == 0) forward = true;
            }
        }
        System.out.println(Arrays.toString(lines));

        String interspersed = stringify(lines);
        String token = "abcdef";

        return joinToken(interspersed, token);
    }

    static String stringify(String[] arr) {
        StringBuilder result = new StringBuilder();
        for(String each : arr) result.append(each);
        return result.toString();
    }

    static String joinToken(String str, String token) {
        int a = str.length();
        int b = token.length();
        String longer = a > b ? str : token;
        int min = Math.min(a, b);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < min; i++)
            result.append(str.charAt(i)).append(token.charAt(i));
        result.append(longer.substring(min));
        return result.toString();
    }
}
/*
c    r    e    e   t
 o  e b  t c  d r y e
  d    y    o   b


 */