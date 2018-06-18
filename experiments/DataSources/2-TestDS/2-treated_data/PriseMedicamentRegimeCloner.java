package org.imogene.epicam.domain.entity.backup ; public class PriseMedicamentRegimeCloner { public static PriseMedicamentRegimeBck cloneEntity( PriseMedicamentRegime entity) { PriseMedicamentRegimeBck bck = new PriseMedicamentRegimeBck() ;  bck.setTraceId(UUID.randomUUID().toString()) ;  bck.setId(entity.getId()) ;  bck.setCreated(entity.getCreated()) ;  bck.setCreatedBy(entity.getCreatedBy()) ;  bck.setModified(entity.getModified()) ;  bck.setModifiedBy(entity.getModifiedBy()) ;  bck.setModifiedFrom(entity.getModifiedFrom()) ;  bck.setUploadDate(entity.getUploadDate()) ;  bck.setDeleted(entity.getDeleted()) ;  bck.setVersion(entity.getVersion()) ;  if (entity.getRegime() != null) { bck.setRegime(entity.getRegime().getId()) ;  }  if (entity.getMedicament() != null) { bck.setMedicament(entity.getMedicament().getId()) ;  }  bck.setQuantite(entity.getQuantite()) ;  bck.setQuantiteUnite(entity.getQuantiteUnite()) ;  return bck ;  } }