package de.qaware.fasttrack.functions;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static de.qaware.fasttrack.warmingup.WarmingUp1.reverse1;

public class Functions1 {
    public static void sortByDefault(List<String> strings) {
        Collator myCollator = Collator.getInstance();
        strings.sort(myCollator);
    }

    public static void sortByLetter(List<String> strings, int n) {
        strings.sort(new StringComparator(n));
    }

    public static void sortByLetter1(List<String> strings, int n) {
        strings.sort(new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(n) - o2.charAt(n);
            }
        });
    }

    public static void sortByLetter2(List<String> strings, int n) {
        strings.sort((s, t) -> s.charAt(n) - t.charAt(n));
    }

    public static void sortByLetter3(List<String> strings, int n) {
        strings.sort(Comparator.comparingInt((s) -> s.charAt(n)));
    }

    public static <T> Function<T, Integer> addFunction(Function<T, Integer> f, Function<T, Integer> g) {
        return new AddFunction<>(f, g);
    }

    public static <T> Function<T, Integer> addFunction1(Function<T, Integer> f, Function<T, Integer> g) {
        return new Function<T, Integer>() {
            @Override
            public Integer apply(T t) {
                return f.apply(t) + g.apply(t);
            }
        };
    }

    public static <T> Function<T, Integer> addFunction2(Function<T, Integer> f, Function<T, Integer> g) {
        return (x) -> f.apply(x) + g.apply(x);
    }

    public static <T, U> Function<T, Integer> curry(BiFunction<T, U, Integer> f, U x) {
        return (y) -> f.apply(y, x);
    }

    public static <T, U> BiFunction<U, T, Integer> flip(BiFunction<T, U, Integer> f) {
        return (x, y) -> f.apply(y, x);
    }

    //////////////////////////////////////////
    // Working with map and reduce in Java //
    //////////////////////////////////////////
    public static Integer multiplyAllElements(List<Integer> xs) {
        Stream<Integer> st = xs.stream();
        int result = st.reduce(1, (a, b) -> a * b);
        return result;
    }

    public static void times2(List<Integer> xs) {
        Stream<Integer> st = xs.stream();
        xs.stream().mapToInt((x) -> 2 * x);
    }

    /**
     * @param x, the point that we are interested in
     * @param cs a list of coefficients for representing the Horner-scheme
     *           f(x) = cs.get(0) + cs.get(1) x + cs.get(2) x**2 + ...
     * @return the value of f(x)
     */
    public static Double horner(List<Double> cs, float x) {
        List<Double> aux = reverse1(cs);
        Stream<Double> st = aux.stream();
        return st.reduce(0.0, (s, t) -> s * x + t);
    }

    ///////////////////////////////
    // Sorting by letter
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

    // ///////////////////////
    //  Playing with Functions
    // ///////////////////////
    private static class AddFunction<T> implements Function<T, Integer> {
        private final Function<T, Integer> f;
        private final Function<T, Integer> g;

        AddFunction(Function<T, Integer> f, Function<T, Integer> g) {
            this.f = f;
            this.g = g;
        }

        @Override
        public Integer apply(T x) {
            return f.apply(x) + g.apply(x);
        }
    }
}
