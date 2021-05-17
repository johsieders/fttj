// j.siedersleben
// fasttrack to professional programming
// lesson1: warm up
// 15.11.2020

package de.qaware.fasttrack.warmingup;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class Warmingup {

    /**
     * @param n: an integer >= 0
     * @return true if n is a power of twotrue if n is a power of two
     */
    public static boolean powerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * @param n an integer >= 0
     * @return (number of binary digits of n) - 1
     * So: log2(1) = 0, log2(2) = 1, log2(3) = 1
     */
    public static int log2(int n) {
        if (n < 0)
            throw new IllegalArgumentException();

        int result = -1;
        while (n > 0) {
            n >>= 1;
            result += 1;
        }
        return result;
    }

    /**
     * @param u: half open interval (u0, u1)
     * @param v: half open interval (v0, v1)
     * @return intersection of u and v
     * Convention: an interval u is empty iff u[0] >= u[1]
     */
    public static List<Integer> intersection(List<Integer> u, List<Integer> v) {
        return Arrays.asList(max(u.get(0), v.get(0)), min(u.get(1), v.get(1)));
    }

    /**
     * @param n integer >= 0
     * @return faculty of n
     * obvious solution
     */
    public static long faculty(int n) {
        if (n < 0)
            throw new IllegalArgumentException();

        long result = 1;
        for (int i = 1; i < n + 1; i++) {
            result *= i;
        }
        return result;
    }


    /**
     * @param n integer >= 0
     * @return faculty of n
     * recursive solution
     */
    public static long faculty1(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        else if (n <= 1) {
            return 1;
        } else {
            return n * faculty1(n - 1);
        }
    }


    /**
     * @param n integer >= 0
     * @return n-th Fibonacci number
     * naive solution
     */
    public static int fibo1(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        else if (n == 0)
            return 0;
        else {
            List<Integer> result = new ArrayList<>(n + 1);
            result.add(0);
            result.add(1);
            for (int i = 2; i < n + 1; i++) {
                int next = result.get(i - 2) + result.get(i - 1);
                result.add(next);
            }
            int last = result.size() - 1;
            return result.get(last);
        }
    }

    /**
     * @param n integer >= 0
     * @return n-th Fibonacci number
     * standard solution
     */
    public static int fibo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return 0;
        } else {
            int a = 0;
            int b = 1;
            for (int i = 2; i < n + 1; i++) {
                int c = b;
                b = a + b;
                a = c;
            }
            return b;
        }
    }

    /**
     * @param n integer >= 0
     * @return n-th Fibonacci number
     * recursive programming, cool and slow
     */
    public static int fibo2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n <= 1) {
            return n;
        } else {
            return fibo2(n - 2) + fibo2(n - 1);
        }
    }


    /**
     * @param a an integer
     * @param b an integer
     * @return greatest common divisor of a and b
     * standard solution
     */
    public static long gcd0(long a, long b) {
        while (b != 0) {
            long c = b;
            b = a % b;
            a = c;
        }
        return a;
    }

    /**
     * @param a an integer
     * @param b an integer
     * @return greatest common divisor of a and b
     * recursive solution
     */
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
     * @param n integer >= 0
     * @param x float
     * @return x**n / n!  (nth coefficient of taylor series of exp_taylor)
     * recursive solution
     */
    public static double exp_coeff(int n, double x) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1) {
            return 1;
        } else {
            return exp_coeff(n - 1, x) * x / n;
        }
    }


    /**
     * @param xs  a list
     * @param <T> any type
     *            This function reverses the order of xs
     */
    public static <T> List<T> reverse1(List<T> xs) {
        if (xs.size() <= 1) {
            return new ArrayList<>(xs);
        } else {
            List<T> result = new ArrayList<>();
            result.add(xs.get(xs.size() - 1));
            result.addAll(reverse1(xs.subList(1, xs.size() - 1)));
            result.add(xs.get(0));
            return result;
        }
    }

    /**
     * @param xs  a list
     * @param <T> any type
     *            This function reverses the order of xs
     */
    public static <T> void reverse(List<T> xs) {
        int m = xs.size() / 2;
        for (int i = 0; i < m; i++) {
            T aux;
            int j = xs.size() - i;
            aux = xs.get(i);
            xs.set(i, xs.get(j));
            xs.set(j, aux);
        }
    }

    public static boolean isAscii(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    /**
     * @param s a string
     * @return s with non ASCII-charaters stripped, all lowercase
     */

    public static String normstring(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isAscii(c)) {
                result.append(c);
            }
        }
        return new String(result).toLowerCase();
    }

    public static boolean palindrome(String s) {
        List<String> t = Arrays.asList(s.split(""));
        List<String> r = reverse1(t);
        return r.equals(t);
    }
}
