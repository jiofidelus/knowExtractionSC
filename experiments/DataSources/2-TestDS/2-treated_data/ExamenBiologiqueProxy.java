package org.imogene.epicam.shared.proxy ; @ProxyFor(value = ExamenBiologique.class, locator = ExamenBiologiqueLocator.class)public interface ExamenBiologiqueProxy extends ImogBeanProxy { public PatientProxy getPatient() ;  public void setPatient(PatientProxy value) ;  public Date getDate() ;  public void setDate(Date value) ;  public Double getPoids() ;  public void setPoids(Double value) ;  public String getObservations() ;  public void setObservations(String value) ; }