package model;

public class Model {

    Board board;
    Score score;

    public Model(int sideSizeInSquares){
        score = new Score();
        board = new BoardImpl(sideSizeInSquares, score);
    }

    public Board getBoard() {
        return board;
    }
}
