package model;

/**
 * Created by plouzeau on 2014-10-09.
 */
public class BoardImpl implements Board {


    private final int sideSizeInSquares;
    private Direction directionToPackInto;

    public BoardImpl(int sideSizeInSquares) {
        if (sideSizeInSquares <= 1) {
            throw new IllegalArgumentException("sideSizeInSquares");
        }
        this.sideSizeInSquares = sideSizeInSquares;
        currentBoard = new Tile[sideSizeInSquares][sideSizeInSquares];
        nextBoard = new Tile[sideSizeInSquares][sideSizeInSquares];
    }

    @Override
    public int getSideSizeInSquares() {
        return this.sideSizeInSquares;
    }


    private Tile[][] currentBoard;
    private Tile[][] nextBoard;

    /**
     * Return the tile at a given coordinate, or null if none exists there.
     *
     * @param lineNumber   must be >=1 and <= getSideSizeInSquares()
     * @param columnNumber must be >=1 and <= getSideSizeInSquares()
     * @return a tile or null if none
     * @throws IllegalArgumentException if parameters are out of board's bounds
     */
    @Override
    public Tile getTile(int lineNumber, int columnNumber) {
        return currentBoard[lineNumber - 1][columnNumber - 1];
    }

    @Override
    public void packIntoDirection(Direction direction) {
        this.directionToPackInto = direction;
        for (int i = 1; i <= sideSizeInSquares; i++) {
            packLine(i);
        }

    }

    @Override
    public void commit() {

        currentBoard = nextBoard;
        nextBoard = new Tile[sideSizeInSquares][sideSizeInSquares];
    }



    private void packLine(int lineNumber) {
      /*
      * Scan the current board line looking for two consecutive tiles
      * with the same rank
      * When this case is encountered, write a single tile with rank+1
      * Otherwise just copy the tile (in practice packing it in the nex board)
      * Remember that indices are 1-based in this code
      * Conversion to Java arrays indices is done in computeLineIndex and computeColumnIndex
      */
        int readIndex = 1; // Position of the tile to be read
        int writeIndex = 0; // Position of the last tile written

        while (readIndex <= sideSizeInSquares) {
            // Find next tile
            while ((readIndex <= sideSizeInSquares)
                    && (readTile(currentBoard, lineNumber, readIndex) == null)) {
                readIndex++;
            }
            if (readIndex > sideSizeInSquares) {
                break; // Done with the line
            }
            // Try to merge with previous tile
            if ((writeIndex > 0) &&
                    (readTile(nextBoard, lineNumber, writeIndex).getRank()
                            == readTile(currentBoard, lineNumber, readIndex).getRank())) {
                // Merge previously written tile and currently read one
                readTile(nextBoard, lineNumber, writeIndex).incrementRank();
            } else {
                // Advance write index and copy currently read tile
                writeIndex++;
                writeTile(nextBoard, readTile(currentBoard, lineNumber, readIndex), lineNumber, writeIndex);
            }
            // Done with the tile read, move forward
            readIndex++;
        }


    }

    private void writeTile(Tile[][] board, Tile tile, int lineIndex, int columnIndex) {
        board[computeLineIndex(lineIndex, columnIndex)][computeColumnIndex(lineIndex, columnIndex)] = tile;
    }

    private Tile readTile(Tile[][] board, int lineIndex, int columnIndex) {
        int boardLineIndex = computeLineIndex(lineIndex, columnIndex);
        int boardColumnIndex = computeColumnIndex(lineIndex, columnIndex);
        Tile currentTile = board[boardLineIndex][boardColumnIndex];
        return currentTile;
    }

    /**
     * Adds a level of indirection in the index computation
     * In practice provides a rotation/symmetry so that we need
     * to deal with one packing directionToPackInto only.
     * !! Note that NO CHECKS are made on parameter bounds.
     *
     * @param lineIndex   must be in [1..sideSizeInSquares]
     * @param columnIndex must be in [1..sideSizeInSquares]
     * @return the columnIndex after rotation/symmetry
     */
    private int computeColumnIndex(int lineIndex, int columnIndex) {
        switch (directionToPackInto) {
            case RIGHT:
                return sideSizeInSquares - columnIndex;
            case LEFT:
                return columnIndex - 1;
            case UP:
                return lineIndex - 1;
            case DOWN:
                return lineIndex - 1;
        }
        return 0; // NOT REACHED
    }

    /**
     * Adds a level of indirection in the index computation
     * In practice provides a rotation/symmetry so that we need
     * to deal with one packing directionToPackInto only.
     * !! Note that NO CHECKS are made on parameter bounds.
     *
     * @param lineIndex   must be in [1..sideSizeInSquares]
     * @param columnIndex must be in [1..sideSizeInSquares]
     * @return the lineIndex after rotation/symmetry
     */
    private int computeLineIndex(int lineIndex, int columnIndex) {
        switch (directionToPackInto) {
            case LEFT:
                return lineIndex - 1;
            case RIGHT:
                return lineIndex - 1;
            case DOWN:
                return sideSizeInSquares - columnIndex;
            case UP:
                return columnIndex - 1;
        }
        return 0; // NOT REACHED
    }

    /**
     * For testing purposes only.
     * Creates a board configuration using a matrix of ranks
     *
     * @param rankMatrix a non null matrix reference, must match board size
     */
    public void loadBoard(int[][] rankMatrix) {
        for (int i = 0; i < sideSizeInSquares; i++) {
            for (int j = 0; j < sideSizeInSquares; j++) {
                if (rankMatrix[i][j] > 0) {
                    currentBoard[i][j] = new TileImpl(rankMatrix[i][j]);
                }
            }
        }
    }
}