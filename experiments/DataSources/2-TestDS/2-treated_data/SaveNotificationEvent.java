package org.imogene.admin.client.event.save ; public class SaveNotificationEvent extends GwtEvent<SaveNotificationEvent.Handler> { public static final Type<Handler> TYPE = new Type<Handler>() ;  public interface Handler extends EventHandler { void saveNotification(NotificationProxy value) ;  void saveNotification(NotificationProxy value, String initField) ;  }  private final NotificationProxy value ;  private final String initField ;  public SaveNotificationEvent(NotificationProxy value) { this(value, null) ;  }  public SaveNotificationEvent(NotificationProxy value, String initField) { this.value = value ;  this.initField = initField ;  }  @Override protected void dispatch(Handler handler) { if (initField == null) handler.saveNotification(value) ;  else handler.saveNotification(value, initField) ;  }  @Override public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() { return TYPE ;  } }