package me.sweetboy13735.pixel_paintballers;

import static java.awt.Color.BLACK;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import me.sweetboy13735.pixel_paintballers.ui.Window;

/**
 * Represents the core of the game.
 * When the program is executed, this class is responsible for handling the game's main functions (E.g. {@code tick} and {@code render}).
 * @author Ramone Graham
 */
public final class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final String TITLE = "Pixel Paintballers";
	public static final int WIDTH = 800, HEIGHT = WIDTH / 4 * 3;

	private final Window window;

	private boolean running = false;
	private transient Thread thread;

	/** Creates a new Game instance. */
	public Game() {
		Dimension dimension = new Dimension(WIDTH, HEIGHT);

		setMinimumSize(dimension);
		setMaximumSize(dimension);
		setPreferredSize(dimension);

		window = new Window(this);
	}

	/**
	 * Starts execution of the program (Called by the JVM).
	 * @param args Additional arguments to start the game.
	 */
	public static void main(String[] args) { new Game(); }

	/** Starts execution of the game. */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, TITLE + " (Main)");

		thread.start();
	}

	/** Stops execution of the game. */
	public synchronized void stop() {
		running = false;

		try { thread.join(); } catch (InterruptedException exception) { exception.printStackTrace(); }
	}
	
	/** Updates the logic of the game. */
	private void tick() {}

	/** Updates the visuals of the game. */
	private void render() {
		BufferStrategy strategy = getBufferStrategy();
		
		if (strategy == null) {
			createBufferStrategy(3);

			return;
		}

		Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();

		graphics.setColor(BLACK);
		graphics.fill(getBounds());

		graphics.dispose();
		strategy.show();
	}

	/** {@inheritDoc} */
	@Override
	public void run() {
		requestFocus();

		double desiredTPS = 64, nanosecsPerTick = 1000000000 / desiredTPS, delta = 0;
		int tps = 0, fps = 0;
		long timer = System.currentTimeMillis(), lastTime = System.nanoTime();

		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / nanosecsPerTick;
			lastTime = now;

			while (delta >= 1) {
				tick();

				delta--;
				tps++;
			}

			if (running) render();

			fps++;

			if (System.currentTimeMillis()-timer >= 1000) {
				System.out.println("TPS: " + tps + ". FPS: " + fps + ".");

				timer += 1000;
				tps = 0;
				fps = 0;
			}
		}

		stop();
	}
}
