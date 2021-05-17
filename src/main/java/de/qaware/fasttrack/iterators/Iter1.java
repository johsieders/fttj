package de.qaware.fasttrack.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iter1 {
    public static Iterator<Integer> myInter(int n) {
        return new myConst(n);
    }

    public static Iterator myABC() {
        return new abcIter();
    }

    public static Iterator myABC1() {
        return new Iterator() {
            private final String abc = "abc";
            int last = abc.length() - 1;
            int cnt = -1;

            @Override
            public boolean hasNext() {
                return cnt < last;
            }

            @Override
            public Object next() {
                if (cnt < last) {
                    cnt++;
                    return abc.charAt(cnt);
                } else {
                    throw new NoSuchElementException();
                }

            }
        };
    }

    public static void main(String[] args) {
        Iterator<Integer> it = myInter(2);
        System.out.println(it.hasNext());
        System.out.println(it.next());
        Iterator abc = myABC();
        System.out.println(abc.hasNext());
        System.out.println(abc.next());
        System.out.println(abc.next());
        System.out.println(abc.next());

        Iterator abc1 = myABC1();
        System.out.println(abc1.hasNext());
        System.out.println(abc1.next());
        System.out.println(abc1.next());
        System.out.println(abc1.next());
    }

    public static class myConst implements Iterator<Integer> {
        int value;

        public myConst(int value) {
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

    public static class abcIter implements Iterator {
        String abc = "abc";
        int last = abc.length() - 1;
        int cnt = -1;

        public abcIter() {
            this.abc = abc;
        }

        @Override
        public boolean hasNext() {
            return cnt < last;
        }

        @Override
        public Object next() {
            if (cnt + 1 < last) {
                cnt++;
                return abc.charAt(cnt);
            } else {
                throw new NoSuchElementException();
            }

        }
    }

    public static class faculty implements Iterator<Integer> {
        private int current;
        private int factor;

        public faculty() {
            this.current = 1;
            this.factor = 1;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            current *= factor;
            factor++;
            return current;
        }
    }
}
