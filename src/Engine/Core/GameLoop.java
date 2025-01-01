package Engine.Core;

import Engine.Input.InputHandler;
import Engine.Utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;


public class GameLoop {
	// Gravity and velocity
	private double gravity = 500; // Strength of gravity (pixels per second squared)
	private double verticalVelocity = 0; // Current speed in the vertical direction
	private boolean onGround = false; // Tracks if the rectangle is on the ground
	// Floor variables
	private int floorX = 0;
	private int floorY = 1020;
	private int floorWidth = 1920;
	private int floorHeigth = 60;
	// Player variables
	private int playerLocationX = 100;
	private int playerLocationY = 100;
	private final int playerWidth = 50;
	private final int playerHeight = 50;
	// Game loop variables
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
		int speed = 200; // Horizontal movement speed, pixels per second

		// Move horizontaly based on inputs
		if (inputHandler.isKeyPressed(KeyEvent.VK_A)) {
			playerLocationX -= speed * deltaTime; // move left
		}
		if (inputHandler.isKeyPressed(KeyEvent.VK_D)) {
			playerLocationX += speed * deltaTime; // move right
		}

		// Apply gravity when not on the ground
		if (!onGround) {
			verticalVelocity += gravity * deltaTime; // Increase downwards speed
		} else {
			verticalVelocity = 0; // reset vertical speed while on the ground
		}

		// update vertical position
		playerLocationY += verticalVelocity * deltaTime;

		// Horizontal boundary checks
		int canvasWidth = 1920;
		if (playerLocationX < 0) {
			playerLocationX = 0;
		}
		if (playerLocationX + playerWidth > canvasWidth) {
			playerLocationX = canvasWidth - playerWidth;
		}

		// Floor collision detection
		if (playerLocationY + playerHeight > floorY) {
			playerLocationY = floorY - playerHeight;
			onGround = true;
		} else {
			onGround = false;
		}

		// Jumping logic
		if (inputHandler.isKeyPressed(KeyEvent.VK_W) && onGround) {
			verticalVelocity = - 300;
			onGround = false;
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

		// Draw a rectangle PLAYER
		g.setColor(Color.blue);
		g.fillRect(playerLocationX, playerLocationY, playerWidth, playerHeight);

		// Draw a floor
		g.setColor(Color.green);
		g.fillRect(floorX, floorY, floorWidth, floorHeigth);

		// Dispose of Graphics and show the buffer
		g.dispose();
		bufferStrategy.show();
	//	Logger.log("GameLoop", "Rendering Game....");
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

