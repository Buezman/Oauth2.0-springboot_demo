package com.buezman.spring_security_client;

import java.util.*;

public class Kunle {
    public static void main(String[] args) {
        int[][] nested = {{1,2,4}, {2,3,2}, {3,2,1}};

        int[] arr = {4,3,1,1,3,3,2};
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i : arr) {
            if(map.containsKey(i))
                map.put(i, map.get(i)+1);
            else map.put(i, 1);
        }

        double a = 1;
        double b = a/3;

        System.out.println(b);

        System.out.println(Math.pow(3,4));

        String h = "hello";
//        System.out.println(h.substring(3,5));
//
//        System.out.println(myAtoi("   -32"));
//        System.out.println(Arrays.deepToString(sort(nested)));
    }
    static int getMinInMap(Map<Integer, Integer> map) {
        int min = 7;
        int key = -1;
        for (int i : map.keySet()) {
            if (map.get(i) < min) {
                min = map.get(i);
                key = i;
            }
        }
        return key;
    }

    public static int myAtoi(String s) {
            if (s == null) return 0;
            if (s.isEmpty()) return 0;
            while (s.startsWith(" ")) {
                s =  s.replaceFirst(" ", "");
            }
            String[] arr = s.split(" ");
            String use = arr[0];
            String sign = "+-";
            boolean isNegative = use.startsWith("-");
            if (use.isEmpty()) return 0;
            String nums = "0123456789";
            long result = 0;
            int max = Integer.MAX_VALUE;
            int min = Integer.MIN_VALUE;
            int l = use.length()-1;
            int n = 0;

            for (String i : use.split("")) {
                if (!nums.contains(i) && !sign.contains(i)) break;
                if (n > 0 && !nums.contains(i)) break;
                if (nums.contains(i)) {
                    result *= 10;
                    result += Integer.parseInt(i);
                    if (result > max && !isNegative) {
                        result = max;
                        break;
                    } else if (result*-1 < min && isNegative) {
                        result = max + 1;
                        break;
                    }
                }
                n++;

            }
            return (int) (isNegative ? result * -1 : result);
    }


   public static int[][] sort(int[][] arr) {
        int[][] columns = new int[arr.length][arr[0].length];
        int[][] sortedColumn = new int[arr.length][arr[0].length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                columns[i][j] = arr[j][i];
            }
            list.add(getUniqueNo(columns[i]));
        }
       System.out.println(list);
        int count = 0;
        while (count < list.size()) {
            int i = getMin(list);
            System.out.println(i);
            sortedColumn[count] = columns[i];
            list.set(i, 100);
            count++;
        }
       for (int i = 0; i < arr.length; i++) {
           for (int j = 0; j < arr[i].length; j++)
               arr[i][j] = sortedColumn[j][i];
       }

       return arr;
   }
   static int getMin(List<Integer> list) {
        int min = 10;
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
           if(list.get(i) < min) {
               min = list.get(i);
               index = i;
           }
        }
        return index;
   }

   public static int getUniqueNo(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else map.put(i, 1);
        }
        return map.size();
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return intersect(nums2,nums1);

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums1) {
            if (map.containsKey(i))
                map.put(i, map.get(i)+1);
            else map.put(i,1);
        }
        System.out.println(map);

        List<Integer> list = new ArrayList<>();

        for (int i : nums2) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i)-1);
                list.add(i);
                if (map.get(i) == 0) map.remove(i);
            }

        }
        int[] result = new int[list.size()];

        int count = 0;
        for (int i : list) {
            result[count] = i;
            count++;
        }


        return result;
    }
}
