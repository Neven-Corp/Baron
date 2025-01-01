package Engine.Core;

import Engine.Utils.Logger;

public class EngineConfig {
	private int screenWidth;
	private int screenHeight;
	private boolean fullscreen;
	private int targetFPS;
	private boolean debugMode;

	public EngineConfig() {
		// Default constructor
	}

	public EngineConfig(int screenWidth, int screenHeight, boolean fullscreen, int targetFPS) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.fullscreen = fullscreen;
		this.targetFPS = 60;
	}

	// Getters and setters
	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenWidth = screenHeight;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public int getTargetFPS() {
		return targetFPS;
	}

	public void setTargetFPS(int targetFPS) {
		this.targetFPS = targetFPS;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

}
