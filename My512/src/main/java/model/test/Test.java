package model.test;

import java.io.File;

public class Test {

    public static void main(String[] args) {

        File file = new File("src/main/java/sample/sample.fxml");
        System.out.println(file.toURI().toString());
    }
}
