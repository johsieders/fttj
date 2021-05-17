// j.siedersleben
// fasttrack to professional programming
// lesson1: warm up
// 15.11.2020

package de.qaware.fasttrack.warmingup;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static de.qaware.fasttrack.warmingup.Warmingup.*;
import static org.testng.Assert.*;


// shorthand for long typename
interface Faculty extends Function<Integer, Long> {
}

public class WarmingupTest {

    @Test
    public void testArrays() {
        Integer[] xs = new Integer[3];
        xs[0] = 1;
        xs[1] = 2;
        xs[2] = 3;
        Integer[] ys = {1, 2, 3};

        List<Integer> zs = Arrays.asList(xs);
        List<Integer> rs = Arrays.asList(ys);
        Integer[] ts = zs.toArray(new Integer[0]);

        Stream<Integer[]> stm1 = Stream.of(xs, ys, ts);
        Stream<List<Integer>> stm2 = Stream.of(rs, zs);

        stm1.forEach((xx) -> {
            for (int x : xx) {
                System.out.println(x);
            }
        });
        // stm1.forEach(System.out::println);    doesn't work
        stm2.forEach(System.out::println);
    }

    @Test
    public void testPowerOf2() {
        for (int n = 0; n < 10; n++) {
            int k = (int) Math.pow(2, n);
            assertTrue(powerOf2(k));
        }
        for (int n = 1025; n < 1035; n++) {
            assertFalse(powerOf2(n));
        }
    }


    @Test
    public void testFaculty() {
        List<Faculty> fs = Arrays.asList(Warmingup::faculty, Warmingup::faculty1);

        for (Faculty f : fs) {
            boolean caught = false;
            try {
                f.apply(-1);
            } catch (IllegalArgumentException e) {
                caught = true;
            }
            assertTrue(caught);
            assertEquals(f.apply(0), 1, 0);
            assertEquals(f.apply(1), 1, 0);
            assertEquals(f.apply(2), 2, 0);
            assertEquals(f.apply(3), 6, 0);
            assertEquals(f.apply(4), 24, 0);
            assertEquals(f.apply(5), 120, 0);
        }
    }


    public int fibo(int n) {
        return Warmingup.fibo(n);
    }

    @Test
    public void testFibo() {
        boolean caught = false;
        try {
            fibo(-1);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught);
        assertEquals(fibo(0), 0);
        assertEquals(fibo(1), 1);
        assertEquals(fibo(2), 1);
        assertEquals(fibo(3), 2);
        assertEquals(fibo(4), 3);
        assertEquals(fibo(5), 5);
    }


    @Test
    public void testGcd() {
        assertEquals(gcd(0, 0), 0);
        assertEquals(gcd(0, 1), 1);
        assertEquals(gcd(1, 0), 1);
        assertEquals(gcd(1, 1), 1);
        assertEquals(gcd(20, 8), 4);
        assertEquals(gcd(8, 20), 4);
    }


    @Test
    public void testPalindrome() {
        String s = normstring("ein Neger mit Gazelle zagt im Regen nie");
        // System.out.println(s);
        boolean b = palindrome(s);
        assertTrue(b);
    }
}