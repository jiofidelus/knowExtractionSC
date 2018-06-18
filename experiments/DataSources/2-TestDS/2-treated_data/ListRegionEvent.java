package org.imogene.epicam.client.event.list ; public class ListRegionEvent extends GwtEvent<ListRegionEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listRegion() ;  void listRegion(String searchText) ;  }  public ListRegionEvent() { }  public ListRegionEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listRegion() ;  else handler.listRegion(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }