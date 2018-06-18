package org.imogene.epicam.client.event.list ; public class ListIntrantEvent extends GwtEvent<ListIntrantEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private String searchText = null ;  public interface Handler extends EventHandler { void listIntrant() ;  void listIntrant(String searchText) ;  }  public ListIntrantEvent() { }  public ListIntrantEvent(String searchText) { this.searchText = searchText ;  }  @Override protected void dispatch(Handler handler) { if (searchText == null) handler.listIntrant() ;  else handler.listIntrant(searchText) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }