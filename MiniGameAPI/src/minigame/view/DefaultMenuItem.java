package minigame.view;

import java.util.ArrayList;
import java.util.List;

public class DefaultMenuItem implements MenuItem {
	
	private int amount;
	private int slotIndex;
	private String name;
	private String icon;
	private ActionHandler actionHandler;
	private List<String> lore;
	
	public DefaultMenuItem() {
		setAmount(1);
		setIcon("white_wool");
		setName("Untitled");
		lore = new ArrayList<String>();
	}
	
	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int getSlotIndex() {
		return slotIndex;
	}

	@Override
	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public ActionHandler getActionHandler() {
		return actionHandler;
	}

	@Override
	public void setActionHandler(ActionHandler clickHandler) {
		this.actionHandler = clickHandler;
	}

	@Override
	public void addLoreLine(String line) {
		lore.add(line);	
	}

	@Override
	public List<String> getLore() {
		return lore;
	}
		
}
