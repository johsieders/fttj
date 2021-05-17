package de.qaware.fasttrack.sudoku;

import de.qaware.fasttrack.searching.SearchByList;
import de.qaware.fasttrack.searching.SearchProblemList;
import de.qaware.fasttrack.searching.Sudoku;
import de.qaware.fasttrack.searching.SudokuList;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;


public class SudokuTest {

    String test0 =
            "8 0 0   0 3 0   0 2 6" +
            "1 5 0   4 0 0   8 0 0" +
            "0 0 0   0 0 8   9 0 4" +
            "9 0 0   0 4 0   2 0 5" +
            "5 0 8   0 0 6   0 0 1" +
            "6 0 0   0 0 0   7 9 0" +
            "0 0 0   9 1 5   0 0 0" +
            "2 0 6   3 0 0   0 8 9" +
            "7 0 0   0 0 0   0 1 0";

    String test1 =
            "8 4 0   5 3 7   1 2 6" +
            "0 5 2   4 6 9   8 3 7" +
            "3 6 7   1 2 8   9 5 4" +
            "9 7 0   8 4 3   2 6 5" +
            "5 2 8   7 9 6   3 4 1" +
            "6 3 4   2 5 1   7 9 8" +
            "4 8 3   9 1 5   6 7 2" +
            "2 1 6   3 7 4   5 8 6" +
            "7 9 5   6 8 2   4 1 3";

    String test2 =
            "8 4 -    5 3 7    - 2 6" +
            "- 5 2    4 6 9    8 3 7" +
            "3 6 7    1 2 8    9 5 4" +
            "9 7 -    8 4 3    2 6 5" +
            "5 2 8    7 9 6    3 4 1" +
            "6 3 4    2 5 1    7 9 8" +
            "4 8 3    9 1 5    6 7 2" +
            "2 1 6    3 7 4    5 8 6" +
            "7 9 5    6 8 2    4 1 3";

    String stern23bonsai =
            "- - 9   5 - -   8 1 7" +
            "- 3 -   - 8 -   6 - 9" +
            "8 - -   - - -   2 3 4" +
            "6 - -   3 1 5   - - -" +
            "- 9 -   8 - 2   - 6 -" +
            "- - -   7 6 9   - - 2" +
            "3 8 1   - - -   - - 5" +
            "9 - 5   - 2 -   - 8 -" +
            "4 6 2   - - 8   9 - -";

    // List<String> sudokusToSolve = Arrays.asList(test0, stern23bonsai);
    List<String> sudokusToSolve = Arrays.asList(test0, test1, test2, stern23bonsai);
    // List<String> sudokusToSolve = Collections.singletonList(test0);

    @Test
    public void testCheckStep() {
        Sudoku.Step step = new Sudoku.Step(8, 8, 6);
        SudokuList problem = new SudokuList(stern23bonsai);
        problem.checkStep(step);
        System.out.println(problem.checkStep(step));
        assertFalse(problem.checkStep(step));

        step = new Sudoku.Step(7, 8, 6);
        problem.checkStep(step);
        System.out.println(problem.checkStep(step));
        assertTrue(problem.checkStep(step));
    }

    @Test
    public void testDone() {
        SudokuList problem = new SudokuList(stern23bonsai);
        assertFalse(problem.done());
    }

    @Test
    public void testEquals() {
        SudokuList problem1 = new SudokuList(stern23bonsai);
        SudokuList problem2 = new SudokuList(stern23bonsai);
        SudokuList state1 = problem1.getState();
        SudokuList state2 = problem2.getState();
        assertEquals(state2, state1);
    }

    @Test
    public void testDoStepUnDoStep() {
        Sudoku problem = new SudokuList(stern23bonsai);
        Sudoku oldState = problem.getState();
        Sudoku.Step step = new Sudoku.Step(0, 0, 1);
        problem.doStep(step);
        problem.undoStep(step);
        Sudoku newState = problem.getState();
        assertEquals(newState, oldState);
    }

    @Test
    public void testGetSteps() {
        SearchProblemList<Sudoku, Sudoku.Step> problem = new SudokuList(test2);
        List<Sudoku.Step> steps = problem.getSteps();
        System.out.println(steps.size());
        for (Sudoku.Step step : steps) {
            System.out.println(step);
        }
    }

    @Test
    public void testState() {
        SearchProblemList<Sudoku, Sudoku.Step> problem = new SudokuList(test0);
        List<Sudoku.Step> steps = problem.getSteps();
        Sudoku.Step s = steps.get(0);

        Sudoku state0 = problem.getState();

        problem.doStep(s);
        Sudoku state1 = problem.getState();

        problem.undoStep(s);
        Sudoku state2 = problem.getState();

        String str0 = state0.toString();
        String str1 = state1.toString();
        String str2 = state2.toString();

        assertNotEquals(state0, state1);
        assertNotEquals(state1, state2);
        assertEquals(state0, state2);

        assertNotEquals(str0, str1);
        assertNotEquals(str1, str2);
        assertEquals(str0, str2);
    }

    @Test
    public void testSearchByList() {
        for (String sudokuToSolve : sudokusToSolve) {
            SearchProblemList<Sudoku, Sudoku.Step> problem = new SudokuList(sudokuToSolve);
            SearchByList<Sudoku, Sudoku.Step> searcher = new SearchByList<>(problem);
            List<Sudoku> result = searcher.search();

            for (Sudoku state : result) {
                System.out.println(state);
                System.out.println("__________________");
            }
        }
    }

    @Test
    public void testToString() {
        SearchProblemList<Sudoku, Sudoku.Step> problem = new SudokuList(stern23bonsai);
        System.out.println(problem);
        Sudoku state = problem.getState();
        System.out.println(state);
    }
}
