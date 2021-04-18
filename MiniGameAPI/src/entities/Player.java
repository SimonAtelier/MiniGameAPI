package entities;

import java.util.UUID;

public class Player {
	
	private UUID uniqueId;
		
	public Player(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public UUID getUniqueId() {
		return uniqueId;
	}
	
	public void setUniqueId(UUID uniqueId) {
		if (uniqueId == null)
			throw new PlayerIdCannotBeNullException();
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
	
	public class PlayerIdCannotBeNullException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
	}

}
