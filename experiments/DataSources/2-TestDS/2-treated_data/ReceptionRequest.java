package org.imogene.epicam.shared.request ; @Service(value = ReceptionHandler.class, locator = SpringServiceLocator.class)public interface ReceptionRequest extends ImogEntityRequest { Request<ReceptionProxy> findById(String id) ;  Request<Void> save(ReceptionProxy c, boolean isNew) ;  Request<List<ReceptionProxy>> listReception(String sortProperty, boolean sortOrder) ;  Request<List<ReceptionProxy>> listReception(int first, int max, String sortProperty, boolean sortOrder) ;  Request<List<ReceptionProxy>> listReception(int first, int max, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions) ;  Request<List<ReceptionProxy>> listReception(int first, int max, String sortProperty, boolean sortOrder, List<BasicCriteriaProxy> criterions) ;  Request<List<ReceptionProxy>> listNonAffectedReception(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<ReceptionProxy>> listNonAffectedReception(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<ReceptionProxy>> listNonAffectedReceptionReverse(int i, int j, String sortProperty, boolean sortOrder, String property) ;  Request<List<ReceptionProxy>> listNonAffectedReceptionReverse(int i, int j, String sortProperty, boolean sortOrder, ImogJunctionProxy criterions, String property) ;  Request<List<ReceptionProxy>> getReceptionEmptyList() ;  Request<Long> countReception() ;  Request<Long> countReception(ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedReception(String property) ;  Request<Long> countNonAffectedReception(String property, ImogJunctionProxy criterions) ;  Request<Long> countNonAffectedReceptionReverse(String property) ;  Request<Long> countNonAffectedReceptionReverse(String property, ImogJunctionProxy criterions) ;  Request<Void> delete(Set<ReceptionProxy> entities) ;  Request<Void> delete(ReceptionProxy entity) ;  Request<Void> save(ImogBeanProxy entity, boolean isNew) ;  Request<Void> delete(ImogBeanProxy entity) ; }