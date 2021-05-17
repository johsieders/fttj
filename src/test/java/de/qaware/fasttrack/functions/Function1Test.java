package de.qaware.fasttrack.functions;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static de.qaware.fasttrack.functions.Functions1.*;

public class Function1Test {
    /**
     * testStringComparatorDefault sorts a list of strings using a already default
     * implemented Interface Comparator called Collator. Secondly, the a list
     * is sorted using the newly implemented private class called StringComparator
     * of class Functions1.
     */
    @Test
    public static void testSortByDefault() {
        List<String> strings = Arrays.asList("Xaver", "Adam", "Lutz");
        sortByDefault(strings);
        System.out.println(strings);
    }

    @Test
    public static void testSortByLetter() {
        List<String> strings = Arrays.asList("Xaver", "Adam", "Lutz");
        sortByLetter(strings, 1);
        System.out.println(strings);
    }

    @Test
    public static void testSortByLetter1() {
        List<String> strings = Arrays.asList("Xaver", "Adam", "Lutz");
        sortByLetter1(strings, 1);
        System.out.println(strings);
    }

    @Test
    public static void testSortByLetter2() {
        List<String> strings = Arrays.asList("Xaver", "Adam", "Lutz");
        sortByLetter2(strings, 1);
        System.out.println(strings);
    }

    @Test
    public static void testSortByLetter3() {
        List<String> strings = Arrays.asList("Xaver", "Adam", "Lutz");
        sortByLetter3(strings, 1);
        System.out.println(strings);
    }

    // ///////////////////////
    //  Playing with Functions
    // ///////////////////////
    @Test
    public static void testAddFunction() {
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x + 2;
        Function<Integer, Integer> h = addFunction(f, g);
        System.out.println(h.apply(10));
    }

    @Test
    public static void testAddFunction1() {
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x + 2;
        Function<Integer, Integer> h = addFunction1(f, g);
        System.out.println(h.apply(10));
    }

    @Test
    public static void testAddFunction2() {
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x + 2;
        Function<Integer, Integer> h = addFunction2(f, g);
        System.out.println(h.apply(10));
    }

    @Test
    public static void testCurry() {
        BiFunction<Integer, Integer, Integer> f = (x, y) -> x + y + 1;
        int c = 1;
        Function<Integer, Integer> h = curry(f, c);
        System.out.println(h.apply(10));
    }

    @Test
    public static void testFlip() {
        BiFunction<Integer, Integer, Integer> f = (x, y) -> x / y;
        BiFunction<Integer, Integer, Integer> h = flip(f);
        System.out.println((f.apply(2, 1)));
        System.out.println(h.apply(2, 1));
    }

    @Test
    public static void testMultiplyAllElements() {
        List<Integer> xs = Arrays.asList(1, 2, 3);
        int result = multiplyAllElements(xs);
        System.out.println(result);
    }

    @Test
    public static void testTimes2() {
        List<Integer> xs = Arrays.asList(1, 2, 3);
        times2(xs);
        System.out.println(xs);
    }

    @Test
    public static void testHorner() {
        List<Double> cs = Arrays.asList(2.0, 3.0, 1.0);
        float x = 0;
        Double result = horner(cs, x);
        System.out.println(result);
    }
}
