package de.qaware.fasttrack.warmingup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarmingUp1 {
    /**
     * this just a test method
     *
     * @param n any integer
     * @return n
     */
    public static int simpleMethod(int n) {
        return n;
    }

    public static int fiboSimon(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        } else {
            List<Integer> result = new ArrayList<>(n + 1);
            result.add(0, 0);
            result.add(1, 1);
            for (int i = 2; i < n + 1; i++) {
                result.add(result.get(i - 2) + result.get(i - 1));
            }
            int last = result.size() - 1;
            return result.get(last);
        }

    }

    public static int fiboSimon1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        } else {
            int a = 0;
            int b = 1;
            int c;
            for (int i = 2; i < n + 1; i++) {
                c = b;
                b = a + b;
                a = c;
            }
            return b;
        }
    }

    /**
     * @param c, char
     * @return True if c is a letter of the ABC, otherwise False
     */
    public static boolean isAscii(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    /**
     * @param s, a string
     * @return a normstring, i.e. only letters from a-z are accepted and
     * returned in lower case letters
     */
    public static String normString(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isAscii(c)) {
                result.append(c);
            }
        }
        return result.toString().toLowerCase();
    }

    /**
     * a on place reverse method
     *
     * @param xs   of any type
     * @param <T>, any type
     */
    public static <T> void reverse(List<T> xs) {
        if (xs.size() <= 1) {
            return;
        }
        int last = xs.size() - 1;
        int m = xs.size() / 2;
        for (int i = 0; i < m; i++) {
            T temp = xs.get(i);
            xs.set(i, xs.get(last - i));
            xs.set(last - i, temp);
        }
    }

    /**
     * a not on place reverse method
     *
     * @param xs of any type
     */
    public static <T> List<T> reverse1(List<T> xs) {
        if (xs.size() <= 1) {
            return new ArrayList<>(xs);
        }
        int last = xs.size() - 1;
        List<T> result = new ArrayList<>();
        for (int i = last; i >= 0; i--) {
            result.add(xs.get(i));
        }
        return result;
    }

    /**
     * this is a wrapper around reverse to have a not in place method
     *
     * @param xs  list of any type
     * @param <T> any type
     * @return a reversed list of xs, by use of reverse
     */
    public static <T> List<T> reverse2(List<T> xs) {
        if (xs.size() <= 1) {
            return new ArrayList<>(xs);
        }
        List<T> xs_copy = new ArrayList<>(xs);
        reverse(xs_copy);
        return xs_copy;
    }

    /**
     * @param s, a string
     * @return True in case s is a palindrome, else -> False
     */
    public static boolean is_palindrome(String s) {
        String cleanString = normString(s);
        List<String> temp = Arrays.asList(cleanString.split(""));
        List<String> temp_rev = reverse2(temp);
        return temp.equals(temp_rev);
    }

    public static void main(String[] args) {
        List<Integer> testList = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> xs_rev = reverse2(testList);
        System.out.println(xs_rev);
    }

}
