package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListLaboratoireReferenceEvent;

/**
 * Event that is fired in order to display a LaboratoireReference form in view mode
 * @author MEDES-IMPS
 */
public class ViewLaboratoireReferenceEvent
		extends
			GwtEvent<ViewLaboratoireReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewLaboratoireReference(String entityId, GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewLaboratoireReferenceEvent(String entityId) {
		this(entityId, new ListLaboratoireReferenceEvent());
	}

	public ViewLaboratoireReferenceEvent(String entityId, GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewLaboratoireReference(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
