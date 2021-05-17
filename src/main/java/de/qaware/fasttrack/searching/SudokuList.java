package de.qaware.fasttrack.searching;

import java.util.ArrayList;
import java.util.List;

public class SudokuList extends Sudoku implements SearchProblemList<Sudoku, Sudoku.Step> {

    public SudokuList(int[][] grid) {
        super(grid);
    }

    public SudokuList(String input) {
        super(input);
    }

    @Override
    public SudokuList getState() {
        return new SudokuList(this.grid);
    }

    @Override
    public List<Step> getSteps() {
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    for (int value = 1; value < 10; value++) {
                        Step step = new Step(i, j, value);
                        if (checkStep(step)) {
                            steps.add(step);
                        }
                    }
                    return steps;
                }
            }
        }
        return steps;
    }
}