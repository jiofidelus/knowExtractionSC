package org.imogene.epicam.client.event.create ; public class CreateCommandeEvent extends GwtEvent<CreateCommandeEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  private final GwtEvent<?> closeEvent ;  public interface Handler extends EventHandler { void createNewCommande(GwtEvent<?> closeEvent) ;  }  public CreateCommandeEvent() { this(new ListCommandeEvent()) ;  }  public CreateCommandeEvent(GwtEvent<?> closeEvent) { this.closeEvent = closeEvent ;  }  @Override protected void dispatch(Handler handler) { handler.createNewCommande(closeEvent) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }