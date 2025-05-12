package PracticalExercises.SecondPractical.ex02;

public class Hand {
    private Card vira;
    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;
    private Round[] rounds;
    int numRounds;

    public Hand(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        Deck deck = new Deck();
        deck.shuffle();
        vira = deck.takeOne();
        player1.setCards(deck.take(3));
        player2.setCards(deck.take(3));
        rounds = new Round[3];
        numRounds = 0;
        player1Score = 0;
        player2Score = 0;
    }

    public void playRound(){
        if(isDone()) return;

        Card cardPlayer1 = player1.chooseCard();
        Card cardPlayer2 = player2.chooseCard();
        if(cardPlayer1 == null || cardPlayer2 == null) return;
        Round round = new Round(player1.getName(), cardPlayer1, player2.getName(), cardPlayer2, vira);
        String winner = round.getWinner();
        System.out.println("The winner of the round is: " + winner);
        rounds[numRounds] = round;
        numRounds++;

        if(winner!=null){
            if(winner.equals(player1.getName())) player1Score++;
            else if(winner.equals(player2.getName())) player2Score++;
        }
    }

    public boolean isDone(){
       return numRounds>=3 || player1Score == 2 || player2Score == 2;
    }

    public String getWinner(){
        if(player1Score==2) return player1.getName();
        if(player2Score==2) return player2.getName();
        return null;
    }
}
