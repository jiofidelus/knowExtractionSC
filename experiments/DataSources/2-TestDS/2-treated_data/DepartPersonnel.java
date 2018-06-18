package org.imogene.epicam.domain.entity ; @Entitypublic class DepartPersonnel extends ImogEntityImpl { public static interface Columns { public static final String REGION = "region" ;  public static final String DISTRICTSANTE = "districtsante" ;  public static final String CDT = "cdt" ;  public static final String PERSONNEL = "personnel" ;  public static final String DATEDEPART = "datedepart" ;  public static final String MOTIFDEPART = "motifdepart" ;  }  private static final long serialVersionUID = 2225146226433633386L ;  @ManyToOne private Region region ;  @ManyToOne private DistrictSante districtSante ;  @ManyToOne private CentreDiagTrait CDT ;  @ManyToOne private Personnel personnel ;  @Temporal(TemporalType.TIMESTAMP) private Date dateDepart ;  @Column(columnDefinition = "TEXT") private String motifDepart ;  * Constructor public DepartPersonnel() { }  public Region getRegion() { return region ;  }  public void setRegion(Region value) { region = value ;  }  public DistrictSante getDistrictSante() { return districtSante ;  }  public void setDistrictSante(DistrictSante value) { districtSante = value ;  }  public CentreDiagTrait getCDT() { return CDT ;  }  public void setCDT(CentreDiagTrait value) { CDT = value ;  }  public Personnel getPersonnel() { return personnel ;  }  public void setPersonnel(Personnel value) { personnel = value ;  }  public Date getDateDepart() { return dateDepart ;  }  public void setDateDepart(Date value) { dateDepart = value ;  }  public String getMotifDepart() { return motifDepart ;  }  public void setMotifDepart(String value) { motifDepart = value ;  } }