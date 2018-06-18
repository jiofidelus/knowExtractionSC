package org.imogene.epicam.client.event.view ; public class ViewExamenBiologiqueEvent extends GwtEvent<ViewExamenBiologiqueEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void viewExamenBiologique(String entityId, GwtEvent<?> closeEvent) ;  }  private final String entityId ;  private final GwtEvent<?> closeEvent ;  public ViewExamenBiologiqueEvent(String entityId) { this(entityId, new ListExamenBiologiqueEvent()) ;  }  public ViewExamenBiologiqueEvent(String entityId, GwtEvent<?> closeEvent) { this.entityId = entityId ;  this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.viewExamenBiologique(entityId, closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }