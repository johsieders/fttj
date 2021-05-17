package de.qaware.fasttrack.searching;

import java.util.Arrays;

public abstract class Sudoku {
    int[][] grid = new int[9][9];

    public Sudoku(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, 9);
        }
    }

    public Sudoku(String input) {
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
    }

    public void doStep(Step step) {
        grid[step.i][step.j] = step.value;
    }

    public void undoStep(Step step) {
        grid[step.i][step.j] = 0;
    }

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

    public abstract Sudoku getState();

    public boolean checkStep(Step step) {
        return step.value > 0 && step.value < 10 &&
                checkBox(step) && checkRow(step) && checkColumn(step);
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

    @Override
    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null || getClass() != x.getClass()) return false;
        Sudoku state = (Sudoku) x;
        for (int i = 0; i < grid.length; i++) {
            if (!Arrays.equals(grid[i], state.grid[i])) return false;
        }
        return true;
    }

    @Override
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

    public static class Step {
        int i;
        int j;
        int value;

        public Step(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        public String toString() {
            return "(" + this.i + ", " + this.j + ", " + this.value + ")";
        }
    }
}