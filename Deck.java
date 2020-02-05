import java.util.*;

/**
* This class initializes all the functionalities and
* constructors of the Deck class
*/
public class Deck {

	// Stack of cards
	Stack<Card> cards = new Stack<Card>();
	
	/**
	* Constructor of the Deck class where there is
	* initialization of the 52 cards shuffled
	*/
	public Deck() {
        Card.Suit[] suits = Card.Suit.values();
		for (int i = 0 ; i < suits.length ; i++){
			for (int j = 0 ; j < Card.MAX_RANK ; j++){
				this.cards.push(new Card(suits[i], j));
			}
		}
		Collections.shuffle(this.cards);
	}

	/**
	* This method carries out the card popping movement
	* for Columns 1 to 8
	* @param number Number of cards
	* @return ArrayList of cards 
	*/
	public ArrayList<Card> popCard(int number) {
        ArrayList<Card> cards = new ArrayList<Card>();

        if (number > this.cards.size()) {
            number = this.cards.size();
        }
        for (int i = 0; i < number; i++) {
            cards.add(this.cards.pop());
        }
		return cards;
    }
    
	/**
	* This method carries out the card popping movement
	* @return ArrayList of cards 
	*/
    public ArrayList<Card> popCard() {
		return popCard(this.cards.size());
    }
}