package Engine.Core;

import Engine.Input.InputHandler;
import Engine.Utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.security.Key;

public class GameLoop {
	private int playerLocationX = 100;
	private int playerLocationY = 100;
	private final int playerWidth = 50;
	private final int playerHeight = 50;
	private boolean running = true; // Determining if the game loop is running
	private final int targetFps; // Target FPS
	private final long frameTime; // time per frame in nanoseconds
	private InputHandler inputHandler;

	public GameLoop() {
		this.targetFps = 60;
		this.frameTime = 1_000_000_000 / targetFps; // Convert fps to nanoseconds
	}

	public void start(GameWindow window) {
		this.inputHandler = window.getInputHandler(); // Acces inputhandeler

		Logger.log("GameLoop", "Starting Game Loop....");
		long lastTime = System.nanoTime();
		long deltaTime; // Time since last frame

		while (running) {
			long now = System.nanoTime();
			deltaTime = now - lastTime; // Time between frames
			lastTime = now;

			// Update game logic
			update(deltaTime / 1_000_000_000.0); // Convert nanoseconds to seconds

			// Render game
			render(window);

			// Frame timing control
			long elapsedTime = System.nanoTime() - now;
			long sleepTime = frameTime - elapsedTime;
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime / 1_000_000); // Convert to milliseconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void update(double deltaTime) {
		//TODO Initialize game resources here
		Logger.log("GameLoop", "Rendering game with deltaTime: " + deltaTime);

		if (inputHandler.isKeyPressed(KeyEvent.VK_W)) {
			Logger.log("GameLoop", "W is pressed");
			playerLocationY -= 200 * deltaTime;
		}
		if (inputHandler.isKeyPressed(KeyEvent.VK_S)) {
			Logger.log("GameLoop", "S is pressed");
			playerLocationY += 200 * deltaTime;
		}
		if (inputHandler.isKeyPressed(KeyEvent.VK_A)) {
			playerLocationX -= 200 * deltaTime; // Move left
		}
		if (inputHandler.isKeyPressed(KeyEvent.VK_D)) {
			playerLocationX += 200 * deltaTime; // Move right
		}
	}

	private void render(GameWindow window) {
		// Get canvas graphics context
		Canvas canvas = window.getCanvas();
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();

		if (bufferStrategy == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		// Clear the screen
		g.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

		// Draw a rectangle
		g.setColor(Color.blue);
		g.fillRect(playerLocationX, playerLocationY, playerWidth, playerHeight);

		// Dispose of Graphics and show the buffer
		g.dispose();
		bufferStrategy.show();
		Logger.log("GameLoop", "Rendering Game....");
	}

	private void cleanup() {
		//TODO Free resources
		Logger.log("GameLoop", "Cleaning up");
	}

	public void stop() {
		running = false;
		Logger.log("GameLoop", "Game Loop Stopped.");
	}
}

