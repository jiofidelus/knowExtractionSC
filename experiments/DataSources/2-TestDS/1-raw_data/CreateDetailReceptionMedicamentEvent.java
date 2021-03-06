package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailReceptionMedicamentEvent;

/**
 * Event that is fired in order to display a DetailReceptionMedicament form in creation mode
 * @author MEDES-IMPS
 */
public class CreateDetailReceptionMedicamentEvent
		extends
			GwtEvent<CreateDetailReceptionMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewDetailReceptionMedicament(GwtEvent<?> closeEvent);
	}

	public CreateDetailReceptionMedicamentEvent() {
		this(new ListDetailReceptionMedicamentEvent());
	}

	public CreateDetailReceptionMedicamentEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewDetailReceptionMedicament(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
