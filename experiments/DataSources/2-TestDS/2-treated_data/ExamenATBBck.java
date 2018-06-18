package org.imogene.epicam.domain.entity.backup ; @Entity@Table(name = "ExamenATB_Bck")public class ExamenATBBck extends ImogBeanBck { private static final long serialVersionUID = 2531214122384524454L ;  private String CDT ;  private String laboratoireReference ;  private String casTb ;  @Temporal(TemporalType.TIMESTAMP) private Date dateExamen ;  private String raisonDepistage ;  private String resultat ;  @Column(columnDefinition = "TEXT") private String observations ;  * Constructor public ExamenATBBck() { }  public String getCDT() { return CDT ;  }  public void setCDT(String value) { CDT = value ;  }  public String getLaboratoireReference() { return laboratoireReference ;  }  public void setLaboratoireReference(String value) { laboratoireReference = value ;  }  public String getCasTb() { return casTb ;  }  public void setCasTb(String value) { casTb = value ;  }  public Date getDateExamen() { return dateExamen ;  }  public void setDateExamen(Date value) { dateExamen = value ;  }  public String getRaisonDepistage() { return raisonDepistage ;  }  public void setRaisonDepistage(String value) { raisonDepistage = value ;  }  public String getResultat() { return resultat ;  }  public void setResultat(String value) { resultat = value ;  }  public String getObservations() { return observations ;  }  public void setObservations(String value) { observations = value ;  } }