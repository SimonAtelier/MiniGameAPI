package minigame.entities;

import java.util.UUID;

public class Hologram {

	private double x;
	private double y;
	private double z;
	private boolean textVisible;
	private boolean holderVisible;
	private String holderHead;
	private String name;
	private String world;
	private UUID uniqueId;
	private String text;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public boolean isTextVisible() {
		return textVisible;
	}

	public void setTextVisible(boolean textVisible) {
		this.textVisible = textVisible;
	}

	public boolean isHolderVisible() {
		return holderVisible;
	}

	public void setHolderVisible(boolean holderVisible) {
		this.holderVisible = holderVisible;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHolderHead() {
		return holderHead;
	}

	public void setHolderHead(String holderHead) {
		this.holderHead = holderHead;
	}

}
