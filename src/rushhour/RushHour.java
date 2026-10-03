package rushhour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;

class Car {
	public int x;
	public int y;
	public String name;
	public int length;
	public int direction;

	public Car(String carName, int xPos, int yPos, int carDirection) {
		name = carName;
		x = xPos;
		y = yPos;
		direction = carDirection;
		length = 1;
	}
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

	Map<String, Car> cars = new HashMap<>();
	
	/**
	 * @param fileName
	 * Reads a board from file and creates the board
	 * @throws Exception if the file not found or the board is bad
	 */
	public RushHour(String fileName)throws FileNotFoundException{
		File myObj = new File(fileName);
		Map<String, Car> cars_found = new HashMap<>();

		// try-with-resources: Scanner will be closed automatically
		int y = 0;
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			Map<String, Car> cars_on_this_line = new HashMap<>();

			int x = 0;
			for (char ch : data.toCharArray()) {
				if (ch == '.' || ch == ' ') {
						x++;
						continue;
				}
				String tile = String.valueOf(ch);

				if(cars_on_this_line.containsKey(tile)) {
					Car car = cars_found.get(tile);
					car.direction = HORIZONTAL;
					car.length++;
					
				} else {
					if(!cars_found.containsKey(tile)) {
						Car car = new Car(tile, x, y, VERTICAL);
						cars_found.put(tile, car);
						cars_on_this_line.put(tile, car);
					} else {
						Car car = cars_found.get(tile);
						car.length++;
					}
				}
				x++;
			}
			y++;
		}
		this.cars = cars_found;
	}
	 
	public boolean testCollision(int start_x, int start_y, int end_x, int end_y, String ignore_name) {
			// Determine the step directions along x and y (-1, 0, or 1)
			int dx = Integer.compare(end_x, start_x);
			int dy = Integer.compare(end_y, start_y);

			int currentX = start_x;
			int currentY = start_y;

			while (true) {
					for (Car car : this.cars.values()) {
						if (car.name.equals(ignore_name)) continue;
							for (int i = 0; i < car.length; i++) {
									int carCellX = car.x + (car.direction == HORIZONTAL ? i : 0);
									int carCellY = car.y + (car.direction == VERTICAL ? i : 0);

									if (carCellX == currentX && carCellY == currentY) {
											return true; // Collision detected
									}
							}
					}

					// Got to the end with no collison
					if (currentX == end_x && currentY == end_y) {
							break;
					}

					currentX += dx;
					currentY += dy;
			}

			return false; // Path is clear
	}
	
	/**
	 * @param carName
	 * @param dir
	 * @param length
	 * Moves car with the given name for length steps in the given direction  
	 * @throws IllegalMoveException if the move is illegal
	 */
	public void makeMove(char ch, int dir, int length) throws IllegalMoveException {
		String carName = String.valueOf(ch);
		Car car = this.cars.get(carName);
		if (car == null) {
			throw new IllegalMoveException("Car " + carName + " does not exist.");
		}


		int startX = car.x;
    int startY = car.y;
    int targetX = car.x;
    int targetY = car.y;
		int pos = car.y;

		if(car.direction == HORIZONTAL) {
			pos = car.x;
		}

		if (car.direction == HORIZONTAL && (dir == UP || dir == DOWN)) {
			throw new IllegalMoveException("Horizontal cars cannot move vertically.");
		}
		if (car.direction == VERTICAL && (dir == LEFT || dir == RIGHT)) {
			throw new IllegalMoveException("Vertical cars cannot move horizontally.");
		}

		if(dir == DOWN) {
			if(pos + length + car.length > SIZE) {
				throw new IllegalMoveException("That is too far Down");
			} 
			startY = car.y + car.length; 
      targetY = car.y + car.length + length - 1;
		}
		if(dir == UP)  {
			if(pos - length < 0) {
				throw new IllegalMoveException("That is too far Up");
			}
			startY = car.y - 1;
      targetY = car.y - length;
		}
		if(dir == RIGHT) {
			if(pos + car.length + length > SIZE) {
				throw new IllegalMoveException("That is too far Right");
			}
			startX = car.x + car.length; 
      targetX = car.x + car.length + length - 1;
		}
		if(dir == LEFT)  {
			if(pos - length < 0) {
				throw new IllegalMoveException("That is too far Left");
			}
			startX = car.x - 1;
      targetX = car.x - length;
		}

		//Check other cars in path
		boolean collision = testCollision(startX, startY, targetX, targetY, car.name);
		if (collision) {
        throw new IllegalMoveException("Path blocked by another car.");
    }

		if (dir == RIGHT) car.x += length;
    else if (dir == LEFT) car.x -= length;
    else if (dir == DOWN) car.y += length;
    else if (dir == UP) car.y -= length;
	}
	
	/**
	 * @return true if and only if the board is solved,
	 * i.e., the XX car is touching the right edge of the board
	 */
	public boolean isSolved() {
    Car redCar = this.cars.get("X");
    if (redCar == null) return false;
    return (redCar.x + redCar.length) == SIZE;
	}	
}
