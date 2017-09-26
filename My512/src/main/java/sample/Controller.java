package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Board;
import model.Model;

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

    private void Init(){
        primaryStage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.O) {
                System.out.println("You pressed O");
                packUp();
            }else if(key.getCode()== KeyCode.K) {
                System.out.println("You pressed K");
                packLeft();
            }else if(key.getCode()== KeyCode.L) {
                System.out.println("You pressed L");
                packDown();
            }else if(key.getCode()== KeyCode.M) {
                System.out.println("You pressed M");
                packRight();
            }
        });
    }

    public void packUp(){
        model.getBoard().packIntoDirection(Board.Direction.TOP);
    }

    public void packDown(){
        model.getBoard().packIntoDirection(Board.Direction.BOTTOM);
    }

    public void packLeft(){
        model.getBoard().packIntoDirection(Board.Direction.LEFT);
    }

    public void packRight(){
        model.getBoard().packIntoDirection(Board.Direction.RIGHT);
    }


}
