package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.HorsUsageProxy;
import org.imogene.epicam.shared.request.HorsUsageRequest;
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
 * Data provider to feed the HorsUsage entry Table and Selection List
 * @author MEDES-IMPS
 */
public class HorsUsageDataProvider extends ImogBeanDataProvider<HorsUsageProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity HorsUsage with pagination
	 */
	public HorsUsageDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity HorsUsage that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public HorsUsageDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity HorsUsage that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public HorsUsageDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<HorsUsageProxy>> getList(int start, int numRows) {

		HorsUsageRequest request = (HorsUsageRequest) getContext();
		Request<List<HorsUsageProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedHorsUsage(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedHorsUsageReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listHorsUsage(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedHorsUsage(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedHorsUsageReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listHorsUsage(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getHorsUsageEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedHorsUsage(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedHorsUsageReverse(start,
								numRows, "modified", false, searchCriterions,
								property);
				} else
					result = request.listHorsUsage(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedHorsUsage(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedHorsUsageReverse(start,
								numRows, "modified", false, property);
				} else
					result = request.listHorsUsage(start, numRows, "modified",
							false);
			}
		}

		if (isFiltered) {
			result.with("lot");
			result.with("lot.lot");
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<HorsUsageProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		HorsUsageRequest request = (HorsUsageRequest) getContext();
		Request<List<HorsUsageProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listHorsUsage(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listHorsUsage(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getHorsUsageEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listHorsUsage(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listHorsUsage(start, numRows, property, asc);
		}

		result.with("lot");
		result.with("lot.lot");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		HorsUsageRequest request = (HorsUsageRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedHorsUsage(property,
									searchCriterions);
						else
							return request.countNonAffectedHorsUsageReverse(
									property, searchCriterions);
					} else
						return request.countHorsUsage(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedHorsUsage(property,
									filterCriteria);
						else
							return request.countNonAffectedHorsUsageReverse(
									property, filterCriteria);
					} else
						return request.countHorsUsage(filterCriteria);
				}

			} else
				return request.countNonAffectedHorsUsage("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedHorsUsage(property,
								searchCriterions);
					else
						return request.countNonAffectedHorsUsageReverse(
								property, searchCriterions);
				} else
					return request.countHorsUsage(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedHorsUsage(property);
					else
						return request
								.countNonAffectedHorsUsageReverse(property);
				} else
					return request.countHorsUsage();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.horsUsageRequest();
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

			HorsUsageRequest request = (HorsUsageRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Numero
			BasicCriteriaProxy lot_lot_numeroCrit = request
					.create(BasicCriteriaProxy.class);
			lot_lot_numeroCrit.setField("lot.lot.numero");
			lot_lot_numeroCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lot_lot_numeroCrit.setValue(text);
			buffer.append("(" + NLS.constants().lot_field_numero() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(lot_lot_numeroCrit);
			// Search field Identifiant
			BasicCriteriaProxy lot_lot_intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			lot_lot_intrant_identifiantCrit
					.setField("lot.lot.intrant.identifiant");
			lot_lot_intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lot_lot_intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(lot_lot_intrant_identifiantCrit);
			// Search field Designation
			BasicCriteriaProxy lot_lot_medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			lot_lot_medicament_designationCrit
					.setField("lot.lot.medicament.designation");
			lot_lot_medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lot_lot_medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(lot_lot_medicament_designationCrit);
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy lot_lot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				lot_lot_quantiteCrit.setField("lot.lot.quantite");
				lot_lot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				lot_lot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().lot_field_quantite() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(lot_lot_quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}
			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy lot_quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				lot_quantiteCrit.setField("lot.quantite");
				lot_quantiteCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				lot_quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().sortieLot_field_quantite()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(lot_quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Type
			if (text.toLowerCase().equals(
					NLS.constants().horsUsage_type_perimee_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.HORSUSAGE_TYPE_PERIMEE);
				buffer.append("(" + NLS.constants().horsUsage_field_type()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}
			if (text.toLowerCase()
					.equals(NLS.constants().horsUsage_type_casse_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.HORSUSAGE_TYPE_CASSE);
				buffer.append("(" + NLS.constants().horsUsage_field_type()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
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
