package com.erik.learn.twothousandfortyeight.modules;

import com.erik.learn.twothousandfortyeight.enums.Direction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Getter
public class TwoThousandFortyEightGame {
	final int[][] grid;
	final int size;
	final int target;

	public TwoThousandFortyEightGame(int size, int target) {
		this.grid = new int[size][size];
		this.target = target;
		this.size = size;
		shuffleNewNumber();
		shuffleNewNumber();
	}

	TwoThousandFortyEightGame(int[][] grid, int target) {
		this.grid = grid;
		this.target = target;
		this.size = grid.length;
	}

	public void move(Direction direction) {
		Direction mergeDirection = direction;
		if (!direction.isHorizontal()) {
			mergeDirection = Direction.translateVerticalToHorizontal(direction);
		}

		switch (direction) {
			case UP:
			case DOWN:
				for (int j = 0; j < size; j++) {
					int[] row = columnToRow(j);
					row = mergeRow(row, mergeDirection);
					applyRowAsColumn(row, j);
				}
				break;
			case RIGHT:
			case LEFT:
				for (int i = 0; i < size; i++) {
					grid[i] = mergeRow(grid[i], direction);
				}
				break;
		}
	}

	public void shuffleNewNumber() {
		List<String> emptySpaceList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j] == 0) {
					emptySpaceList.add(i + " " + j);
				}
			}
		}
		if (!emptySpaceList.isEmpty()) {
			int idx = new Random().nextInt(emptySpaceList.size());
			String[] s = emptySpaceList.get(idx).split(" ");
			grid[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = 2;
		}
	}

	public boolean isCompleted() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (target == grid[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean canMove(Direction direction) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j] != 0) {
					if (canCellMove(direction, i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isGameOver() {
		for (Direction direction : Direction.values()) {
			if (canMove(direction)) {
				return false;
			}
		}
		return true;
	}

	private int[] mergeRow(int[] row, Direction direction) {
		if (!direction.isHorizontal()) {
			throw new RuntimeException("mergeRow can only be done for horizontal movement");
		}
		List<Integer> merged = new ArrayList<>();
		IntStream.of(row).filter(i -> i != 0).forEach(i -> {
			if ((merged.isEmpty() || merged.get(merged.size() - 1) != i)) {
				merged.add(i);
			} else {
				Integer lastValue = merged.remove(merged.size() - 1);
				merged.add(lastValue * 2);
			}
		});
		int[] result = new int[row.length];
		if (!merged.isEmpty()) {
			if (Direction.LEFT.equals(direction)) {
				for (int i = 0; i < merged.size(); i++) {
					result[i] = merged.get(i);
				}
			} else {
				int mergedIndexStart = row.length - merged.size();
				for (int i = mergedIndexStart; i < row.length; i++) {
					result[i] = merged.get(i - (mergedIndexStart));
				}
			}
		}
		return result;
	}

	private void applyRowAsColumn(int[] row, int j) {
		for (int i = 0; i < size; i++) {
			grid[i][j] = row[i];
		}
	}

	private int[] columnToRow(int j) {
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = grid[i][j];
		}
		return result;
	}

	private boolean canCellMove(Direction direction, int i, int j) {
		int vBefore = i - 1;
		int vAfter = i + 1;
		int hBefore = j - 1;
		int hAfter = j + 1;
		int lastIndex = size - 1;

		switch (direction) {
			case UP:
				if (i <= 0) return false;
				return grid[vBefore][j] == 0 || grid[vBefore][j] == grid[i][j];
			case RIGHT:
				if (j >= lastIndex) return false;
				return grid[i][hAfter] == 0 || grid[i][hAfter] == grid[i][j];
			case DOWN:
				if (i >= lastIndex) return false;
				return grid[vAfter][j] == 0 || grid[vAfter][j] == grid[i][j];
			case LEFT:
				if (j <= 0) return false;
				return grid[i][hBefore] == 0 || grid[i][hBefore] == grid[i][j];
			default:
				throw new RuntimeException("invalid direction");
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j] == 0) {
					builder.append(" - ");
				} else {
					builder.append(String.format(" %d ", grid[i][j]));
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
