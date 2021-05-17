package de.qaware.fasttrack.searching;

public class Grid {
    public int[][] grid;

    public Grid(int[][] grid) {
        this.grid = grid;
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
}
