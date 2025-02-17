package Baron.Core;

public class GameLoop {
	private boolean running = false;
	private int fps;
	private double timePerFrame;
	private GameCallback callback;
	private Thread gameThread;

	// Callback interface for update & render
	public interface GameCallback {
		void update();
		void render();
	}

	// Constructor
	public GameLoop(int fps, GameCallback callback) {
		this.fps = fps;
		this.timePerFrame = 1_000_000_000.0 / fps; // Nanoseconds per frame
		this.callback = callback;
	}

	// Start the game loop
	public void start() {
		if (running) return;
		running = true;
		gameThread = new Thread(this::runLoop);
		gameThread.start();
	}

	// Stop the game loop
	public void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// The main game loop
	private void runLoop() {
		long lastTime = System.nanoTime();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / timePerFrame;
			lastTime = now;

			while (delta >= 1) {
				callback.update();
				delta--;
			}

			callback.render();

			try {
				Thread.sleep(1); // Prevents CPU overload
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
