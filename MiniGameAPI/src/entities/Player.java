package entities;

import java.util.UUID;

public class Player {
	
	private UUID uniqueId;
	
	public UUID getUniqueId() {
		return uniqueId;
	}
	
	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public boolean equals(Object other) {
		if (other == this)
			return true;
		
		if (other instanceof Player) {
			return getUniqueId().equals(((Player) other).getUniqueId());
		}
		
		return false;
	}

}
