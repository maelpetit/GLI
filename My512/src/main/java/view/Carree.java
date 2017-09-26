package view;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Carree extends Parent {

    public String lettre;//lettre de la touche, c'est une variable public pour qu'elle puisse être lue depuis les autres classes

    private int positionX = 0;//abscisse

    private int positionY = 0;//ordonnée de la touche

    private int note = 0;//note correspond au numéro MIDI de la note qui doit être jouée quand on appuie sur la touche


    Rectangle fond_touche;

    Text lettre_touche;



    public Carree(){

        lettre =  new String("2");

        fond_touche = new Rectangle(75,75, Color.WHITE);

        fond_touche.setArcHeight(10);

        fond_touche.setArcWidth(10);

        this.getChildren().add(fond_touche);//ajout du rectangle de fond de la touche



        lettre_touche = new Text(lettre);

        lettre_touche.setFont(new Font(25));

        lettre_touche.setFill(Color.GREY);

        lettre_touche.setX(25);

        lettre_touche.setY(45);

        this.getChildren().add(lettre_touche);//ajout de la lettre de la touche

        this.setTranslateX(positionX);//positionnement de la touche sur le clavier

        this.setTranslateY(positionY);

    }
}

