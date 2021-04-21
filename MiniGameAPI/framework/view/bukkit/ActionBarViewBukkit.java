package view.bukkit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import view.ActionBarView;

public class ActionBarViewBukkit implements ActionBarView {
	
	@Override
	public void displayMessage(UUID viewer, String message) {
		Player player = Bukkit.getPlayer(viewer);
		sendActionBar(player, message);
	}

	private void sendActionBar(Player player, String message) {
		final String newMessage = message.replace("_", " ");
		String s = ChatColor.translateAlternateColorCodes('&', newMessage);
		IChatBaseComponent ichatbasecomponent = ChatSerializer.a("{\"text\": \"" + s + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(ichatbasecomponent, ChatMessageType.GAME_INFO, player.getUniqueId());
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}

}