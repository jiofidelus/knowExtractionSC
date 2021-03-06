package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListCandidatureFormationEvent;

/**
 * Event that is fired in order to display a CandidatureFormation form in view mode
 * @author MEDES-IMPS
 */
public class ViewCandidatureFormationEvent
		extends
			GwtEvent<ViewCandidatureFormationEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewCandidatureFormation(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewCandidatureFormationEvent(String entityId) {
		this(entityId, new ListCandidatureFormationEvent());
	}

	public ViewCandidatureFormationEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewCandidatureFormation(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
