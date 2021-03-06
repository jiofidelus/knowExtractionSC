package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.EntreeLotProxy;
import org.imogene.epicam.shared.request.EntreeLotRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogConjunctionProxy;
import org.imogene.web.shared.proxy.criteria.ImogCriterionProxy;
import org.imogene.web.shared.proxy.criteria.ImogDisjunctionProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.ProfileUtil;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * Data provider to feed the EntreeLot entry Table and Selection List
 * @author MEDES-IMPS
 */
public class EntreeLotDataProvider extends ImogBeanDataProvider<EntreeLotProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity EntreeLot with pagination
	 */
	public EntreeLotDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity EntreeLot that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public EntreeLotDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity EntreeLot that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public EntreeLotDataProvider(EpicamRequestFactory requestFactory,
			String pProperty, boolean searchInReverse) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		this.searchInReverse = searchInReverse;
		setSearchCriterions(null);
	}

	/**
	 * Sets criterions for which values have to be temporally searched
	 * @param criterions ImogJunctionProxy including the criterions for which the values have to be searched
	 */
	public void setSearchCriterions(ImogJunctionProxy criterions) {
		if (criterions == null) {
			if (ProfileUtil.isAdmin()) {
				filter(getDeletedEntityFilterCriteria(false));
				LocalSession.get().setSearchCriterions(getSearchCriterions(),
						null);
			} else
				searchCriterions = criterions;
		} else
			searchCriterions = criterions;
	}

	/**
	 * Called by Relation Boxes
	 */
	@Override
	public Request<List<EntreeLotProxy>> getList(int start, int numRows) {

		EntreeLotRequest request = (EntreeLotRequest) getContext();
		Request<List<EntreeLotProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedEntreeLot(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedEntreeLotReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listEntreeLot(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedEntreeLot(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedEntreeLotReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listEntreeLot(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getEntreeLotEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedEntreeLot(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedEntreeLotReverse(start,
								numRows, "modified", false, searchCriterions,
								property);
				} else
					result = request.listEntreeLot(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedEntreeLot(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedEntreeLotReverse(start,
								numRows, "modified", false, property);
				} else
					result = request.listEntreeLot(start, numRows, "modified",
							false);
			}
		}

		if (isFiltered) {
			result.with("lot");
			result.with("lot.intrant");
			result.with("lot.medicament");
		}

		else {
			result.with("lot");
			result.with("lot.intrant");
			result.with("lot.medicament");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<EntreeLotProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		EntreeLotRequest request = (EntreeLotRequest) getContext();
		Request<List<EntreeLotProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listEntreeLot(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listEntreeLot(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getEntreeLotEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listEntreeLot(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listEntreeLot(start, numRows, property, asc);
		}

		result.with("lot");
		result.with("lot.intrant");
		result.with("lot.medicament");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		EntreeLotRequest request = (EntreeLotRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedEntreeLot(property,
									searchCriterions);
						else
							return request.countNonAffectedEntreeLotReverse(
									property, searchCriterions);
					} else
						return request.countEntreeLot(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedEntreeLot(property,
									filterCriteria);
						else
							return request.countNonAffectedEntreeLotReverse(
									property, filterCriteria);
					} else
						return request.countEntreeLot(filterCriteria);
				}

			} else
				return request.countNonAffectedEntreeLot("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedEntreeLot(property,
								searchCriterions);
					else
						return request.countNonAffectedEntreeLotReverse(
								property, searchCriterions);
				} else
					return request.countEntreeLot(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedEntreeLot(property);
					else
						return request
								.countNonAffectedEntreeLotReverse(property);
				} else
					return request.countEntreeLot();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.entreeLotRequest();
	}

	@Override
	public String fullTextSearch(String text) {

		boolean fullTextSearch = false;
		StringBuffer buffer = new StringBuffer(BaseNLS.constants()
				.label_filtered() + " ");

		if (text == null || (text != null && text.equals(""))) {
			setSearchCriterions(null);
		} else {

			String locale = NLS.constants().locale();

			EntreeLotRequest request = (EntreeLotRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Numero
			BasicCriteriaProxy lot_numeroCrit = request
					.create(BasicCriteriaProxy.class);
			lot_numeroCrit.setField("lot.numero");
			lot_numeroCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lot_numeroCrit.setValue(text);
			buffer.append("(" + NLS.constants().lot_field_numero() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(lot_numeroCrit);
			// Search field Identifiant
			BasicCriteriaProxy lot_intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			lot_intrant_identifiantCrit.setField("lot.intrant.identifiant");
			lot_intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lot_intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(lot_intrant_identifiantCrit);
			// Search field Designation
			BasicCriteriaProxy lot_medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			lot_medicament_designationCrit
					.setField("lot.medicament.designation");
			lot_medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lot_medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(lot_medicament_designationCrit);
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy lot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				lot_quantiteCrit.setField("lot.quantite");
				lot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				lot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().lot_field_quantite() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(lot_quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				quantiteCrit.setField("quantite");
				quantiteCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().entreeLot_field_quantite()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			disJunction.setCriterions(criterionList);
			criterias.add(disJunction);
			fullTextSearch = true;

			if (ProfileUtil.isAdmin()) {
				BasicCriteriaProxy isDeletedCrit = request
						.create(BasicCriteriaProxy.class);
				isDeletedCrit.setField("deleted");
				isDeletedCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL);
				isDeletedCrit.setValue(text);
				criterias.add(isDeletedCrit);
			}
			junction.setCriterions(criterias);

			// add FilterCriteria if exists
			if (isFiltered && filterCriteria != null)
				setSearchCriterions(mergeFilterCriteriaAndFullTextSearchCriterion(
						request, junction));
			else
				setSearchCriterions(junction);

		}
		if (fullTextSearch) {
			String message = buffer.toString();
			int lastSymbolIndex = message.lastIndexOf(SYMBOL_OR);
			return message.substring(0, lastSymbolIndex);
		} else
			return null;
	}

}
