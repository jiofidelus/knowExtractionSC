package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DetailRavitaillement form entries
 * @author MEDES-IMPS
 */
public class ListDetailRavitaillementEvent
		extends
			GwtEvent<ListDetailRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDetailRavitaillement();
		void listDetailRavitaillement(String searchText);
	}

	public ListDetailRavitaillementEvent() {
	}

	public ListDetailRavitaillementEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDetailRavitaillement();
		else
			handler.listDetailRavitaillement(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
