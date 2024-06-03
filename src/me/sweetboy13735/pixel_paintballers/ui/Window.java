package me.sweetboy13735.pixel_paintballers.ui;

import static java.awt.BorderLayout.CENTER;
import static me.sweetboy13735.pixel_paintballers.Game.TITLE;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import me.sweetboy13735.pixel_paintballers.Game;

/**
 * Represents the window frame of the game.
 * When constructed, this class is responsible for handling the game's window and any changes to its state.
 * @author Ramone Graham
 */
public final class Window extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;

	private final Game game;

	private transient GraphicsDevice screen;
	private transient List<DisplayMode> displayModes = new ArrayList<>();
	private boolean fullscreen = false;

	/**
	 * Creates a new Window.
	 * @param game The {@link Game} visuals to add to the window.
	 */
	public Window(final Game game) {
		super(TITLE);

		this.game = game;

		screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		for (DisplayMode mode : screen.getDisplayModes()) if (mode.getWidth() == Game.WIDTH && mode.getHeight() == Game.HEIGHT) displayModes.add(mode);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		add(game, CENTER);
		addWindowListener(this);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		game.start();
	}

	/** {@inheritDoc} */
	@Override
	public void windowOpened(WindowEvent e) {}

	/** {@inheritDoc} */
	@Override
	public void windowClosing(WindowEvent e) {}

	/** {@inheritDoc} */
	@Override
	public void windowClosed(WindowEvent e) {}

	/** {@inheritDoc} */
	@Override
	public void windowIconified(WindowEvent e) {}

	/** {@inheritDoc} */
	@Override
	public void windowDeiconified(WindowEvent e) {}

	/** {@inheritDoc} */
	@Override
	public void windowActivated(WindowEvent e) {}

	/** {@inheritDoc} */
	@Override
	public void windowDeactivated(WindowEvent e) {}

	/** Toggles the window's fullscreen mode. */
	public void toggleFullscreen() {
		if (!screen.isFullScreenSupported()) return;

		fullscreen = !fullscreen;

		dispose();
		setUndecorated(fullscreen);
		pack();

		screen.setFullScreenWindow(fullscreen ? this : null);

		if (!fullscreen) {
			setLocationRelativeTo(null);
			setVisible(true);
		} else if (screen.isDisplayChangeSupported() && !displayModes.isEmpty()) screen.setDisplayMode(displayModes.getFirst());

		game.requestFocus();
	}
}
