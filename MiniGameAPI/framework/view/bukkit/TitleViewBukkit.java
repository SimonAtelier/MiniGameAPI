package view.bukkit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_15_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle.EnumTitleAction;
import view.DefaultTitleViewModel;
import view.TitleView;
import view.TitleViewModel;

public class TitleViewBukkit implements TitleView {

	private TitleViewModel titleViewModel;
	
	public TitleViewBukkit() {
		titleViewModel = new DefaultTitleViewModel();
	}

	@Override
	public void display(UUID viewer) {
		displayTitle(viewer);
	}

	@Override
	public TitleViewModel getTitleViewModel() {
		return titleViewModel;
	}

	@Override
	public void setTitleViewModel(TitleViewModel titleViewModel) {
		this.titleViewModel = titleViewModel;
	}

	private PacketPlayOutTitle createPacketPlayOutTitleForTitle() {
		int fadeInTicks = getTitleViewModel().getTitleFadeInTimeInTicks();
		int stayTicks = getTitleViewModel().getTitleStayTimeInTicks();
		int fadeOutTicks = getTitleViewModel().getTitleFadeOutTimeInTicks();
		String title = getTitleViewModel().getTitle();
		return new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"),
				fadeInTicks, stayTicks, fadeOutTicks);
	}

	private PacketPlayOutTitle createPacketPlayOutTitleForSubtitle() {
		int fadeInTicks = getTitleViewModel().getSubtitleFadeInTimeInTicks();
		int stayTicks = getTitleViewModel().getSubtitleStayTimeInTicks();
		int fadeOutTicks = getTitleViewModel().getSubtitleFadeOutTimeInTicks();
		String title = getTitleViewModel().getSubtitle();
		return new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"),
				fadeInTicks, stayTicks, fadeOutTicks);
	}

	private void displayTitle(UUID viewer) {
		Player player = Bukkit.getPlayer(viewer);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacketPlayOutTitleForTitle());
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacketPlayOutTitleForSubtitle());
	}

}
