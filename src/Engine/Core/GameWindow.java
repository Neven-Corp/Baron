package Engine.Core;

import Engine.Input.InputHandler;
import Engine.Utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntUnaryOperator;

public class GameWindow {
	private JFrame frame;
	private Canvas canvas;
	private InputHandler inputHandler;

	public GameWindow(String title, EngineConfig config) {
		// Initialize the jframe
		frame = new JFrame(title);

		// Configuring the jframe
		frame.setSize(config.getScreenWidth(), config.getScreenHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(config.isFullscreen());

		// Add canvas for drawing
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(config.getScreenWidth(), config.getScreenHeight()));
		canvas.setFocusable(true);
		canvas.requestFocus();
		frame.add(canvas);
		frame.pack();

		// Handle input
		inputHandler = new InputHandler();
		canvas.addKeyListener(inputHandler); // Attach inputhandeler to canvas
		// Show the frame
		frame.setVisible(true);

		// Debug log
		if (config.isDebugMode()) {
			Logger.log("GameWindow", "Window Initialized with title" + title);
		}
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
}
