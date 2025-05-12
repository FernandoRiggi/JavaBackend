import Lesson01.Ex00.Card;
import Lesson01.Ex00.Deck;

public void main() {
    final Deck deck = new Deck();
    var oneCard = deck.takeOne();
    System.out.println(oneCard.cardAsString());

    var manyCards = deck.takeMany(3);
    for (Card card : manyCards) {
        card.faceUP();
        System.out.println(card.cardAsString());
    }
}
