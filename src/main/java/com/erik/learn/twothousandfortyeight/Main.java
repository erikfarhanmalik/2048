package com.erik.learn.twothousandfortyeight;

import com.erik.learn.twothousandfortyeight.enums.Direction;
import com.erik.learn.twothousandfortyeight.exceptions.GameRuntimeException;
import com.erik.learn.twothousandfortyeight.modules.Renderer;
import com.erik.learn.twothousandfortyeight.modules.ConsoleRenderer;
import com.erik.learn.twothousandfortyeight.modules.TwoThousandFortyEightGame;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		TwoThousandFortyEightGame game = new TwoThousandFortyEightGame(3, 2048);
		Renderer renderer = new ConsoleRenderer(game);
		while (true) {
			renderer.renderBoard();
			Scanner s = new Scanner(System.in);
			try {
				Direction direction = Direction.of(s.nextLine());
				if (game.canMove(direction)) {
					game.move(direction);
					if (game.isCompleted()) {
						renderer.renderMessage(String.format("-= Congratulation =-\n" +
								"You beat the game, target %d reached!", game.getTarget()));
						return;
					}
					game.shuffleNewNumber();
					if (game.isGameOver()) {
						renderer.renderBoard();
						renderer.renderMessage("-= Game Over =-\n" +
								"No more move available");
						return;
					}
				} else {
					renderer.renderMessage("could not move to that direction");
				}
			} catch (GameRuntimeException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
