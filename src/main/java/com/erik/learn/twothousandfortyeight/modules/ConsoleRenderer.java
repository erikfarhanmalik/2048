package com.erik.learn.twothousandfortyeight.modules;

public class ConsoleRenderer implements Renderer {
	private final TwoThousandFortyEightGame game;

	public ConsoleRenderer(TwoThousandFortyEightGame game) {
		this.game = game;
	}

	@Override
	public void renderBoard() {
		System.out.println();
		StringBuilder builder = new StringBuilder();
		drawBorder(builder, "╔", "╗\n");
		for (int i = 0; i < game.getSize(); i++) {
			builder.append("║");
			for (int j = 0; j < game.getSize(); j++) {
				if (game.getGrid()[i][j] == 0) {
					builder.append(" - ");
				} else {
					builder.append(String.format(" %d ", game.getGrid()[i][j]));
				}
			}
			builder.append("║\n");
		}
		drawBorder(builder, "╚", "╝");
		System.out.println(builder.toString());
	}

	private void drawBorder(StringBuilder builder, String s, String s2) {
		builder.append(s);
		for (int i = 0; i < game.size; i++) {
			builder.append("═══");
		}
		builder.append(s2);
	}

	@Override
	public void renderMessage(String message) {
		System.out.println();
		System.out.println(message);
	}
}
