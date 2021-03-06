package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCommandeEvent;

/**
 * Event that is fired in order to display a Commande form in creation mode
 * @author MEDES-IMPS
 */
public class CreateCommandeEvent extends GwtEvent<CreateCommandeEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewCommande(GwtEvent<?> closeEvent);
	}

	public CreateCommandeEvent() {
		this(new ListCommandeEvent());
	}

	public CreateCommandeEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewCommande(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
