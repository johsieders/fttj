package de.qaware.fasttrack.functions;



import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static de.qaware.fasttrack.functions.Functions.*;

public class FunctionsTest {
    @Test
    public void testSorting() {
        List<String> strings = Arrays.asList("otto", "rudi", "anna", "sepp");
        sortByLetter(strings, 2);
        System.out.println(strings);
    }

    @Test
    public void testAnagram() {
        List<String> strings = Arrays.asList("otto", "nana", "anna", "toto");
        anagram_sort(strings);
        System.out.println(strings);

        strings = Arrays.asList("otto", "nana", "anna", "toto");
        anagram_sort1(strings);
        System.out.println(strings);
    }

    @Test
    public void testCompose() {
        Function<Integer, Integer> f = (x) -> x + 5;
        Function<Integer, Integer> g = (x) -> x * 8;
        Function<Integer, Integer> h = compose(f, g);
        System.out.println(h.apply(10));

        Function<Integer, Integer> k = f.compose(g);
        System.out.println(k.apply(10));
    }

    @Test
    public void testReduce() {
        List<Integer> xs = Arrays.asList(2, 3, 5);
        int m = multiplyAll(xs);
        System.out.println(m);
    }

    @Test
    public void testMap() {
        List<Integer> xs = Arrays.asList(2, 3, 5);
        IntStream stm = times2(xs);
        stm.forEach(System.out::println);
    }
}
