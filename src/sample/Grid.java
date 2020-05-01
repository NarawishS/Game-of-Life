package sample;

public class Grid {
    int width;
    int height;
    int[][] grid;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[width][height];
    }

    public void printGrid() {
        StringBuilder line = new StringBuilder();
        System.out.println("---");
        for (int y = 0; y < height; y++) {
            line.delete(0,line.length());
            line.append("|");
            for (int x = 0; x < width; x++) {
                if (this.grid[x][y] == 0) {
                    line.append("*");
                } else {
                    line.append(".");
                }
            }
            line.append("|");
            System.out.println(line.toString());
        }
        System.out.println("---\n");
    }

    public void setAlive(int x, int y) {
        this.grid[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.grid[x][y] = 0;
    }

    public int countNeighbors(int x, int y) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int col = (x + i + width) % width;
                int row = (y + j + height) % height;
                sum += this.grid[col][row];
            }
        }
        sum -= this.grid[x][y];
        return sum;
    }

    public void next() {
        int[][] next = new int[width][height];
        // Compute next based on grid
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Count live neighbors!
                int neighbors = countNeighbors(i, j);
                int state = this.grid[i][j];
                if (state == 0 && neighbors == 3) {
                    next[i][j] = 1;
                } else if (state == 1 && (neighbors < 2 || neighbors > 3)) {
                    next[i][j] = 0;
                } else {
                    next[i][j] = state;
                }
            }
        }
        this.grid = next;
    }

    public static void main(String[] args) {
        Grid grid = new Grid(10, 5);
        grid.setAlive(2, 2);
        grid.setAlive(3, 2);
        grid.setAlive(4, 2);
    }
}
