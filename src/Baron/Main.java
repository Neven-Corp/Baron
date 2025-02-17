package Baron;

import Baron.Core.*;
public class Main {
	public static void main(String[] args) {
		GameLoop gameLoop = new GameLoop(60, new GameLoop.GameCallback() {
			@Override
			public void update() {
				System.out.println("Update");
			}
			@Override
			public void render() {
				System.out.println("Render");
			}
		});
		gameLoop.start();
	}
}
