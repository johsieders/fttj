package de.qaware.fasttrack.warmingup;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.qaware.fasttrack.warmingup.WarmingUp1.*;
import static org.testng.Assert.*;

public class WarmingUpTest1 {

    @Test
    public void testSimpleMethod() {
        assertEquals(simpleMethod(3), 3);
    }

    @Test
    public void testFiboSimon() {
        assertEquals(fiboSimon(0), 0);
        assertEquals(fiboSimon(1), 1);
        assertEquals(fiboSimon(2), 1);
        assertEquals(fiboSimon(3), 2);
    }

    @Test
    public void testFiboSimon1() {
        assertEquals(fiboSimon1(0), 0);
        assertEquals(fiboSimon1(1), 1);
        assertEquals(fiboSimon1(2), 1);
        assertEquals(fiboSimon1(3), 2);
    }

    @Test
    public void testReverse() {
        List<Integer> input_Test1 = new ArrayList<>();
        input_Test1.add(0, 0);
        input_Test1.add(0, 1);
        input_Test1.add(0, 2);

        List<Integer> result_Test1 = new ArrayList<>();
        result_Test1.add(0, 2);
        result_Test1.add(0, 1);
        result_Test1.add(0, 0);

        reverse(input_Test1);
        assertEquals(input_Test1, result_Test1);
    }

    @Test
    public void testNormString() {
        String inputTest1 = "Otto";
        String expectedResult1 = "otto";
        assertEquals(normString(inputTest1), expectedResult1);
    }

    @Test
    public void test_is_palindrome() {
        String input_test1 = "ein Neger mit Gazelle zagt im Regen nie";
        String cleanString = normString(input_test1);
        List<String> temp = Arrays.asList(cleanString.split(""));
        List<String> temp_rev = reverse2(temp);
        System.out.println(temp);
        System.out.println(temp_rev);
        System.out.println(temp.equals(temp_rev));
        System.out.println(temp == temp_rev);
        boolean b = is_palindrome(input_test1);
        assertTrue(b);
    }
}
