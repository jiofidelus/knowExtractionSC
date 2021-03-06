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
 * A data handler for the Ravitaillement beans 
 * @author Medes-IMPS
 */
public class RavitaillementHandler {

	private RavitaillementDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Ravitaillement findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public Ravitaillement getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(Ravitaillement entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			// manage related details
			List<DetailRavitaillement> ravca = entity.getDetails();
			if (ravca != null) {
				for (DetailRavitaillement ravcaItem : ravca) {
					handlerHelper.prepare(ravcaItem);
					// manage related sortieLot
					SortieLot det_ravba = ravcaItem.getSortieLot();
					if (det_ravba != null) {
						handlerHelper.prepare(det_ravba);
					}
					// manage related entreeLot
					EntreeLot det_ravca = ravcaItem.getEntreeLot();
					if (det_ravca != null) {
						handlerHelper.prepare(det_ravca);
					}
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
	 * Lists the entities of type Ravitaillement
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listRavitaillement(String sortProperty,
			boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Ravitaillement> beans = dao
				.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Ravitaillement
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listRavitaillement(String sortProperty,
			boolean sortOrder, ImogJunction junction) {

		List<Ravitaillement> beans = dao
				.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type Ravitaillement
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<Ravitaillement> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type Ravitaillement
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Ravitaillement> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type Ravitaillement
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listRavitaillement(int i, int j,
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

		List<Ravitaillement> beans = dao.load(i, j, sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Ravitaillement	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listNonAffectedRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions,
			String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Ravitaillement> beans = dao.loadNonAffected(i, j, sortProperty,
				sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type Ravitaillement	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listNonAffectedRavitaillement(int i, int j,
			String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedRavitaillement(i, j, sortProperty, sortOrder,
				null, property);
	}

	/**
	 * Used when Ravitaillement is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Ravitaillement non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listNonAffectedRavitaillementReverse(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<Ravitaillement> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when Ravitaillement is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of Ravitaillement non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listNonAffectedRavitaillementReverse(int i,
			int j, String sortProperty, boolean sortOrder, String property) {
		return listNonAffectedRavitaillementReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of Ravitaillement	
	 * @return an empty list of Ravitaillement
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> getRavitaillementEmptyList() {
		return new ArrayList<Ravitaillement>();
	}

	/**
	 * Counts the number of ravitaillement in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countRavitaillement() {
		return countRavitaillement(null);
	}

	/**
	 * Counts the number of ravitaillement in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countRavitaillement(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected ravitaillement entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedRavitaillement(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected ravitaillement entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedRavitaillement(String property) {
		return countNonAffectedRavitaillement(property, null);
	}

	/**
	 * Counts the number of non affected ravitaillement entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedRavitaillementReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected ravitaillement entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedRavitaillementReverse(String property) {
		return countNonAffectedRavitaillementReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<Ravitaillement> entities) {
		if (entities != null) {
			for (Ravitaillement entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(Ravitaillement entity) {

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
	 * Lists the entities of type Ravitaillement for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<Ravitaillement> listForCsv(String sortProperty,
			boolean sortOrder, String cDTDepart_nom, String dateDepartBefore,
			String dateDepartAfter, String cDTArrivee_nom,
			String dateArriveeBefore, String dateArriveeAfter) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (cDTDepart_nom != null && !cDTDepart_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("cDTDepart.nom");
			criteria.setValue(cDTDepart_nom);
			junction.add(criteria);
		}
		if (dateDepartBefore != null && !dateDepartBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateDepart");
			criteria.setValue(dateDepartBefore);
			junction.add(criteria);
		}
		if (dateDepartAfter != null && !dateDepartAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateDepart");
			criteria.setValue(dateDepartAfter);
			junction.add(criteria);
		}
		if (cDTArrivee_nom != null && !cDTArrivee_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("cDTArrivee.nom");
			criteria.setValue(cDTArrivee_nom);
			junction.add(criteria);
		}
		if (dateArriveeBefore != null && !dateArriveeBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("dateArrivee");
			criteria.setValue(dateArriveeBefore);
			junction.add(criteria);
		}
		if (dateArriveeAfter != null && !dateArriveeAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("dateArrivee");
			criteria.setValue(dateArriveeAfter);
			junction.add(criteria);
		}

		List<Ravitaillement> beans = dao
				.load(sortProperty, sortOrder, junction);
		List<Ravitaillement> securedBeans = filter
				.<Ravitaillement> toSecure(beans);
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
	 * @param dao the Ravitaillement Dao
	 */
	public void setDao(RavitaillementDao dao) {
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
