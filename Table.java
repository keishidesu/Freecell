import java.util.*;

/**
* This class initializes all the functionalities and
* constructors of the Table class
*/
public class Table {

    private ArrayList<Column> columns = new ArrayList<Column>();
    private HashMap<Character, Column> piles =  new HashMap<Character, Column>();

    private static final int COLUMN_SIZE = 9;

    /**
	* Constructor of the Table class where deck,
    * columns, piles and cards are created
	*/
    public Table() {
        Deck deck = new Deck();
        Random random = new Random();

        /**
        for (int i = 0; i < COLUMN_SIZE; i++) {
            int number = 4 + random.nextInt(6); // 4 to 9
            
            if (i == COLUMN_SIZE - 1) { // Column 9 
                columns.add(new Column());
            } else if (i == COLUMN_SIZE - 2) {  // Column 8
                columns.add(new Column(deck.popCard()));
            } else {
                columns.add(new Column(deck.popCard(number))); // Column 1 to 7
            }
        }*/
        for (int i = 0; i < COLUMN_SIZE; i++) {
            
            if (i == COLUMN_SIZE - 1) { // Column 9 
                columns.add(new Column());
            } else if (i >= COLUMN_SIZE - 9 && i <= COLUMN_SIZE - 6) {  // Column 8
                columns.add(new Column(deck.popCard(7)));
            } else {
                columns.add(new Column(deck.popCard(6))); // Column 1 to 7
            }
        }
        piles.put('s', new Column());
        piles.put('h', new Column());
        piles.put('c', new Column());
        piles.put('d', new Column());
    }

    /**
	* This method carries out the movement of the game.
    * Consisting of error messages whenever there is an invalid movement
    * or whenever the game rules are being broken.
	* @param from Which column of the card to move
	* @param to Which column of the card's destination
    * @return Integer which determines its error messages
	*/
    public int moveCards(Character from, Character to) {
        // ascii table value of digit 1 to 9
        if ((from >= 49) && (from <= 57)) {
            int fromIndex = from - 49;
            Column fromColumn = columns.get(fromIndex);
            if (fromColumn.isEmpty()) {
                // no card to move from this column
                return 6;
            }
            fromColumn.updateBuffer();
            if ((to >= 49) && (to <= 57)) {
                // move to other column
                if (from == to) {
                    // cannot move to same place
                    return 3;
                }
                int toIndex = to - 49;
                Column toColumn = columns.get(toIndex);
                if (toColumn.isEmpty()) {
                    fromColumn.moveCards(toColumn, false);
                    return 0;
                }
                Card fromCard = fromColumn.bufferedPeek();
                Card toCard = toColumn.peek();
                if (fromCard.isOneRankSmallerThan(toCard)) {
                    fromColumn.moveCards(toColumn, false);
                } else {
                    // invalid movement (rank)
                    return 4;
                }
            } else if (piles.containsKey(to)) {
                // move to pile
                Column toColumn = piles.get(to);
                if (toColumn.isEmpty()) {
                    if (fromColumn.peek().isCard(to, 0)) {
                        fromColumn.moveCards(toColumn, false);
                        return 0;
                    } else {
                        // first card in pile must be rank A
                        return 7;
                    }
                }
                Card fromCard = fromColumn.peek();
                Card toCard = toColumn.peek();
                if (toCard.isOneRankSmallerThan(fromCard) && toCard.isSameSuit(fromCard)) {
                    fromColumn.moveCards(toColumn, true);
                } else {
                    // invalid movement (pile)
                    return 5;
                }
            } else {
                // unknown to column
                return 2;
            }
        } else {
            // can only move from column 1 to 9;
            return 1;
        }
        return 0;
    }

    /**
	* This method is called by the main fucntion
    * @param col Determines which column to rotate
	*/
    public int rotateColumn(Character col) {
        if ((col >= 49) && (col <= 57)) {
            int colIndex = col - 49;
            Column column = columns.get(colIndex);
            column.rotate(1);
        } else {
            return 1;
        }
        return 0;
    }

    /**
	* This method checks whether all columns are empty
    * or whether there are any cards in each columns
	* @return Boolean true or false
	*/
    public boolean isColumnsEmpty() {
        for (Column column: columns) {
            if (!column.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
	* This method overides the class' toString() method
    * @return Expected Strings
    */
    public String toString() {
        String str = "";

        str += "[Piles]\n";
        for (Map.Entry<Character, Column> entry : piles.entrySet()) {
            Character key = entry.getKey();
            Column column = entry.getValue();
            str += "Pile " + key + ": " + column.toString() + '\n';
        }

        str += "\n[Columns]\n";
        for (int i = 0; i < COLUMN_SIZE; i++) {
            str += "Column " + (i + 1) + ": " + columns.get(i).toString() + '\n';
        }
        return str;
    }
}