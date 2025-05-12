package PracticalExercises.SecondPractical.ex02;

public class Game {
    private Player player1;
    private Player player2;
    private Hand[] hands;
    private int numberOfHands = 0;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        hands = new Hand[30];
        hands[numberOfHands] = new Hand(player1, player2);
    }

    public void play() {
        if(hands[numberOfHands].isDone()){
            String winner = hands[numberOfHands].getWinner();
            if(winner!=null){
                if(winner.equals(player1.getName())){
                    player1.setScore(player1.getScore()+1);
                } else if(winner.equals(player2.getName())){
                    player2.setScore(player2.getScore()+1);
                }
                System.out.println("Winner of this hand: " + winner);
                System.out.println("Scores: - " + player1.getName() + ": " + player1.getScore() + ", " + player2.getName() + ": " + player2.getScore());
            }
        numberOfHands++;
        hands[numberOfHands] = new Hand(player1, player2);
        }
        hands[numberOfHands].playRound();
    }

    public boolean isDone(){
        return numberOfHands>=30 || player1.getScore()==12 || player2.getScore()==12;
    }

    public Player getWinner(){
        if(player1.getScore()==12) return player1;
        if(player2.getScore()==12) return player2;
        return null;
    }
}
