package me.sweetboy13735.pixel_paintballers.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Represents the "skeleton" of all entities in the game.
 * When an entity is created, this abstract class is responsible for providing fields and methods used to handle it.
 * @author Ramone Graham
 */
public abstract class Entity {

	/**
	 * Represents the type tag of an entity.
	 * When utilised, this enumeration is reponsible for distinguishing the types of entities in the game.
	 * @author Ramone Graham
	 */
	public enum Type {

		PLAYER, PAINTBALL, TRAIL;
	}

	protected final Type type;
	protected float x, y;

	/**
	 * Creates a new Entity.
	 * @param type The type tag.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	protected Entity(final Type type, float x, float y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	/** Updates the logic of the entity. */
	public abstract void tick();

	/**
	 * Updates the visuals of the entity.
	 * @param graphics The graphics context to render with.
	 */
	public abstract void render(Graphics2D graphics);

	/**
	 * Gets the boundaries of the entity.
	 * @return The boundaries of the entity.
	 */
	public abstract Rectangle getBounds();

	/**
	 * Gets the entity type tag.
	 * @return The entity type.
	 */
	public Type getType() { return type; }

	/**
	 * Gets the X coordinate of the entity.
	 * @return The X coordinate.
	 */
	public float getX() { return x; }

	/**
	 * Gets the Y coordinate of the entity.
	 * @return The Y coordinate.
	 */
	public float getY() { return y; }

	/**
	 * Sets the X coordinate of the entity.
	 * @param x The X coordinate to set.
	 */
	public void setX(float x) { this.x = x; }

	/**
	 * Sets the Y coordinate of the entity.
	 * @param y The Y coordinate to set.
	 */
	public void setY(float y) { this.y = y; }
}
