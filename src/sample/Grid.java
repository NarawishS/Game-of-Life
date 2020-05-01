package sample;

public class Grid {
    final static int ALIVE = 1;
    final static int DEAD = 0;
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
            line.delete(0, line.length());
            line.append("|");
            for (int x = 0; x < width; x++) {
                if (this.grid[x][y] == DEAD) {
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
        setState(x, y, ALIVE);
    }

    public void setDead(int x, int y) {
        setState(x, y, DEAD);
    }

    public void setState(int x, int y, int state) {
        if (x < this.width && y < this.height && x >= 0 && y >= 0)
            this.grid[x][y] = state;
    }

    public int countNeighbors(int x, int y) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int col = (x + i + this.width) % this.width;
                int row = (y + j + this.height) % this.height;
                sum += this.grid[col][row];
            }
        }
        sum -= this.grid[x][y];
        return sum;
    }

    public void next() {
        int[][] next = new int[this.width][this.height];
        // Compute next based on grid
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                // Count live neighbors!
                int neighbors = countNeighbors(i, j);
                int state = this.grid[i][j];
                if (state == 0 && neighbors == 3) {
                    next[i][j] = ALIVE;
                } else if (state == 1 && (neighbors < 2 || neighbors > 3)) {
                    next[i][j] = DEAD;
                } else {
                    next[i][j] = state;
                }
            }
        }
        this.grid = next;
    }
}
