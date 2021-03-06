package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListExamenMicroscopieEvent;

/**
 * Event that is fired in order to display a ExamenMicroscopie form in view mode
 * @author MEDES-IMPS
 */
public class ViewExamenMicroscopieEvent
		extends
			GwtEvent<ViewExamenMicroscopieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewExamenMicroscopie(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewExamenMicroscopieEvent(String entityId) {
		this(entityId, new ListExamenMicroscopieEvent());
	}

	public ViewExamenMicroscopieEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewExamenMicroscopie(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
