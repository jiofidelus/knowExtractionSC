package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;

/**
 * Event that is fired after that a ExamenSerologie form has been saved
 * @author MEDES-IMPS
 */
public class SaveExamenSerologieEvent
		extends
			GwtEvent<SaveExamenSerologieEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveExamenSerologie(ExamenSerologieProxy value);
		void saveExamenSerologie(ExamenSerologieProxy value, String initField);
	}

	private final ExamenSerologieProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveExamenSerologieEvent(ExamenSerologieProxy value) {
		this(value, null);
	}

	public SaveExamenSerologieEvent(ExamenSerologieProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveExamenSerologie(value);
		else
			handler.saveExamenSerologie(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
