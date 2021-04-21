package view;

import java.util.List;

public interface MenuItem {
	
	int getAmount();
	
	void setAmount(int amount);
	
	int getSlotIndex();

	void setSlotIndex(int slotIndex);
	
	String getName();
	
	void setName(String name);
	
	String getIcon();
	
	void setIcon(String icon);
	
	ActionHandler getActionHandler();
	
	void setActionHandler(ActionHandler actionHandler);
	
	void addLoreLine(String line);
	
	List<String> getLore();
	
}
