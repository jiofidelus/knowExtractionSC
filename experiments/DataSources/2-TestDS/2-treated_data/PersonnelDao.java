package org.imogene.epicam.domain.dao ; public interface PersonnelDao extends ImogActorDao<Personnel> { * List associated Qualification,  * on the field qualification * @param parent the parent entity * @return the list of the associated entities public List<Qualification> loadQualification(Personnel parent) ; }