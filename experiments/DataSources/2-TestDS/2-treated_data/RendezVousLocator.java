package org.imogene.epicam.server.locator ; public class RendezVousLocator extends Locator<RendezVous, String> { private RendezVousHandler handler ;  public RendezVousLocator() { }  @Override public RendezVous create(Class<? extends RendezVous> clazz) { return new RendezVous() ;  }  @Override public RendezVous find(Class<? extends RendezVous> clazz, String id) { if (handler == null) initHandler() ;  return handler.getById(id) ;  }  @Override public Class<RendezVous> getDomainType() { return RendezVous.class ;  }  @Override public String getId(RendezVous domainObject) { return domainObject.getId() ;  }  @Override public Class<String> getIdType() { return String.class ;  }  @Override public Object getVersion(RendezVous domainObject) { return domainObject.getVersion() ;  }  private void initHandler() { HttpServletRequest request = RequestFactoryServlet .getThreadLocalRequest() ;  ApplicationContext context = WebApplicationContextUtils .getWebApplicationContext(request.getSession() .getServletContext()) ;  handler = (RendezVousHandler) context.getBean("rendezVousHandler") ;  } }