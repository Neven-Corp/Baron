package Engine;

import Engine.Core.EngineConfig;
import Engine.Core.GameLoop;
import Engine.Core.GameWindow;

public class Main {
	public static void main(String[] args) {
		// Initialize engine configuration
		EngineConfig config = new EngineConfig(1920, 1080, false, 60);

		// Create and display the game window
		GameWindow gameWindow = new GameWindow("My game", config);

		// Start the game loop
		GameLoop gameLoop = new GameLoop();
		gameLoop.start(gameWindow);
	}
}