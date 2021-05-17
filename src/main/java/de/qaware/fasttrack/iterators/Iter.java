package de.qaware.fasttrack.iterators;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Iter {

    /**
     * @param last first natural not returned
     * @return a Stream containing naturals from 0 to last - 1
     */
    public static Stream<Integer> counter(int last) {
        Integer[] cnt = {0};
        return Stream.generate(() -> {
            if (cnt[0] < last) {
                cnt[0] += 1;
                return cnt[0];
            } else
                throw new NoSuchElementException();
        });
    }

    public static <T> Stream<Long> faculty() {
        Iterator<Long> t = new Faculty();
        return Stream.generate(t::next);
    }

    public static <T> Stream<Long> faculty1() {
        return Stream.generate(new FacultySupplier());
    }

    public static <T> Stream<T> fun(T arg0, T arg1, BinaryOperator<T> f) {
        return Stream.generate(new Fun<>(arg0, arg1, f));
    }

    public static Stream<Long> fibo() {
        return fun(1L, 1L, Long::sum);
    }

    /**
     * @param x any number > 0 of positive integers, normally primes
     * @return stream of all multiples in ascending order
     */
    public static Stream<Integer> hamming(Integer... x) {
        return Stream.generate(new Hamming(x));
    }

    /**
     * an Iterator returning const indefinitely
     */
    public static class Const implements Iterator<Integer> {

        int value;

        public Const(int value) {
            this.value = value;

        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            return value;
        }
    }

    /**
     * an Iterator returning natural numbers from 0 to last - 1
     */
    public static class Counter implements Iterator<Integer> {

        int last;
        int cnt = 0;

        public Counter(int last) {
            this.last = last;

        }

        @Override
        public boolean hasNext() {
            return cnt < last;
        }

        @Override
        public Integer next() {
            if (cnt < last) {
                return cnt++;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public static class Faculty implements Iterator<Long> {
        int factor = 0;
        long current = 1;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            factor += 1;
            current *= factor;
            return current;
        }
    }

    public static class FacultySupplier implements Supplier<Long> {
        private final Iterator<Long> t = new Faculty();

        @Override
        public Long get() {
            return t.next();
        }
    }

    public static class Fun<T> implements Supplier<T> {

        T a;
        T b;
        BinaryOperator<T> f;
        int cnt = 0;

        public Fun(T arg0, T arg1, BinaryOperator<T> f) {
            this.a = arg0;
            this.b = arg1;
            this.f = f;
        }

        @Override
        public T get() {
            switch (cnt) {
                case 0 -> {
                    cnt += 1;
                    return a;
                }
                case 1 -> {
                    cnt += 1;
                    return b;
                }
                default -> {
                    T c = f.apply(a, b);
                    a = b;
                    b = c;
                    return c;
                }
            }
        }
    }

    /**
     * an exercise attributed to Hamming.
     * Given: a list of integers, often primes
     * return: all multiples of given integers in ascending order
     */
    public static class Hamming implements Supplier<Integer> {

        private final List<Integer> ps;
        private List<Integer> q = new ArrayList<>();

        public Hamming(Integer... x) {
            ps = Arrays.asList(x);
            q.add(1);
        }

        @Override
        public Integer get() {
            int maxq = Collections.max(q);
            int minp = Integer.MAX_VALUE;
            for (int p : ps) {
                for (int x : q) {
                    if (p * x > maxq && p * x < minp) {
                        minp = p * x;
                    }
                }
            }
            q.add(minp);
            return minp;
        }
    }
}
