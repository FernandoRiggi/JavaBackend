package Aula03.Ex00;

public class Card {
    private final Rank rank;
    private final Suit suit;
    private boolean faceDown;

    public Card(Rank rank, Suit suit) {
       this(rank, suit, false);
    }

    public Card(Rank rank, Suit suit, boolean faceDown) {
        this.rank = rank;
        this.suit = suit;
        this.faceDown = faceDown;
    }

    public String cardAsString() {
        return isFaceDown()?"XX" : (rank + " " + suit);
    }



    public Rank getRank() {
        return rank;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public void faceUP(){
        faceDown = false;
    }

    public void faceDown(){
        faceDown = true;
    }

    public Suit getSuit() {
        return suit;
    }
}
