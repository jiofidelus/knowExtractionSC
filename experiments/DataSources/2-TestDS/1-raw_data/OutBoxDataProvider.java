package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.OutBoxProxy;
import org.imogene.epicam.shared.request.OutBoxRequest;
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
 * Data provider to feed the OutBox entry Table and Selection List
 * @author MEDES-IMPS
 */
public class OutBoxDataProvider extends ImogBeanDataProvider<OutBoxProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity OutBox with pagination
	 */
	public OutBoxDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity OutBox that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public OutBoxDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity OutBox that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public OutBoxDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<OutBoxProxy>> getList(int start, int numRows) {

		OutBoxRequest request = (OutBoxRequest) getContext();
		Request<List<OutBoxProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedOutBox(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedOutBoxReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listOutBox(start, numRows, "modified",
								false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedOutBox(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedOutBoxReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listOutBox(start, numRows, "modified",
								false, filterCriteria);
				}

			} else
				result = request.getOutBoxEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedOutBox(start, numRows,
								"modified", false, searchCriterions, property);
					else
						result = request.listNonAffectedOutBoxReverse(start,
								numRows, "modified", false, searchCriterions,
								property);
				} else
					result = request.listOutBox(start, numRows, "modified",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedOutBox(start, numRows,
								"modified", false, property);
					else
						result = request.listNonAffectedOutBoxReverse(start,
								numRows, "modified", false, property);
				} else
					result = request.listOutBox(start, numRows, "modified",
							false);
			}
		}

		if (isFiltered) {
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<OutBoxProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		OutBoxRequest request = (OutBoxRequest) getContext();
		Request<List<OutBoxProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listOutBox(start, numRows, property, asc,
							searchCriterions);
				else
					/* permanent filter only */
					result = request.listOutBox(start, numRows, property, asc,
							filterCriteria);

			} else
				result = request.getOutBoxEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listOutBox(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listOutBox(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		OutBoxRequest request = (OutBoxRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedOutBox(property,
									searchCriterions);
						else
							return request.countNonAffectedOutBoxReverse(
									property, searchCriterions);
					} else
						return request.countOutBox(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedOutBox(property,
									filterCriteria);
						else
							return request.countNonAffectedOutBoxReverse(
									property, filterCriteria);
					} else
						return request.countOutBox(filterCriteria);
				}

			} else
				return request.countNonAffectedOutBox("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedOutBox(property,
								searchCriterions);
					else
						return request.countNonAffectedOutBoxReverse(property,
								searchCriterions);
				} else
					return request.countOutBox(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedOutBox(property);
					else
						return request.countNonAffectedOutBoxReverse(property);
				} else
					return request.countOutBox();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.outBoxRequest();
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

			OutBoxRequest request = (OutBoxRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Message
			BasicCriteriaProxy messageCrit = request
					.create(BasicCriteriaProxy.class);
			messageCrit.setField("message");
			messageCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			messageCrit.setValue(text);
			buffer.append("(" + NLS.constants().outBox_field_message() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(messageCrit);

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
