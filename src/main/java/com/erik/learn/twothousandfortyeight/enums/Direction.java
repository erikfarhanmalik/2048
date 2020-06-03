package com.erik.learn.twothousandfortyeight.enums;

import com.erik.learn.twothousandfortyeight.exceptions.GameRuntimeException;

public enum Direction {
	UP,
	RIGHT,
	DOWN,
	LEFT;

	public static Direction of(String direction) {
		switch (direction) {
			case "w":
				return Direction.UP;
			case "d":
				return Direction.RIGHT;
			case "s":
				return Direction.DOWN;
			case "a":
				return Direction.LEFT;
			default:
				throw new GameRuntimeException("invalid direction");
		}
	}

	public static Direction translateVerticalToHorizontal(Direction direction) {
		if (direction.isHorizontal()) {
			throw new RuntimeException("direction is already horizontal");
		}

		if (UP.equals(direction)) {
			return LEFT;
		} else if (DOWN.equals(direction)) {
			return RIGHT;
		}

		throw new RuntimeException("invalid direction fro translation");
	}

	public boolean isHorizontal() {
		return this.equals(Direction.LEFT) || this.equals(Direction.RIGHT);
	}
}
