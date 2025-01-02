package Engine.Entities;

import java.awt.*;

public class Enemy {
	private int x, y, width, height;
	private int speed; // Speed in pixels per second
	private int direction; // 1 for right, -1 for left

	public Enemy(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.direction = 1; // Start moving right
	}

	// Update enemy position
	public void update(double deltaTime, int canvasWidth) {
		x += speed * direction * deltaTime;

		// Reverse direction if the enemy hits the screen boundary
		if (x < 0 || x + width > canvasWidth) {
			direction *= -1;
		}
	}

	// Render the enemy
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

	// Check for collisions with player
	public boolean isColliding(int playerX, int playerY, int playerHeight, int playerWidth) {
		return playerX < x + width && playerX + playerWidth > x && playerY < y + height && playerY + playerHeight > y;
	}

	// Getters and setters for posistion and size
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
