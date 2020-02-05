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

			System.out.println("=============================================================");
			System.out.println("New Game");
			System.out.println("Command > <Column Number> <Column Number>||<Pile Character>");
			System.out.println();
			System.out.println("Press c to column rotate");
			System.out.println("Command > c <Column Number>");
			System.out.println();
			System.out.println("Press r to restart game");
			System.out.println("Command > r");
			System.out.println();
			System.out.println("Press x to quit game");
			System.out.println("Command > x");
			System.out.println("=============================================================\n");

			while(true) {
				System.out.println(table.toString());
				System.out.println(getErrorMessage(errorIndex));
				System.out.print("Command > ");

				char from = scan.next().toLowerCase().charAt(0);
				if (from == 'c') {
					char col = scan.next().toLowerCase().charAt(0);
					errorIndex = table.rotateColumn(col);
				} else if (from == 'r') {
					System.out.println("\nRestarting Game . . .");
					quit = false;
					break;
				} else if (from == 'x') {
					quit = true;
					break;
				} else {
					char to = scan.next().toLowerCase().charAt(0);
					errorIndex = table.moveCards(from, to);

					if (table.isColumnsEmpty()) {
						System.out.println("\n=============================");
						System.out.println("You win (^o^)");
						System.out.println("=============================\n");
						break;
					}
				}
			}
		}
		System.out.println("\n=============================");
		System.out.println("Game ended . . .");
		System.out.println("Thank You For Playing (^0^)");
		System.out.println("=============================\n");
	}
}