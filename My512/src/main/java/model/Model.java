package model;

public class Model {

    Board board;

    public Model(){
        board = new BoardImpl(4);
    }

    public Board getBoard() {
        return board;
    }
}
