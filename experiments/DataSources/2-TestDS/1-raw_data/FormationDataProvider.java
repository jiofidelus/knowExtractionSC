package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.FormationProxy;
import org.imogene.epicam.shared.request.FormationRequest;
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
 * Data provider to feed the Formation entry Table and Selection List
 * @author MEDES-IMPS
 */
public class FormationDataProvider extends ImogBeanDataProvider<FormationProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Formation with pagination
	 */
	public FormationDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Formation that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public FormationDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Formation that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public FormationDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<FormationProxy>> getList(int start, int numRows) {

		FormationRequest request = (FormationRequest) getContext();
		Request<List<FormationProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedFormation(start,
									numRows, "dateDebut", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedFormationReverse(
									start, numRows, "dateDebut", false,
									searchCriterions, property);
					} else
						result = request.listFormation(start, numRows,
								"dateDebut", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedFormation(start,
									numRows, "dateDebut", false,
									filterCriteria, property);
						else
							result = request.listNonAffectedFormationReverse(
									start, numRows, "dateDebut", false,
									filterCriteria, property);
					} else
						result = request.listFormation(start, numRows,
								"dateDebut", false, filterCriteria);
				}

			} else
				result = request.getFormationEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedFormation(start,
								numRows, "dateDebut", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedFormationReverse(start,
								numRows, "dateDebut", false, searchCriterions,
								property);
				} else
					result = request.listFormation(start, numRows, "dateDebut",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedFormation(start,
								numRows, "dateDebut", false, property);
					else
						result = request.listNonAffectedFormationReverse(start,
								numRows, "dateDebut", false, property);
				} else
					result = request.listFormation(start, numRows, "dateDebut",
							false);
			}
		}

		if (isFiltered) {
			result.with("libelle");
			result.with("lieu");
		}

		else {
			result.with("libelle");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<FormationProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		FormationRequest request = (FormationRequest) getContext();
		Request<List<FormationProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listFormation(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listFormation(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getFormationEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listFormation(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listFormation(start, numRows, property, asc);
		}

		result.with("libelle");
		result.with("lieu");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		FormationRequest request = (FormationRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedFormation(property,
									searchCriterions);
						else
							return request.countNonAffectedFormationReverse(
									property, searchCriterions);
					} else
						return request.countFormation(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedFormation(property,
									filterCriteria);
						else
							return request.countNonAffectedFormationReverse(
									property, filterCriteria);
					} else
						return request.countFormation(filterCriteria);
				}

			} else
				return request.countNonAffectedFormation("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedFormation(property,
								searchCriterions);
					else
						return request.countNonAffectedFormationReverse(
								property, searchCriterions);
				} else
					return request.countFormation(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedFormation(property);
					else
						return request
								.countNonAffectedFormationReverse(property);
				} else
					return request.countFormation();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.formationRequest();
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

			FormationRequest request = (FormationRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field DateDebut
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateDebutCrit = request
						.create(BasicCriteriaProxy.class);
				dateDebutCrit.setField("dateDebut");
				dateDebutCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateDebutCrit.setValue(text);
				buffer.append("(" + NLS.constants().formation_field_dateDebut()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateDebutCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Libelle
			BasicCriteriaProxy libelleCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				libelleCrit.setField("libelle.francais");
			if (locale.equals("en"))
				libelleCrit.setField("libelle.english");
			libelleCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			libelleCrit.setValue(text);
			buffer.append("(" + NLS.constants().formation_field_libelle()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(libelleCrit);

			// Search field Lieu
			BasicCriteriaProxy lieuCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				lieuCrit.setField("lieu.francais");
			if (locale.equals("en"))
				lieuCrit.setField("lieu.english");
			lieuCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			lieuCrit.setValue(text);
			buffer.append("(" + NLS.constants().formation_field_lieu() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(lieuCrit);

			// Search field effectuee
			if (text.toLowerCase().equals("true")
					|| text.toLowerCase().equals("false")) {
				BasicCriteriaProxy effectueeCrit = request
						.create(BasicCriteriaProxy.class);
				effectueeCrit.setField("effectuee");
				effectueeCrit
						.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
				effectueeCrit.setValue(text);
				buffer.append("(" + NLS.constants().formation_field_effectuee()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(effectueeCrit);
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
