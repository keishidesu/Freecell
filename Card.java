/**
* This class initializes all the functionalities and
* constructors of the Card class
*/
public class Card {
	/**
	* All the exiting suits of a class
	*/
	public enum Suit {
		Spades,
		Hearts,
		Clubs,
		Diamonds
    }

	/**
	* Initializing the number of ranks of a deck
	*/
    public static final int MAX_RANK = 13;
	private int rank;
	private Suit suit;

	/**
	* Constructor of the Card class
	* @param suit suit of the card under enum type
	* @param rank rank of the card
	*/
	public Card (Suit suit, int rank) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	* This method determines whether a card
	* is one rank smaller than another card
	* @param card Comparing card
	* @return Boolean true or false
	*/
	public boolean isOneRankSmallerThan(Card card) {
		return (this.rank == (card.rank - 1));
    }

	/**
	* This method checks whether two cards are
	* of the same suit
	* @param card Checking card
	* @return Boolean true or false
	*/  
    public boolean isSameSuit(Card card) {
        return (this.suit.equals(card.suit));
    }

	/**
	* This method checks whether the a card
	* has a specific rank and suit
	* @param charsuit Suit of the card
	* @param rank Rank of the card
	* @return Boolean true or false
	*/
    public boolean isCard(Character charsuit, int rank) {
        Suit suit;
        if (charsuit == 's') {
            suit = Suit.Spades;
        } else if (charsuit == 'h') {
            suit = Suit.Hearts;
        } else if (charsuit == 'c') {
            suit = Suit.Clubs;
        } else {
            suit = Suit.Diamonds;
        }
        return ((this.suit.equals(suit)) && (this.rank == rank));
    }

	/**
	* This method overides the class' toString() method
	* @return Expected strings
	*/
	public String toString(){
        String str = "";
        if (suit == Suit.Spades) {
            str += 's';
        } else if (suit == Suit.Hearts) {
            str += 'h';
        } else if (suit == Suit.Clubs) {
            str += 'c';
        } else if (suit == Suit.Diamonds) {
            str += 'd';
        }

        if (rank == 0) {
            str += 'A';
        } else if (rank == 9) {
            str += 'X';
        } else if (rank == 10) {
            str += 'J';
        } else if (rank == 11) {
            str += 'Q';
        } else if (rank == 12) {
            str += 'K';
        } else {
            str += Integer.toString(rank + 1);
        }

   		return str;
  	}
}