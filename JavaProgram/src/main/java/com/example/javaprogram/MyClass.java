package com.example.javaprogram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
//        List<Integer> arrList = Arrays.asList(234, 1123, 11, 15, 32, 23, 12, 19);
//            arrList.stream().map(s -> s + "").filter(s -> s.startsWith("1")).forEach(System.out::println);

//        String j="";
//        for(int i=5; i>0; i--) {
//            j=i+j;
//            System.out.println(j);
//
//        }
/////////////////////////////////////////////
        String str;
        int index, len;
        int[] counter = new int[256];

        str = "AnithaAlvekodi";
        len = str.length();

        for (index = 0; index < len; index++) {
            counter[str.toLowerCase().charAt(index)]++;
        }
        for (index = 0; index < 256; index++) {
            if (counter[index] != 0) {
                System.out.println((char) index + " --> " + counter[index]);
            }
        }

        //////////////////////////////////////


        // Converting string into an array for computation
        char[] arr = str.toCharArray();

        // Nested loops for comparison of characters
        // in above character array

        char temp;

        for(int i = 0 ;i<arr.length;i++) {
            for(int j=i+1; j<arr.length; j ++) {
                if (arr[i] > arr[j]) {
                    // Comparing the characters one by one
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(arr);

//        for (int i = 0; i<arr.length;i++) {
//            int count = 1;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] == arr[j]) {
//                    count++;
//                } else {
//                    break;
//                }
//            }
//            System.out.println(arr[i] + "   " + count);
//        }

    }
////////////////////////////////////

}