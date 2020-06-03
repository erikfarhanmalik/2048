package com.erik.learn.twothousandfortyeight.modules;

import com.erik.learn.twothousandfortyeight.enums.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoThousandFortyEightGameTest {

	@Test
	public void BoardIsInitializedWithValidSizeAndTarget() {
		TwoThousandFortyEightGame board = new TwoThousandFortyEightGame(4, 2048);
		assertEquals(4, board.size);
		assertEquals(2048, board.target);
	}

	@Test
	public void BoardIsInitializedWithTwoOfNumberTwoInRandomLocation() {
		int size = 4;
		int target = 2048;
		TwoThousandFortyEightGame board = new TwoThousandFortyEightGame(size, target);

		int zeroCount = 0;
		int twoCount = 0;
		for (int i = 0; i < board.size; i++) {
			for (int j = 0; j < board.size; j++) {
				if (board.grid[i][j] == 0) {
					zeroCount++;
				} else if (board.grid[i][j] == 2) {
					twoCount++;
				}
			}
		}
		assertEquals(2, twoCount);
		assertEquals(size * size, zeroCount + twoCount);
	}

	@Test
	public void moveLeft() {
		int[][] grid = {
				{0, 2, 0, 2},
				{2, 2, 0, 0},
				{0, 2, 2, 0},
				{2, 2, 2, 2},
		};
		int[][] expectedGrid = {
				{4, 0, 0, 0},
				{4, 0, 0, 0},
				{4, 0, 0, 0},
				{4, 4, 0, 0},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		actual.move(Direction.LEFT);
		TwoThousandFortyEightGame expected = new TwoThousandFortyEightGame(expectedGrid, 16);
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void moveRight() {
		int[][] grid = {
				{0, 2, 0, 2},
				{2, 2, 0, 0},
				{0, 2, 2, 0},
				{2, 2, 2, 2},
		};
		int[][] expectedGrid = {
				{0, 0, 0, 4},
				{0, 0, 0, 4},
				{0, 0, 0, 4},
				{0, 0, 4, 4},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		actual.move(Direction.RIGHT);
		TwoThousandFortyEightGame expected = new TwoThousandFortyEightGame(expectedGrid, 16);
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void moveUp() {
		int[][] grid = {
				{0, 2, 0, 2},
				{2, 2, 0, 0},
				{0, 2, 2, 0},
				{2, 2, 2, 2},
		};
		int[][] expectedGrid = {
				{4, 4, 4, 4},
				{0, 4, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		actual.move(Direction.UP);
		TwoThousandFortyEightGame expected = new TwoThousandFortyEightGame(expectedGrid, 16);
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void moveDown() {
		int[][] grid = {
				{0, 2, 0, 2},
				{2, 2, 0, 0},
				{0, 2, 2, 0},
				{2, 2, 2, 2},
		};
		int[][] expectedGrid = {
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 4, 0, 0},
				{4, 4, 4, 4},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		actual.move(Direction.DOWN);
		TwoThousandFortyEightGame expected = new TwoThousandFortyEightGame(expectedGrid, 16);
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void canStillPlay() {
		int[][] grid = {
				{8, 4, 2},
				{8, 4, 2},
				{8, 4, 2},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		assertFalse(actual.isCompleted());
	}

	@Test
	public void isGameOver() {
		int[][] grid = {
				{2, 4, 8},
				{4, 8, 2},
				{8, 2, 4},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		assertTrue(actual.isGameOver());
	}

	@Test
	public void isComplete() {
		int[][] grid = {
				{2, 4, 16},
				{4, 8, 2},
				{8, 2, 4},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		assertTrue(actual.isCompleted());
	}

	@Test
	public void isNotComplete() {
		int[][] grid = {
				{2, 4, 8},
				{4, 8, 2},
				{8, 2, 4},
		};
		TwoThousandFortyEightGame actual = new TwoThousandFortyEightGame(grid, 16);
		assertFalse(actual.isCompleted());
	}
}