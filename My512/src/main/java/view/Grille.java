package view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

//import java.awt.*;

public class Grille extends Parent {

    private Carree[][] Carrees;

    public Grille() {
        Rectangle fond_Grille = new Rectangle();

        fond_Grille.setWidth(400);

        fond_Grille.setHeight(200);

        fond_Grille.setArcWidth(30);

        fond_Grille.setArcHeight(30);

        fond_Grille.setFill( //on remplie notre rectangle avec un dégradé

                new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,

                        new Stop[] {

                                new Stop(0, Color.web("#333333")),

                                new Stop(1, Color.web("#000000"))

                        }

                )

        );

        Reflection r = new Reflection();//on applique un effet de réflection

        r.setFraction(0.25);

        r.setBottomOpacity(0);

        r.setTopOpacity(0.5);

        fond_Grille.setEffect(r);

//Rectangle carree = new Rectangle(75,75, Color.RED);   a mettre dans le main
        Carrees = new Carree[][]
                {
                        {new Carree(),new Carree(),new Carree(),new Carree()},
                        {new Carree(),new Carree(),new Carree(),new Carree()},
                        {new Carree(),new Carree(),new Carree(),new Carree()},
                        {new Carree(),new Carree(),new Carree(),new Carree()},
                };


        this.setTranslateX(50);

        this.setTranslateY(250);

        this.getChildren().add(fond_Grille);//on ajoute le rectangle au groupe

        for (Carree[] Carree: Carrees){   //on insère chaque Carree une par une.
                for (Carree Carree2 : Carree) {
                    this.getChildren().add(Carree2);
                }

        }
    }



}
