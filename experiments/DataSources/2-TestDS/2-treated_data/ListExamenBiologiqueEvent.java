package org.imogene.epicam.client.event.list ; public class ListExamenBiologiqueEvent extends GwtEvent<ListExamenBiologiqueEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listExamenBiologique() ;  void listExamenBiologique(String searchText) ;  }  public ListExamenBiologiqueEvent() { }  public ListExamenBiologiqueEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listExamenBiologique() ;  else handler.listExamenBiologique(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }