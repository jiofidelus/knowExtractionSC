package org.imogene.epicam.shared.proxy ; @ProxyFor(value = RendezVous.class, locator = RendezVousLocator.class)public interface RendezVousProxy extends ImogBeanProxy { public CasTuberculoseProxy getCasTb() ;  public void setCasTb(CasTuberculoseProxy value) ;  public Date getDateRendezVous() ;  public void setDateRendezVous(Date value) ;  public Boolean getHonore() ;  public void setHonore(Boolean value) ;  public Integer getNombreSMSEnvoye() ;  public void setNombreSMSEnvoye(Integer value) ;  public String getCommentaire() ;  public void setCommentaire(String value) ; }