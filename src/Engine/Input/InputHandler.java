package Engine.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	private final boolean[] keys = new boolean[256]; // Array to track key states

	public InputHandler() {
		// Constructor can be expanded if needed
	}

	// KeyListener methods
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < keys.length) {
			keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < keys.length) {
			keys[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used but required for KeyListener
	}

	// Check if a key is currently pressed
	public boolean isKeyPressed(int keyCode) {
		return keyCode < keys.length && keys[keyCode];
	}
}
