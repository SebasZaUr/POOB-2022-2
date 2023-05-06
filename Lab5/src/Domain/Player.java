package domain;

public class Player{
    private String name;
    private int movements;
    private boolean turn;

    public Player(String name, boolean turn){
        this.name = name;
        movements = 0;
        this.turn = turn;
    }

    public boolean isTurn(){
        return turn;
    }

    public int getMovements(){
        return movements;
    }

    public void addMovement(){
        movements ++;
    }

    public void setTurn(boolean change){
        this.turn = change;
    }

}
