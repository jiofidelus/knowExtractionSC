package org.imogene.web.shared.proxy ; @ProxyFor(value = ImogBeanImpl.class, locator = ImogBeanImplLocator.class)public interface ImogBeanProxy extends EntityProxy { public String getId() ;  public void setId(String id) ;  public int getVersion() ;  public void setVersion(int i) ;  public String getCreatedBy() ;  public void setCreatedBy(String creator) ;  public Date getCreated() ;  public void setCreated(Date date) ;  public String getModifiedBy() ;  public void setModifiedBy(String modifier) ;  public Date getModified() ;  public void setModified(Date date) ;  public Date getDeleted() ;  public void setDeleted(Date deleted) ; }