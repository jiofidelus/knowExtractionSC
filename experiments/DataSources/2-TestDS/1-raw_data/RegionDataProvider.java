package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.epicam.shared.request.RegionRequest;
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
 * Data provider to feed the Region entry Table and Selection List
 * @author MEDES-IMPS
 */
public class RegionDataProvider extends ImogBeanDataProvider<RegionProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Region with pagination
	 */
	public RegionDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Region that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public RegionDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Region that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public RegionDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<RegionProxy>> getList(int start, int numRows) {

		RegionRequest request = (RegionRequest) getContext();
		Request<List<RegionProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRegion(start,
									numRows, "nom", true, searchCriterions,
									property);
						else
							result = request.listNonAffectedRegionReverse(
									start, numRows, "nom", true,
									searchCriterions, property);
					} else
						result = request.listRegion(start, numRows, "nom",
								true, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRegion(start,
									numRows, "nom", true, filterCriteria,
									property);
						else
							result = request.listNonAffectedRegionReverse(
									start, numRows, "nom", true,
									filterCriteria, property);
					} else
						result = request.listRegion(start, numRows, "nom",
								true, filterCriteria);
				}

			} else
				result = request.getRegionEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRegion(start, numRows,
								"nom", true, searchCriterions, property);
					else
						result = request.listNonAffectedRegionReverse(start,
								numRows, "nom", true, searchCriterions,
								property);
				} else
					result = request.listRegion(start, numRows, "nom", true,
							searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRegion(start, numRows,
								"nom", true, property);
					else
						result = request.listNonAffectedRegionReverse(start,
								numRows, "nom", true, property);
				} else
					result = request.listRegion(start, numRows, "nom", true);
			}
		}

		if (isFiltered) {
			result.with("nom");
		}

		else {
			result.with("nom");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<RegionProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		RegionRequest request = (RegionRequest) getContext();
		Request<List<RegionProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listRegion(start, numRows, property, asc,
							searchCriterions);
				else
					/* permanent filter only */
					result = request.listRegion(start, numRows, property, asc,
							filterCriteria);

			} else
				result = request.getRegionEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listRegion(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listRegion(start, numRows, property, asc);
		}

		result.with("nom");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		RegionRequest request = (RegionRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRegion(property,
									searchCriterions);
						else
							return request.countNonAffectedRegionReverse(
									property, searchCriterions);
					} else
						return request.countRegion(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRegion(property,
									filterCriteria);
						else
							return request.countNonAffectedRegionReverse(
									property, filterCriteria);
					} else
						return request.countRegion(filterCriteria);
				}

			} else
				return request.countNonAffectedRegion("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRegion(property,
								searchCriterions);
					else
						return request.countNonAffectedRegionReverse(property,
								searchCriterions);
				} else
					return request.countRegion(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRegion(property);
					else
						return request.countNonAffectedRegionReverse(property);
				} else
					return request.countRegion();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.regionRequest();
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

			RegionRequest request = (RegionRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Code
			BasicCriteriaProxy codeCrit = request
					.create(BasicCriteriaProxy.class);
			codeCrit.setField("code");
			codeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			codeCrit.setValue(text);
			buffer.append("(" + NLS.constants().region_field_code() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(codeCrit);

			// Search field Nom
			BasicCriteriaProxy nomCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				nomCrit.setField("nom.francais");
			if (locale.equals("en"))
				nomCrit.setField("nom.english");
			nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().region_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(nomCrit);

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
