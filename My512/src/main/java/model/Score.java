package model;

public class Score {

    private int score;

    public Score(){
        score = 0;
    }

    public void addScore(int toAdd){
        score += toAdd;
        System.out.println(score);
    }

    public int getScore(){ return score; }
}
