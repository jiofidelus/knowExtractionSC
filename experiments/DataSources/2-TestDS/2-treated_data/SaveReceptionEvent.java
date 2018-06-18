package org.imogene.epicam.client.event.save ; public class SaveReceptionEvent extends GwtEvent<SaveReceptionEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveReception(ReceptionProxy value) ;  void saveReception(ReceptionProxy value, String initField) ;  }  private final ReceptionProxy value ;  private final String initField ;  public SaveReceptionEvent(ReceptionProxy value) { this(value, null) ;  }  public SaveReceptionEvent(ReceptionProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveReception(value) ;  else handler.saveReception(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }