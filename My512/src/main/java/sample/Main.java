package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Board;
import model.Model;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

public class Main extends Application {

    
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/resources/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("2048");
        primaryStage.setScene(new Scene(root, 500, 600));

        Model model = new Model(4);

        int[][] rankMatrix = {{0,0,0,1},{0,0,0,1},{0,0,0,1},{0,0,0,1}};

        model.getBoard().loadBoard(rankMatrix);
        model.getBoard().printBoard(Logger.getGlobal(), "");

        new Controller(primaryStage, model);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
