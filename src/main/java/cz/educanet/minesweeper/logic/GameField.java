package cz.educanet.minesweeper.logic;

public class GameField {

    private Cell[][] gameField;

    public GameField(int rows, int columns) {
        this.gameField = new Cell[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                this.gameField[i][j] = new Cell();
            }
        }
        getAdjacentBombCount(1,1);

        gameField[1][1].setBomb(true);
        gameField[1][2].setBomb(true);
    }
    public int getCellState(int x, int y) {
        return gameField[x][y].getFieldState();
    }

    public boolean isCellBomb(int x, int y) {
        return gameField[x][y].isBomb();
    }

    public void setCellState(int x, int y, int state) {
        gameField[x][y].setFieldState(state);
    }

    //TODO: This
    public int getAdjacentBombCount(int x, int y) {
        int count = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int x1 = x + i;
                int y1 = y + j;
                if(!(x1 < 0 || y1 < 0 || x1>gameField.length-1 || y1 > gameField[0].length-1) && (x1 != 0 && y1 != 0) && isCellBomb(x1, y1)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getBombCount() {
        int count = 0;
        for (Cell[] cells : gameField) {
            for (Cell cell : cells) {
                if(cell.isBomb()) count++;
            }
        }

        return count;
    }

    public int getFlagCount() {
        int count = 0;
        for (Cell[] cells : gameField) {
            for (Cell cell : cells) {
                if(cell.getFieldState() == 2) count++;
            }
        }

        return count;
    }

    public int getRemainingBombCount() {
        return getBombCount() - getFlagCount();
    }

    public boolean checkFlags() {
        for (Cell[] cells : gameField) {
            for (Cell cell : cells) {
                if(cell.isBomb() && cell.getFieldState() != 2) return false;
            }
        }
        return true;
    }

}
