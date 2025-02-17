package Lib.Core;

import javax.swing.*;

public class Window {
	public int screenWidth;
	public int screenHeight;
	public boolean isFullScreen;
	public String windowName;

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
		this.screenHeight = screenHeight;
	}

	public boolean isFullScreen() {
		return isFullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		isFullScreen = fullScreen;
	}

	public String getWindowName() {
		return windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public void createWindow(int screenWidth, int screenHeight, String windowName) {
		JFrame jframe = new JFrame(windowName);
		jframe.setSize(screenWidth, screenHeight);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);

	}

}