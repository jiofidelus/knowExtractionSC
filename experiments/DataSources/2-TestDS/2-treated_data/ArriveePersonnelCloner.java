package org.imogene.epicam.domain.entity.backup ; public class ArriveePersonnelCloner { public static ArriveePersonnelBck cloneEntity(ArriveePersonnel entity) { ArriveePersonnelBck bck = new ArriveePersonnelBck() ;  bck.setTraceId(UUID.randomUUID().toString()) ;  bck.setId(entity.getId()) ;  bck.setCreated(entity.getCreated()) ;  bck.setCreatedBy(entity.getCreatedBy()) ;  bck.setModified(entity.getModified()) ;  bck.setModifiedBy(entity.getModifiedBy()) ;  bck.setModifiedFrom(entity.getModifiedFrom()) ;  bck.setUploadDate(entity.getUploadDate()) ;  bck.setDeleted(entity.getDeleted()) ;  bck.setVersion(entity.getVersion()) ;  if (entity.getRegion() != null) { bck.setRegion(entity.getRegion().getId()) ;  }  if (entity.getDistrictSante() != null) { bck.setDistrictSante(entity.getDistrictSante().getId()) ;  }  if (entity.getCDT() != null) { bck.setCDT(entity.getCDT().getId()) ;  }  if (entity.getPersonnel() != null) { bck.setPersonnel(entity.getPersonnel().getId()) ;  }  bck.setDateArrivee(entity.getDateArrivee()) ;  return bck ;  } }