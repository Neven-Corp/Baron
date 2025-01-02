package Engine.Core;

import Engine.Entities.Enemy;
import Engine.Input.InputHandler;
import Engine.Utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;


public class GameLoop {
	// Enemy's
	private Enemy[] enemies = {
			new Enemy(500, 960, 40, 40, 100), // Enemy 1
			new Enemy(900, 760, 40, 40, 150), // Enemy 2
			new Enemy(1300, 560, 40, 40, 120) // Enemy 3
	};
	// Platform properties
	private Rectangle[] platforms = {
			new Rectangle(400, 800, 200, 20), //1
			new Rectangle(800,600, 300,20), //2
			new Rectangle(1200, 400, 150, 20) //3
	};
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

		// Platform collisions
		for (Rectangle platform : platforms) {
			if (playerLocationY + playerHeight > platform.y &&
					playerLocationY < platform.y + platform.height &&
					playerLocationX + playerWidth > platform.x &&
					playerLocationX < platform.x + platform.width) {

				// Colision detected; snap to top of the platform
				playerLocationY = platform.y - playerHeight;
				onGround = true;
				break;
			}
		}

		// Enemy logic
		for (Enemy enemy : enemies) {
			enemy.update(deltaTime, canvasWidth);
		}
		// Enemy colisions
		for (Enemy enemy : enemies) {
			if (enemy.isColliding(playerLocationX, playerLocationY, playerHeight, playerWidth)) {
				System.out.println("Colision deteced");
				running = false;
			}
		}

		// Jumping logic
		if (inputHandler.isKeyPressed(KeyEvent.VK_W) && onGround) {
			verticalVelocity = - 500;
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

		// Draw the platforms
		g.setColor(Color.yellow);
		for (Rectangle platform : platforms) {
			g.fillRect(platform.x, platform.y, platform.width, platform.height);
		}

		// Render the enemy's
		for (Enemy enemy : enemies) {
			enemy.render(g);
		}

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

