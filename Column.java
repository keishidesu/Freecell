import java.util.*;

/**
* This class initializes all the functionalities and
* constructors of the Column class
*/
public class Column {

    // Each column has a stack of cards
    private Stack<Card> cards = new Stack<Card>();
    private int bufferNumber = 0;

    /**
	* No-argument constructor of the class
	*/
    public Column() {}

    /**
	* Overloading constructor of the class where
    * each column has cards
    * @param cards ArrayList of cards
	*/
    public Column(ArrayList<Card> cards) {
        for (Card card: cards) {
            this.cards.push(card);
        }
    }

    /**
	* This method moves card(s) from column to column or pile
    * @param column Column of the table
    * @param reverse TO determine the way of poping cards is reversed or not
	*/
    public void moveCards(Column column, boolean reverse) {
        if (reverse) {
            for (int i = 0; i < bufferNumber; i++) {
                column.cards.push(this.cards.pop());
            }
        } else {
            Stack<Card> cards = new Stack<Card>();

            for (int i = 0; i < bufferNumber; i++) {
                cards.push(this.cards.pop());
            }

            for (int i = 0; i < bufferNumber; i++) {
                column.cards.push(cards.pop());
            }
        }
    }

    /**
	* This checks whether card exists or not
    * @return boolean true or false
	*/
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
	* This method gets the number of card to move
    * during a single move by checking its rank and suit
	*/
    public void updateBuffer() {
        bufferNumber = 1;
        for (int i = this.cards.size() - 1; i > 0; i--) {
            Card c1 = this.cards.get(i);
            Card c2 = this.cards.get(i - 1);
            if (c1.isOneRankSmallerThan(c2) && c1.isSameSuit(c2)) {
                bufferNumber++;
            } else {
                break;
            }
        }
    }

    /**
	* This method gets the last card of the same stack 
    * during a move
    * @return Last card of the stack
	*/
    public Card bufferedPeek() {
        return this.cards.get(this.cards.size() - (bufferNumber));
    }

    /**
	* This method peeks the last card
    * @return Last card
	*/
    public Card peek() {
        return this.cards.peek();
    }

    /**
	* This method overides the class' toString() method
    * @return Expected Strings
    */
    public String toString() {
        String str = "";
        for (Card card: this.cards) {
            str += card.toString() + ' ';
        }
        return str;
    }
}