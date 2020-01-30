import java.util.*;

/** 
* This is a program of a simple card game called Freecell
*/
public class Freecell {
	private static String getErrorMessage(int index) {
		switch(index) {
			case 1:
				return "Invalid move: Can only move from column 1 to 9";
			case 2:
				return "Invalid move: Unknown destination column";
			case 3:
				return "Invalid move: Cannot move to the same column";
			case 4:
				return "Invalid move: Tailing card rank must be 1 rank larger";
			case 5:
				return "Invalid move: Pile must follow rank order and same suit";
			case 6:
				return "Invalid move: No card to move from this column";
			case 7:
				return "Invalid move: First card in pile must be A";
		}
		return "";
	}

	/** 
	* This method initializes boolean type quit 
	* variable. Its main purpose is determining whether
	* to restart the game or quit the game.
	*/
	public static boolean quit = false;


	/** 
	* This is the main method of the Freecell program
	* @param args Unused.
	*/
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		while (!quit) {
			Table table = new Table();
			int errorIndex = 0;

			while(true) {
				System.out.println(table.toString());
				System.out.println(getErrorMessage(errorIndex));
				System.out.print("Command > ");

				char from = scan.next().charAt(0);
				if (from != 'x' && from != 'r') {
					char to = scan.next().charAt(0);
					errorIndex = table.moveCards(from, to);

					if (table.isColumnsEmpty()) {
						System.out.println("\nYou win!");
						break;
					}
				} else if (from == 'r') {
					System.out.println("\nRestarting Game . . .");
					quit = false;
					break;
				} else {
					quit = true;
					break;
				}
			}
		}
		System.out.println("End of Game . . .");
	}
}