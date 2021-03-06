package org.imogene.epicam.server.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.domain.dao.*;
import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.binary.Binary;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.lib.common.criteria.BasicCriteria;
import org.imogene.lib.common.criteria.ImogConjunction;
import org.imogene.lib.common.criteria.ImogDisjunction;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.security.ImogBeanFilter;
import org.imogene.web.server.util.FilterFieldsHelper;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.web.server.util.ProfileUtil;
import org.imogene.web.server.handler.HandlerHelper;

import org.springframework.transaction.annotation.Transactional;

/**
 * A data handler for the CasTuberculose beans 
 * @author Medes-IMPS
 */
public class CasTuberculoseHandler {

	private CasTuberculoseDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public CasTuberculose findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public CasTuberculose getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(CasTuberculose entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			// manage related priseMedicamenteusePhaseInitiale
			List<PriseMedicamenteuse> tbcasecc = entity
					.getPriseMedicamenteusePhaseInitiale();
			if (tbcasecc != null) {
				for (PriseMedicamenteuse tbcaseccItem : tbcasecc) {
					handlerHelper.prepare(tbcaseccItem);
				}
			}
			// manage related priseMedicamenteusePhaseContinuation
			List<PriseMedicamenteuse> tbcasecd = entity
					.getPriseMedicamenteusePhaseContinuation();
			if (tbcasecd != null) {
				for (PriseMedicamenteuse tbcasecdItem : tbcasecd) {
					handlerHelper.prepare(tbcasecdItem);
				}
			}
			// manage related rendezVous
			List<RendezVous> tbcasece = entity.getRendezVous();
			if (tbcasece != null) {
				for (RendezVous tbcaseceItem : tbcasece) {
					handlerHelper.prepare(tbcaseceItem);
				}
			}

			dao.saveOrUpdate(entity, isNew);

		}
	}

	/**
	 * Saves or updates the bean
	 * @param entity the bean to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(ImogBean entity, boolean isNew) {
		handlerHelper.save(entity, isNew);
	}

	/**
	 * Lists the entities of type CasTuberculose
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listCasTuberculose(String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<CasTuberculose> beans = dao
				.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type CasTuberculose
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listCasTuberculose(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<CasTuberculose> beans = dao
				.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type CasTuberculose
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listCasTuberculose(int i, int j,
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<CasTuberculose> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type CasTuberculose
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listCasTuberculose(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<CasTuberculose> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type CasTuberculose
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listCasTuberculose(int i, int j,
			String sortProperty, boolean sortOrder,
			List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<CasTuberculose> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type CasTuberculose	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listNonAffectedCasTuberculose(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<CasTuberculose> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type CasTuberculose	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listNonAffectedCasTuberculose(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedCasTuberculose(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Used when CasTuberculose is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of CasTuberculose non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listNonAffectedCasTuberculoseReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<CasTuberculose> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when CasTuberculose is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of CasTuberculose non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of casTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listNonAffectedCasTuberculoseReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedCasTuberculoseReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of CasTuberculose	
	 * @return an empty list of CasTuberculose
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> getCasTuberculoseEmptyList() {
		return new ArrayList<CasTuberculose>();
	}

	/**
	 * Counts the number of casTuberculose in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countCasTuberculose() {
		return countCasTuberculose(null);
	}

	/**
	 * Counts the number of casTuberculose in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countCasTuberculose(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected casTuberculose entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCasTuberculose(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected casTuberculose entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCasTuberculose(String property) {
		return countNonAffectedCasTuberculose(property, null);
	}

	/**
	 * Counts the number of non affected casTuberculose entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCasTuberculoseReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected casTuberculose entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCasTuberculoseReverse(String property) {
		return countNonAffectedCasTuberculoseReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<CasTuberculose> entities) {
		if (entities != null) {
			for (CasTuberculose entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(CasTuberculose entity) {

		handlerHelper.prepareForDelete(entity);
		dao.saveOrUpdate(entity, false);
	}

	/**
	 * Removes the specified bean from the database 
	 * @param entity The bean to be deleted
	 */
	@Transactional
	public void delete(ImogBean entity) {
		handlerHelper.delete(entity);
	}

	/**
	 * Lists the entities of type CasTuberculose for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<CasTuberculose> listForCsv(String sortProperty,
			boolean sortOrder, String patient_identifiant, String patient_nom,
			String dateDebutTraitementBefore, String dateDebutTraitementAfter,
			String typePatient, String formeMaladie) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (patient_identifiant != null && !patient_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("patient.identifiant");
			criteria.setValue(patient_identifiant);
			junction.add(criteria);
		}
		if (patient_nom != null && !patient_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("patient.nom");
			criteria.setValue(patient_nom);
			junction.add(criteria);
		}
		if (dateDebutTraitementBefore != null
				&& !dateDebutTraitementBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateDebutTraitement");
			criteria.setValue(dateDebutTraitementBefore);
			junction.add(criteria);
		}
		if (dateDebutTraitementAfter != null
				&& !dateDebutTraitementAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateDebutTraitement");
			criteria.setValue(dateDebutTraitementAfter);
			junction.add(criteria);
		}
		if (typePatient != null && !typePatient.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("typePatient");
			criteria.setValue(typePatient);
			junction.add(criteria);
		}
		if (formeMaladie != null && !formeMaladie.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("formeMaladie");
			criteria.setValue(formeMaladie);
			junction.add(criteria);
		}

		List<CasTuberculose> beans = dao
				.load(sortProperty, sortOrder, junction);
		List<CasTuberculose> securedBeans = filter
				.<CasTuberculose> toSecure(beans);
		return securedBeans;
	}

	/**
	 * Creates a junction based on the filter field declarations, for the current actor.
	 * @param actor the current actor
	 */
	private ImogJunction createFilterJuntion(ImogActor actor) {
		ImogConjunction filterConjunction = new ImogConjunction();
		if (!ProfileUtil.isAdmin(actor))
			filterConjunction.add(handlerHelper.getNotDeletedFilterCriteria());
		return filterConjunction;
	}

	/**
	 * Setter for bean injection
	 * @param dao the CasTuberculose Dao
	 */
	public void setDao(CasTuberculoseDao dao) {
		this.dao = dao;
	}

	/**
	 * Setter for bean injection
	 * @param imogBeanFilter
	 */
	public void setFilter(ImogBeanFilter filter) {
		this.filter = filter;
	}

	/**
	 * Setter for bean injection.
	 * @param helper
	 */
	public void setHandlerHelper(HandlerHelper helper) {
		this.handlerHelper = helper;
	}

}
