package org.imogene.epicam.shared.proxy ; @ProxyFor(value = Utilisateur.class, locator = UtilisateurLocator.class)public interface UtilisateurProxy extends ImogActorProxy { public String getNom() ;  public void setNom(String value) ;  public Date getDateNaissance() ;  public void setDateNaissance(Date value) ;  public String getProfession() ;  public void setProfession(String value) ;  public String getTelephoneUn() ;  public void setTelephoneUn(String value) ;  public String getTelephoneDeux() ;  public void setTelephoneDeux(String value) ;  public String getTelephoneTrois() ;  public void setTelephoneTrois(String value) ;  public String getEmail() ;  public void setEmail(String value) ;  public String getLibelle() ;  public void setLibelle(String value) ;  public String getComplementAdresse() ;  public void setComplementAdresse(String value) ;  public String getQuartier() ;  public void setQuartier(String value) ;  public String getVille() ;  public void setVille(String value) ;  public String getCodePostal() ;  public void setCodePostal(String value) ; }