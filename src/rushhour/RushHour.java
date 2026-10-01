package rushhour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;

public class Car {
	public int x;
	public int y;
	public char name;
	public int direction;
}

public class RushHour
{
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;

	public final static int VERTICAL = 0;
	public final static int HORIZONTAL = 1;

	public final static int SIZE = 6;

	ArrayList<Car> cars = new ArrayList<Car>();
	
	/**
	 * @param fileName
	 * Reads a board from file and creates the board
	 * @throws Exception if the file not found or the board is bad
	 */
	public RushHour(String fileName)throws FileNotFoundException{
		File myObj = new File("filename.txt");
		Map<String, Car> cars_found = new HashMap<>();

		// try-with-resources: Scanner will be closed automatically
		try (Scanner myReader = new Scanner(myObj)) {
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				for (char ch : str.toCharArray()) {
					System.out.println(ch);
				}
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * @param carName
	 * @param dir
	 * @param length
	 * Moves car with the given name for length steps in the given direction  
	 * @throws IllegalMoveException if the move is illegal
	 */
	public void makeMove(char carName, int dir, int length) throws IllegalMoveException {
	}
	
	/**
	 * @return true if and only if the board is solved,
	 * i.e., the XX car is touching the right edge of the board
	 */
	public boolean isSolved() {
		return false;
	}
	
}
