package Lib.Input;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {
	private final Set<Integer> pressedKeys = new HashSet<>();
	private final Set<Integer> pressedMouseButtons= new HashSet<>();

	private int mouseX = 0, mouseY = 0;

	public void inputManager() {
	}

	// --Keyboard Events--
	@Override
	public void keyPressed(KeyEvent e) {
		pressedKeys.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// --Mouse Events--

	@Override
	public void mousePressed(MouseEvent e) {
		pressedMouseButtons.add(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressedMouseButtons.remove(e.getButton());
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Optional
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Optional
	}

	public boolean isKeyPressed(int keyCode) {
		return pressedKeys.contains(keyCode);
	}

	public boolean isMousePressed(int mouseButton) {
		return pressedMouseButtons.contains(mouseButton);
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
}
