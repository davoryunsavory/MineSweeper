import java.awt.Color;

public class cell {
    boolean[][] cell;
    boolean[][] flag;
    boolean[][] bomb;
    int[] potential;
    int[] distance;
    int bombcount = 1;
    Color c;
    int row;
    int col;
    int foundBombs = 0;
    int runningCount = 0;
    int foundOpen = 0;

    cell(int row, int col) {
        boolean[][] tempcell = new boolean[col][row];
        boolean[][] tempflag = new boolean[col][row];
        boolean[][] tempbomb = new boolean[col][row];
        int[] potential = new int[col*row];
        this.potential = potential;
        int[] tempdistance = new int[col * row];
        for (int i = 0; i < col; i++) {
            for (int k = 0; k < row; k++) {
                tempcell[i][k] = false;
            }
        }
        for (int i = 0; i < col; i++) {
            for (int k = 0; k < row; k++) {
                tempflag[i][k] = false;
            }
        }
        for (int i = 0; i < bombcount; i++) {
            int x = (int) (Math.random() * ((col - 1) + 1) + 0);
            int y = (int) (Math.random() * ((row - 1) + 1) + 0);
            tempbomb[x][y] = true;
        }
        int runningnum = 0;

        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                runningnum++;
                // i == row == y
                // k == col == x
                int count = 0;
                // top left corner XX
                if (i == 0 && k == 0) {

                    if (tempbomb[i + 1][k] == true) {
                        count++;
                    }

                    if (tempbomb[i + 1][k + 1] == true) {
                        count++;
                    }

                    if (tempbomb[i][k + 1] == true) {
                        count++;
                    }
                    // top right corner XX
                } else if (i == col - 1 && k == 0) {

                    // middle left
                    if (tempbomb[i - 1][k] == true) {
                        count++;
                    }
                    // bottom left

                    if (tempbomb[i - 1][k + 1] == true) {
                        count++;
                    }
                    // bottom middle

                    if (tempbomb[i][k + 1] == true) {
                        count++;
                    }
                    // bottom left corner
                } else if (i == 0 && k == row - 1) {

                    // top middle
                    if (tempbomb[i][k - 1] == true) {
                        count++;
                    }
                    // top right

                    if (tempbomb[i + 1][k - 1] == true) {
                        count++;
                    }
                    // middle right

                    if (tempbomb[i + 1][k] == true) {
                        count++;
                    }
                    // bottom right corner
                } else if (i == row - 1 && k == col - 1) {

                    // top middle
                    if (tempbomb[i - 1][k] == true) {
                        count++;
                    }
                    // top left

                    if (tempbomb[i - 1][k - 1] == true) {
                        count++;
                    }
                    // middle left

                    if (tempbomb[i][k - 1] == true) {
                        count++;
                    }
                    // top
                } else if (k == 0) {
                    if (tempbomb[i - 1][k] == true) {
                        count++;
                    }
                    if (tempbomb[i - 1][k + 1] == true) {
                        count++;
                    }
                    if (tempbomb[i][k + 1] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k + 1] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k] == true) {
                        count++;
                    }
                    // left
                } else if (i == 0) {
                    if (tempbomb[i][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k + 1] == true) {
                        count++;
                    }
                    if (tempbomb[i][k + 1] == true) {
                        count++;
                    }
                    // right
                } else if (i == col - 1) {
                    if (tempbomb[i][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i - 1][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i - 1][k] == true) {
                        count++;
                    }
                    if (tempbomb[i - 1][k + 1] == true) {
                        count++;
                    }
                    if (tempbomb[i][k + 1] == true) {
                        count++;
                    }
                } else if (k == row - 1) {
                    if (tempbomb[i - 1][k] == true) {
                        count++;
                    }
                    if (tempbomb[i - 1][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k - 1] == true) {
                        count++;
                    }
                    if (tempbomb[i + 1][k] == true) {
                        count++;
                    }
                } else {
                    // left
                    if (tempbomb[i - 1][k] == true) {
                        count++;
                    }
                    // top left
                    if (tempbomb[i - 1][k - 1] == true) {
                        count++;
                    }
                    // top
                    if (tempbomb[i][k - 1] == true) {
                        count++;
                    }
                    // top right
                    if (tempbomb[i + 1][k - 1] == true) {
                        count++;
                    }
                    // right
                    if (tempbomb[i + 1][k] == true) {
                        count++;
                    }
                    // bottom left
                    if (tempbomb[i + 1][k + 1] == true) {
                        count++;
                    }
                    // bottom
                    if (tempbomb[i][k + 1] == true) {
                        count++;
                    }
                    // bottom left
                    if (tempbomb[i - 1][k + 1] == true) {
                        count++;
                    }
                }
                tempdistance[runningnum - 1] = count;
            }
        }

        this.bomb = tempbomb;
        this.col = col;
        this.row = row;
        this.cell = tempcell;
        this.flag = tempflag;
        this.distance = tempdistance;
    }

    public void setCell(int x, int y) {
        if (this.flag[x][y] == true) {
            this.flag[x][y] = false;
        }
        this.cell[x][y] = true;
    }

    public void doAuto() {
        runningCount++;
        System.out.println(runningCount);
        int x = (int) (Math.random() * ((this.col - 1) + 1) + 0);
        int y = (int) (Math.random() * ((this.row - 1) + 1) + 0);
        this.cell[x][y] = true;
        for (int i = 0; i < this.row; i++) {
            for (int k = 0; k < this.col; k++) {
            }
        }
        if (this.bomb[x][y] == true) {
            this.cell[x][y] = false;
            foundOpen++;
        }
        if (this.bomb[x][y] != true) {
            this.cell[x][y] = true;
        }
        if (foundOpen != 64 - bombcount) {
            doAuto();
        }

    }

    public void doRealAuto() {
        int x = (int) (Math.random() * ((this.col - 1) + 1) + 0);
        int y = (int) (Math.random() * ((this.row - 1) + 1) + 0);
        
        if (this.distance[x*y] == 0 && (x == 0 && y == 0)) {
            this.cell[x][y] = true;
            this.cell[x+1][y] = true;
            this.cell[x+1][y+1] = true;
            this.cell[x][y+1] = true;
        } else if (this.distance[x*y] == 0 && (x == this.col - 1 && y == 0)) {
            this.cell[x][y] = true;
            this.cell[x-1][y] = true;
            this.cell[x-1][y+1] = true;
            this.cell[x][y+1] = true;
        } else if (this.distance[x*y] == 0 && (x == 0 && y == this.row - 1)) {
            this.cell[x][y] = true;
            this.cell[x][y-1] = true;
            this.cell[x+1][y-1] = true;
            this.cell[x+1][y] = true;
        } else if (this.distance[x*y] == 0 && (x == this.col - 1 && y == row - 1)) {
            this.cell[x][y] = true;
            this.cell[x-1][y] = true;
            this.cell[x-1][y-1] = true;
            this.cell[x][y-1] = true;
        } else if (y == 0) {
            this.cell[x][y] = true;
            this.cell[x-1][y] = true;
            this.cell[x-1][y+1] = true;
            this.cell[x][y+1] = true;
            this.cell[x+1][y+1] = true;
            this.cell[x+1][y] = true;
        } else if (x == 0) {
            this.cell[x][y] = true;
            this.cell[x][y-1] = true;
            this.cell[x+1][y-1] = true;
            this.cell[x+1][y] = true;
            this.cell[x+1][y+1] = true;
            this.cell[x][y+1] = true;
        } else if (x == this.col - 1) {
            this.cell[x][y] = true;
            this.cell[x][y-1] = true;
            this.cell[x-1][y-1] = true;
            this.cell[x-1][y] = true;
            this.cell[x-1][y+1] = true;
            this.cell[x][y+1] = true;
        } else if (y == this.row - 1) {
            this.cell[x][y] = true;
            this.cell[x-1][y] = true;
            this.cell[x-1][y-1] = true;
            this.cell[x][y-1] = true;
            this.cell[x+1][y-1] = true;
            this.cell[x+1][y] = true;
        } else if (this.distance[x*y] == 0) {
            this.cell[x][y] = true;
            this.cell[x - 1][y + 1] = true;
            this.cell[x][y + 1] = true;
            this.cell[x + 1][y + 1] = true;
            this.cell[x + 1][y] = true;
            this.cell[x][y - 1] = true;
            this.cell[x - 1][y - 1] = true;
            this.cell[x - 1][y] = true;
            this.cell[x + 1][y - 1] = true;
        }
    }

    public boolean[][] getBomb() {
        return this.bomb;
    }

    public void setFlag(int x, int y) {
        this.flag[x][y] = true;
    }

    public boolean[][] getCell() {
        return this.cell;
    }

    public boolean[][] getFlag() {
        return this.flag;
    }

    public int[] getDistance() {
        return this.distance;
    }
}
