package com.godev.entities;


//je n'ai pas pu utiliser lombok à cause d'un probléme d'annotation processing
public class Hero {


    private Position currentPosition;
    private String moves;

    private Hero(Builder builder){
        this.currentPosition = builder.currentPosition;
        this.moves = builder.moves;
    }

    @Override
    public String toString(){return String.format("{Hero : position = %s , moves = %s}" , currentPosition.toString() , moves) ;}
    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    public static Builder builder(){
        return new Builder();
    }
    public void moveX(int distance){
        currentPosition.x+=distance;
    }

    public void moveY(int distance){
        currentPosition.y+=distance;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public static class Builder{
        private Position currentPosition ;
        private String moves;

        public Builder position(Position position){
            this.currentPosition = position;
            return this;
        }
        public Builder moves(String moves){
            this.moves = moves;
            return this;
        }

        public  Hero build(){return new Hero(this);}
    }


}
