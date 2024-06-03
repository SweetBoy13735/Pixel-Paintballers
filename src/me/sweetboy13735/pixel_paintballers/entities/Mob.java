package me.sweetboy13735.pixel_paintballers.entities;

import static me.sweetboy13735.pixel_paintballers.Game.HEIGHT;
import static me.sweetboy13735.pixel_paintballers.Game.WIDTH;

/**
 * Represents the "skeleton" of all mobile entities in the game.
 * When a mob is created, this abstract class is responsible for providing additional fields and methods used to handle it.
 * @author Ramone Graham
 */
public abstract class Mob extends Entity {

	protected float speed, xDisplacement = 0, yDisplacement = 0;

	/**
	 * Creates a new Mob.
	 * @param type The type tag.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 * @param speed The movement speed.
	 */
	protected Mob(Type type, float x, float y, float speed) {
		super(type, x, y);

		this.speed = speed;
	}

	/** {@inheritDoc} */
	@Override
	public void tick() {
		x += speed * xDisplacement;
		y += speed * yDisplacement;

		if (x < 0) x = 0; else if (getBounds().getMaxX() > WIDTH) x = (float) (WIDTH - getBounds().getWidth());
		if (y < 0) y = 0; else if (getBounds().getMaxY() > HEIGHT) y = (float) (HEIGHT - getBounds().getHeight());
	}

	/**
	 * Gets the movement speed of the mob.
	 * @return The movement speed of the mob.
	 */
	public float getSpeed() { return speed; }

	/**
	 * Gets the X axis displacement of the mob.
	 * @return The X axis displacement of the mob.
	 */
	public float getXDisplacement() { return xDisplacement; }

	/**
	 * Gets the Y axis displacement of the mob.
	 * @return The Y axis displacement of the mob.
	 */
	public float getYDisplacement() { return yDisplacement; }

	/**
	 * Sets the movement speed of the mob.
	 * @param speed The speed to set.
	 */
	public void setSpeed(float speed) { this.speed = speed; }

	/**
	 * Sets the X axis displacement of the mob.
	 * @param displacement The X displacement to set.
	 */
	public void setXDisplacement(float displacement) { xDisplacement = displacement; }

	/**
	 * Sets the Y axis displacement of the mob.
	 * @param displacement The Y displacement to set.
	 */
	public void setYDisplacement(float displacement) { yDisplacement = displacement; }
}
