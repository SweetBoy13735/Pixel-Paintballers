package me.sweetboy13735.pixel_paintballers.managers;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import me.sweetboy13735.pixel_paintballers.entities.Entity;

/**
 * Represents the Entity manager of the game.
 * When initialised, this class is responsible for handling all running entities.
 */
public class EntityManager {

	private static final List<Entity> entities = Collections.synchronizedList(new LinkedList<>());

	/** Updates the logic of all running entities. */
	public static void tick() { for (int i = 0; i < entities.size(); i++) { entities.get(i).tick(); }}

	/**
	 * Updates the visuals of all running entities.
	 * @param graphics The graphics context to render with.
	 */
	public static void render(Graphics2D graphics) { for (int i = 0; i < entities.size(); i++) { entities.get(i).render(graphics); }}

	/**
	 * Adds an entity to the running list.
	 * @param entity The entity to add.
	 */
	public static void addEntity(Entity entity) { entities.add(entity); }

	/**
	 * Removes an entity from the running list.
	 * @param entity The entity to remove.
	 */
	public static void removeEntity(Entity entity) { entities.remove(entity); }

	/**
	 * Gets the list of running entities.
	 * @return The list of running entities.
	 */
	public static List<Entity> getEntities() { return entities; }
}
