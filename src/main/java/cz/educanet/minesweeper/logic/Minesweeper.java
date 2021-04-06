package cz.educanet.minesweeper.logic;

public class Minesweeper {

    private int rowsCount;
    private int columnsCount;
    private GameField gameField;
    private boolean loose = false;
    private boolean win = false;

    public Minesweeper(int rows, int columns) {
        this.rowsCount = rows;
        this.columnsCount = columns;
        this.gameField = new GameField(this.rowsCount, this.columnsCount, 30);
    }

    /*
     * TODO: Konce
     * TODO: Otazniky
     * TODO: Vice praporu nez bomb
     * TODO: Overovani na krajich
     * */


    /**
     * 0 - Hidden
     * 1 - Visible
     * 2 - Flag
     * 3 - Question mark
     *
     * @param x X
     * @param y Y
     * @return field type
     */
    public int getField(int x, int y) {
        return this.gameField.getCellState(x,y);
    }

    /**
     * Toggles the field state, ie.
     * 0 -> 1,
     * 1 -> 2,
     * 2 -> 3 and
     * 3 -> 0
     *
     * @param x X
     * @param y Y
     */
    public void toggleFieldState(int x, int y) {
        int fState = gameField.getCellState(x, y);
        if(fState != 1) {
            if (fState == 0) fState = 2;
            else if (fState < 3) {
                fState++;
            } else {
                fState = 0;
            }
            gameField.setCellState(x, y, fState);
        }
        if(getRemainingBombCount() == 0) {
            if(gameField.checkFlags()) {
                win = true;
            }
        }
    }

    /**
     * Reveals the field and all fields adjacent (with 0 adjacent bombs) and all fields adjacent to the adjacent fields... ect.
     *
     * @param x X
     * @param y Y
     */
    public void reveal(int x, int y) {
        if (isBombOnPosition(x,y)) loose = true;
        else {
            gameField.setCellState(x, y, 1);
            if(getAdjacentBombCount(x,y) == 0) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int x1 = x + i;
                        int y1 = y + j;
                        if(!(x1 <= -1 || y1 <= -1 || x1>getColumns()-1 || y1 > getRows()-1)) {
                            if(gameField.getCellState(x1, y1) !=1 && !gameField.isCellBomb(x1, y1)) {
                                reveal(x1,y1);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the amount of adjacent bombs
     *
     * @param x X
     * @param y Y
     * @return number of adjacent bombs
     */
    public int getAdjacentBombCount(int x, int y) {
        return gameField.getAdjacentBombCount(x,y);
    }

    /**
     * Checks if there is a bomb on the current position
     *
     * @param x X
     * @param y Y
     * @return true if bomb on position
     */
    public boolean isBombOnPosition(int x, int y) {
        return gameField.isCellBomb(x,y);
    }

    /**
     * Returns the amount of bombs on the field
     *
     * @return bomb count
     */
    public int getBombCount() {
        return gameField.getBombCount();
    }

    /**
     * total bombs - number of flags
     *
     * @return remaining bomb count
     */
    public int getRemainingBombCount() {
        return gameField.getRemainingBombCount();
    }

    /**
     * returns true if every flag is on a bomb, else false
     *
     * @return if player won
     */
    public boolean didWin() {
        return this.win;
    }

    /**
     * returns true if player revealed a bomb, else false
     *
     * @return if player lost
     */
    public boolean didLoose() {
        return this.loose;
    }

    public int getRows() {
        return rowsCount;
    }

    public int getColumns() {
        return columnsCount;
    }

}
