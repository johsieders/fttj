package de.qaware.fasttrack.sudoku;

import de.qaware.fasttrack.searching.*;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuStreamTest {
    String test0 = "8 0 0   0 3 0   0 2 6" +
            "1 5 0   4 0 0   8 0 0" +
            "0 0 0   0 0 8   9 0 4" +
            "9 0 0   0 4 0   2 0 5" +
            "5 0 8   0 0 6   0 0 1" +
            "6 0 0   0 0 0   7 9 0" +
            "0 0 0   9 1 5   0 0 0" +
            "2 0 6   3 0 0   0 8 9" +
            "7 0 0   0 0 0   0 1 0";

    String test1 = "8 4 0   5 3 7   1 2 6" +
            "0 5 2   4 6 9   8 3 7" +
            "3 6 7   1 2 8   9 5 4" +
            "9 7 0   8 4 3   2 6 5" +
            "5 2 8   7 9 6   3 4 1" +
            "6 3 4   2 5 1   7 9 8" +
            "4 8 3   9 1 5   6 7 2" +
            "2 1 6   3 7 4   5 8 6" +
            "7 9 5   6 8 2   4 1 3";

    String test2 = "8 4 -    5 3 7    - 2 6" +
            "- 5 2    4 6 9    8 3 7" +
            "3 6 7    1 2 8    9 5 4" +
            "9 7 -    8 4 3    2 6 5" +
            "5 2 8    7 9 6    3 4 1" +
            "6 3 4    2 5 1    7 9 8" +
            "4 8 3    9 1 5    6 7 2" +
            "2 1 6    3 7 4    5 8 6" +
            "7 9 5    6 8 2    4 1 3";

    String stern23bonsai = "- - 9   5 - -   8 1 7" +
            "- 3 -   - 8 -   6 - 9" +
            "8 - -   - - -   2 3 4" +
            "6 - -   3 1 5   - - -" +
            "- 9 -   8 - 2   - 6 -" +
            "- - -   7 6 9   - - 2" +
            "3 8 1   - - -   - - 5" +
            "9 - 5   - 2 -   - 8 -" +
            "4 6 2   - - 8   9 - -";

    List<String> sudokusToSolve = Arrays.asList(test0, test1, test2, stern23bonsai);
    @Test
    public void testSudokuStream() {
        SudokuStream problem = new SudokuStream(test2);
        Stream<Step> steps = problem.getSteps();
        List<Step> mySteps = steps.limit(3).collect(Collectors.toList());
        mySteps.forEach(System.out::println);
    }

    @Test
    public void testSearchByStream() {
        SearchProblemStream<SudokuStream.State, Step> problem = new SudokuStream(test2);
        SearchByStream<SudokuStream.State, Step> searcher = new SearchByStream<>(problem);
        List<SudokuStream.State> result = searcher.search();
        for (SudokuStream.State state : result) {
            System.out.println(state);
            System.out.println("__________________");
        }
    }
}
