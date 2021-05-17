package de.qaware.fasttrack.functions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.qaware.fasttrack.warmingup.Warmingup.normstring;


public class Functions {

    ///////////////////////////////
    // Sorting by letter
    //////////////////////////////

    /**
     * @param strings a list of strings
     * @param n       integer smaller than the shortest string
     *                Solution 2: using a named class
     */
    public static void sortByLetter2(List<String> strings, int n) {
        strings.sort(new StringComparator(n));
    }

    /**
     * @param strings a list of strings
     * @param n       integer smaller than the shortest string
     *                Solution1: using an anonymous class
     */
    public static void sortByLetter1(List<String> strings, int n) {
        strings.sort(new Comparator<>() {
            @Override
            public int compare(String s, String t) {
                return s.charAt(n) - t.charAt(n);
            }
        });
    }

    /**
     * @param strings a list of strings
     * @param n       integer smaller than the shortest string
     *                Standard solution lambda-like
     */
    public static void sortByLetter(List<String> strings, int n) {
        strings.sort((s, t) -> s.charAt(n) - t.charAt(n));
    }

    public static String anagram_key1(String s) {
        String t = normstring(s);
        char[] chars = t.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    ///////////////////////////////
    // Anagrams
    //////////////////////////////

    public static String anagram_key2(String s) {
        String t = normstring(s);
        char[] chars = t.toCharArray();
        Arrays.sort(chars);
        return new String(chars) + s;
    }

    /**
     * @param strings a list of strings
     *                strings is grouped by anagrams in on place
     *                Standard solution using lambda
     */
    public static void anagram_sort(List<String> strings) {
        strings.sort((s, t) -> {
                    s = anagram_key2(s);
                    t = anagram_key2(t);
                    return s.compareTo(t);
                }
        );
    }

    /**
     * @param strings a list of strings
     *                strings is grouped by anagrams in on place
     *                Non-standard solution using an anonymous class
     */
    public static void anagram_sort1(List<String> strings) {
        strings.sort(new Comparator<>() {
            @Override
            public int compare(String s, String t) {
                s = anagram_key1(s);
                t = anagram_key1(t);
                return s.compareTo(t);
            }
        });
    }

    /**
     * @param f   a function returning Integer
     * @param g   another function returning Integer
     * @param <T> any type
     * @return a function which returns f + g
     * Solution 2: using a named class
     */
    public static <T> Function<T, Integer> addFun2(Function<T, Integer> f, Function<T, Integer> g) {
        return new AddFunction<>(f, g);
    }


    ///////////////////////////////
    // PLaying with Functions
    //////////////////////////////

    /**
     * @param f   a function returning Integer
     * @param g   another function returning Integer
     * @param <T> any type
     * @return a function which returns f + g
     * Solution 1: using an anonymous class
     */
    public static <T> Function<T, Integer> addFun1(Function<T, Integer> f, Function<T, Integer> g) {
        return new Function<>() {
            @Override
            public Integer apply(T x) {
                return f.apply(x) + g.apply(x);
            }
        };
    }

    /**
     * @param f   a function returning Integer
     * @param g   another function returning Integer
     * @param <T> any type
     * @return a function which returns f + g
     * Standard solution: using lambda
     */
    public static <T> Function<T, Integer> addFun(Function<T, Integer> f, Function<T, Integer> g) {
        return (x) -> f.apply(x) + g.apply(x);
    }

    /**
     * @param f   a function
     * @param g   another function
     * @param <R> any type
     * @param <V> any type
     * @param <T> any type
     * @return composition of f and g
     * compare this to Function.compose
     */
    public static <R, V, T> Function<V, R> compose(Function<? super T, ? extends R> f,
                                                   Function<? super V, ? extends T> g) {
        return (x) -> f.apply(g.apply(x));
    }

    public static Integer multiplyAll(List<Integer> xs) {

        Stream<Integer> stm = xs.stream();
        return stm.reduce(1, (a, b) -> a * b);
    }

    public static IntStream times2(List<Integer> xs) {

        Stream<Integer> stm = xs.stream();
        return stm.mapToInt((x) -> 2 * x);
    }


    // todo: curry, flip


    ///////////////////////////////
    // Map and Reduce
    //////////////////////////////

    private static class StringComparator implements Comparator<String> {
        private final int n;

        StringComparator(int n) {
            this.n = n;
        }

        @Override
        public int compare(String s, String t) {
            return s.charAt(n) - t.charAt(n);
        }
    }

    private static class AddFunction<T> implements Function<T, Integer> {
        private final Function<T, Integer> f;
        private final Function<T, Integer> g;

        AddFunction(Function<T, Integer> f, Function<T, Integer> g) {
            this.f = f;
            this.g = g;
        }

        @Override   // __call___(self, x)       f(x) dasselbe wie f.apply(x)
        public Integer apply(T x) {
            return f.apply(x) + g.apply(x);
        }
    }
}
