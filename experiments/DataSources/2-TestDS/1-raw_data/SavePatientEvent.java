package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.PatientProxy;

/**
 * Event that is fired after that a Patient form has been saved
 * @author MEDES-IMPS
 */
public class SavePatientEvent extends GwtEvent<SavePatientEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void savePatient(PatientProxy value);
		void savePatient(PatientProxy value, String initField);
	}

	private final PatientProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SavePatientEvent(PatientProxy value) {
		this(value, null);
	}

	public SavePatientEvent(PatientProxy value, String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.savePatient(value);
		else
			handler.savePatient(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
