package Aula07.ex02;

public class Player {
    private String name;
    private int score;
    private Card[] cards;

    public Player(String name){
        this.name = name;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Card chooseCard(){
        for(int i =0 ; i < cards.length ; i++){
            if(cards[i]!=null){
                Card chosen = cards[i];
                for(int j = i ; j < cards.length -1; j++){
                    cards[j] = cards[j+1];
                }
                cards[cards.length-1] = null;

                return chosen;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Card[] getCard() {
        return cards;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }


}
