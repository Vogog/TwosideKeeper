package sig.plugin.TwosideKeeper.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PlayerLineDriveEvent extends Event implements Cancellable{
	private Player p;
    private boolean cancelled=false;
	private static final HandlerList handlers = new HandlerList();
	
	public PlayerLineDriveEvent(Player p) {
		this.p=p;
	}

	public Player getPlayer() {
		return p;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled=cancelled;
	}

}
