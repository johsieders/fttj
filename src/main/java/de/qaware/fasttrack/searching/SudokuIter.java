package de.qaware.fasttrack.searching;

import java.util.Arrays;
import java.util.Iterator;

public class SudokuIter implements SearchProblemIter<SudokuIter.State, Step> {
    int[][] grid = new int[9][9];

    public SudokuIter(String input) {
        if (input != null) {
            int counter = 0;
            String temp = input.replace("-", "0").replaceAll("\\D", "");
            if (temp.length() == 81) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        this.grid[i][j] = Character.getNumericValue(temp.charAt(counter));
                        counter++;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Ooops");
        }

    }

    @Override
    public Iterator<Step> getSteps() {
        return new getStepsIter();
    }

    @Override
    public void doStep(Step step) {
        grid[step.i][step.j] = step.value;
    }

    @Override
    public void undoStep(Step step) {
        grid[step.i][step.j] = 0;
    }

    @Override
    public boolean done() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public State getState() {
        return new State(grid);
    }


    public boolean checkStep(Step step) {
        if (step.value > 0 && step.value < 10) {
            return checkBox(step) && checkRow(step) && checkColumn(step);
        } else {
            return false;
        }

    }

    public boolean checkRow(Step step) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][step.j] == step.value) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn(Step step) {
        for (int j = 0; j < 9; j++) {
            if (grid[step.i][j] == step.value) {
                return false;
            }
        }
        return true;
    }

    public boolean checkBox(Step step) {
        int iBox = (step.i / 3) * 3;
        int jBox = (step.j / 3) * 3;
        int iBoxEnd = iBox + 3;
        int jBoxEnd = jBox + 3;
        for (int i = iBox; i < iBoxEnd; i++) {
            for (int j = jBox; j < jBoxEnd; j++) {
                if (grid[i][j] == step.value) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if ((i % 3 == 0) && (i > 0)) {
                output.append("\n");
            }
            for (int j = 0; j < 9; j++) {
                output.append(grid[i][j]);
                if ((j + 1) % 3 != 0) {
                    output.append(" ");
                } else if ((j + 1) == 9) {
                    output.append("\n");
                } else if ((j + 1) % 3 == 0) {
                    output.append("     ");
                }
            }
        }
        return output.toString();
    }

    public class State {
        int[][] grid = new int[9][9];

        State(int[][] grid) {
            for (int i = 0; i < 9; i++) {
                System.arraycopy(grid[i], 0, this.grid[i], 0, 9);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            for (int i = 0; i < grid.length; i++) {
                if (!Arrays.equals(grid[i], state.grid[i])) return false;
            }
            return true;
        }

        public String toString() {
            return SudokuIter.this.toString();
        }
    }

    public class getStepsIter implements Iterator<Step> {
        private int iMem;
        private int jMem;
        private int valueMem;

        public getStepsIter() {
            this.iMem = 0;
            this.jMem = 0;
            this.valueMem = 0;
        }

        @Override
        public boolean hasNext() {
            for (int ii = iMem; ii < 9; ii++) {
                for (int jj = jMem; jj < 9; jj++) {
                    if (grid[ii][jj] == 0) {
                        for (int testValue = valueMem; testValue < 10; testValue++) {
                            Step step = new Step(ii, jj, testValue);
                            if (checkStep(step)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
            }
            return false;
        }

        @Override
        public Step next() {
            for (int ii = iMem; ii < 9; ii++) {
                for (int jj = jMem; jj < 9; jj++) {
                    if (grid[ii][jj] == 0) {
                        for (int value = valueMem; value < 10; value++) {
                            Step step = new Step(ii, jj, value);
                            if (checkStep(step)) {
                                iMem = ii;
                                jMem = jj;
                                valueMem = value + 1;
                                return step;
                            }
                        }
                        valueMem = 0;
                        return null;
                    }
                }
            }
            return null;
        }
    }
}
