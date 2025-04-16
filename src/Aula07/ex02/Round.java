package Aula07.ex02;

public class Round {
    private String winner;

    public Round(String player1, Card card1, String player2, Card card2, Card vira){
        if(card1.isClosed() && !card2.isClosed()) winner = player2;
        if(card2.isClosed() && !card1.isClosed()) winner = player1;
        int comparison = card1.compareValueTo(card2, vira);
        if(comparison>0) winner = player1;
        else if(comparison<0) winner = player2;
        else winner = null;
    }
    public String getWinner() {
        return winner;
    }
}
