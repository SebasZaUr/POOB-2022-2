package domain;

public class TantFant {
    private int[][] table;
    private Player whoWin; 
    private static int SIZE = 3;
    private Player player1;
    private Player player2;
    private int[] patronRow = {-1,-1,0,1,1,1,0,1};
    private int[] patronColumn = {0,1,1,1,0,-1,-1,-1};

    public TantFant(int size){
        table = new int[size][size];
        SIZE = size;
        for(int i = 0; i < SIZE;i++){
            table[0][i] = 2;
            table[SIZE - 1][i] = 1;
        }
        for(int j = 1; j < SIZE - 1;j++){
            for(int k = 0;k < SIZE;k++){
                table[j][k] = 0;
            }
        }
        player1 = new Player("J1", true);
        player2 = new Player("J2", false);
    }

    public void move(int startR, int startC, int finishR, int finishC) throws TantFantException{
        if(player1.isTurn()){
            moveLogic(1,startR,startC,finishR,finishC);
            player1.addMovement();
            player1.setTurn(false);
            player2.setTurn(true);
        }
        else{
            moveLogic(2, startR, startC, finishR, finishC);
            player2.addMovement();
            player1.setTurn(true);
            player2.setTurn(false);
        }
    }

    private void moveLogic(int num,int stR,int stC, int fhR, int fhC) throws TantFantException{
        if(table[stR][stC] == num && table[fhR][fhC] == 0){
            int state = 0;
            while(true){
                if(state == 8){
                    throw new TantFantException(TantFantException.WRONG_MOVEMENT);
                }
                if(stR + patronRow[state] < 0 || stR + patronRow[state] > SIZE - 1 || 
                stC + patronColumn[state] < 0 || stC + patronColumn[state] > SIZE - 1){
                    state ++;
                    continue;
                }
                else{
                    if(table[stR + patronRow[state]][stC + patronColumn[state]] == table[fhR][fhC]){
                        table[stR][stC] = 0;
                        table[fhR][fhC] = num;
                        state++;
                        break;
                    }
                    state++;
                }
            }
        }
        else{
            throw new TantFantException(TantFantException.WRONG_MOVEMENT);
        }
    }

    public int getTurn(){
        int turn = (player1.isTurn()) ? 1:2;
        return turn;
    }

    public int getPlayerMovements(int player){
        int moves = (player == 1) ? player1.getMovements():player2.getMovements();
        return moves;
    }
}
