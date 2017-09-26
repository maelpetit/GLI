package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Board;
import model.Model;

import java.util.logging.Logger;

public class Controller {

    Stage primaryStage;
    Model model;

    @Deprecated
    public Controller(){

    }

    public Controller(Stage primaryStage, Model model){
        this.primaryStage = primaryStage;
        this.model = model;
        Init();
    }

    private void print(){
        model.getBoard().printBoard(Logger.getGlobal(), "");
    }

    private void Init(){
        primaryStage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(!model.getBoard().isGameOver()) {
                if (key.getCode() == KeyCode.O) {
                    System.out.println("You pressed O");
                    packUp();
                    print();
                } else if (key.getCode() == KeyCode.K) {
                    System.out.println("You pressed K");
                    packLeft();
                    print();
                } else if (key.getCode() == KeyCode.L) {
                    System.out.println("You pressed L");
                    packDown();
                    print();
                } else if (key.getCode() == KeyCode.M) {
                    System.out.println("You pressed M");
                    packRight();
                    print();
                }
            }
        });
    }

    public void packUp(){
        model.getBoard().packIntoDirection(Board.Direction.TOP);
        commitAndGenerate();
    }

    public void packDown(){
        model.getBoard().packIntoDirection(Board.Direction.BOTTOM);
        commitAndGenerate();
    }

    public void packLeft(){
        model.getBoard().packIntoDirection(Board.Direction.LEFT);
        commitAndGenerate();
    }

    public void packRight(){
        model.getBoard().packIntoDirection(Board.Direction.RIGHT);
        commitAndGenerate();
    }

    private void commitAndGenerate(){
        model.getBoard().commit();
        model.getBoard().generateRandomTile();
    }


}
