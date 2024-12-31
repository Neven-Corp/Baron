package Engine.Core;

public class GameLoop {
	private boolean running = true; // Determining if the game loop is running
	private final int fps = 60; // Target FPS
	private final long frameTime = 1000 / fps; // Frame duration in miliseconds

	public void start() {
		init();

		// Main game loop
		while (running) {
			long startTime = System.currentTimeMillis();

			update();
			render();

			// Frame rate control
			long elapsedTime = System.currentTimeMillis() - startTime;
			long sleepTime = frameTime - elapsedTime;

			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		cleanup();
	}

	private void init() {
		//TODO Initialize game resources here
	}

	private void update() {
		//TODO  Update game logic
	}

	private void render() {
		//TODO Render the game
	}

	private void cleanup() {
		//TODO Free resources
		System.out.println();
	}

	public void stop() {
		running = false;
	}

	public static void main(String[] args) {
		GameLoop game = new GameLoop();
		game.start();
	}

}