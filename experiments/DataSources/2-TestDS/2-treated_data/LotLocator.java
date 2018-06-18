package org.imogene.epicam.server.locator ; public class LotLocator extends Locator<Lot, String> { private LotHandler handler ;  public LotLocator() { }  @Override public Lot create(Class<? extends Lot> clazz) { return new Lot() ;  }  @Override public Lot find(Class<? extends Lot> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<Lot> getDomainType() { return Lot.class ;  }  @Override public String getId(Lot domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(Lot domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (LotHandler) context.getBean("lotHandler") ;  } }