package org.imogene.epicam.client.ui.filter;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.widget.ImogBooleanAsRadio;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.widget.ImogBooleanAsList;
import org.imogene.web.client.ui.table.filter.ImogFilterBox;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.FilterCriteria;
import org.imogene.web.client.util.SimpleImogDateFormat;

import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Filter panel to filter the Ravitaillement entries
 * @author MEDES-IMPS
 */
public class RavitaillementFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox cdtdepart_nomBox;
	private ImogFilterBox cdtdepart_nomFilterBox;
	private DateBox dateDepartBeforeBox;
	private ImogFilterBox dateDepartBeforeFilterBox;
	private DateBox dateDepartAfterBox;
	private ImogFilterBox dateDepartAfterFilterBox;
	private TextBox cdtarrivee_nomBox;
	private ImogFilterBox cdtarrivee_nomFilterBox;
	private DateBox dateArriveeBeforeBox;
	private ImogFilterBox dateArriveeBeforeFilterBox;
	private DateBox dateArriveeAfterBox;
	private ImogFilterBox dateArriveeAfterFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public RavitaillementFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		cdtdepart_nomBox.setValue(null);
		dateDepartBeforeBox.setValue(null);
		dateDepartAfterBox.setValue(null);
		cdtarrivee_nomBox.setValue(null);
		dateArriveeBeforeBox.setValue(null);
		dateArriveeAfterBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		cdtdepart_nomBox = new TextBox();
		cdtdepart_nomFilterBox = new ImogFilterBox(cdtdepart_nomBox);
		cdtdepart_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdtdepart_nomFilterBox);

		dateDepartAfterBox = new DateBox();
		dateDepartAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDepartAfterFilterBox = new ImogFilterBox(dateDepartAfterBox);
		dateDepartAfterFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateDepart()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateDepartAfterFilterBox);

		dateDepartBeforeBox = new DateBox();
		dateDepartBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDepartBeforeFilterBox = new ImogFilterBox(dateDepartBeforeBox);
		dateDepartBeforeFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateDepart()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateDepartBeforeFilterBox);

		cdtarrivee_nomBox = new TextBox();
		cdtarrivee_nomFilterBox = new ImogFilterBox(cdtarrivee_nomBox);
		cdtarrivee_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdtarrivee_nomFilterBox);

		dateArriveeAfterBox = new DateBox();
		dateArriveeAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateArriveeAfterFilterBox = new ImogFilterBox(dateArriveeAfterBox);
		dateArriveeAfterFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateArrivee()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateArriveeAfterFilterBox);

		dateArriveeBeforeBox = new DateBox();
		dateArriveeBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateArriveeBeforeFilterBox = new ImogFilterBox(dateArriveeBeforeBox);
		dateArriveeBeforeFilterBox.setFilterLabel(NLS.constants()
				.ravitaillement_field_dateArrivee()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateArriveeBeforeFilterBox);

		deletedEntityBox = new ImogBooleanAsRadio();
		deletedEntityBox.isStrict(true);
		deletedEntityBox.setEnabled(true);
		deletedEntityBox.setValue(false);
		deletedEntityBoxFilterBox = new ImogFilterBox(deletedEntityBox);
		deletedEntityBoxFilterBox.setFilterLabel(BaseNLS.constants()
				.entity_field_deleted());
		addTableFilter(deletedEntityBoxFilterBox);
	}

	@Override
	public List<FilterCriteria> getFilterCriteria() {

		String locale = NLS.constants().locale();

		List<FilterCriteria> criteria = new ArrayList<FilterCriteria>();

		FilterCriteria cdtdepart_nomCrit = new FilterCriteria();
		cdtdepart_nomCrit.setField("cDTDepart.nom");
		cdtdepart_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		cdtdepart_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		cdtdepart_nomCrit.setValue(cdtdepart_nomBox.getValue());
		cdtdepart_nomCrit.setValueDisplayName(cdtdepart_nomBox.getValue());
		criteria.add(cdtdepart_nomCrit);

		if (dateDepartBeforeBox.getValue() != null) {
			FilterCriteria dateDepartBeforeCrit = new FilterCriteria();
			dateDepartBeforeCrit.setField("dateDepart");
			dateDepartBeforeCrit.setFieldDisplayName(NLS.constants()
					.ravitaillement_field_dateDepart()
					+ BaseNLS.constants().search_operator_inferior());
			dateDepartBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateDepartBeforeCrit.setValue(DateUtil.getDate(dateDepartBeforeBox
					.getValue()));
			dateDepartBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateDepartBeforeBox.getValue()));
			criteria.add(dateDepartBeforeCrit);
		}

		if (dateDepartAfterBox.getValue() != null) {
			FilterCriteria dateDepartAfterCrit = new FilterCriteria();
			dateDepartAfterCrit.setField("dateDepart");
			dateDepartAfterCrit.setFieldDisplayName(NLS.constants()
					.ravitaillement_field_dateDepart()
					+ BaseNLS.constants().search_operator_superior());
			dateDepartAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateDepartAfterCrit.setValue(DateUtil.getDate(dateDepartAfterBox
					.getValue()));
			dateDepartAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateDepartAfterBox.getValue()));
			criteria.add(dateDepartAfterCrit);
		}

		FilterCriteria cdtarrivee_nomCrit = new FilterCriteria();
		cdtarrivee_nomCrit.setField("cDTArrivee.nom");
		cdtarrivee_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		cdtarrivee_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		cdtarrivee_nomCrit.setValue(cdtarrivee_nomBox.getValue());
		cdtarrivee_nomCrit.setValueDisplayName(cdtarrivee_nomBox.getValue());
		criteria.add(cdtarrivee_nomCrit);

		if (dateArriveeBeforeBox.getValue() != null) {
			FilterCriteria dateArriveeBeforeCrit = new FilterCriteria();
			dateArriveeBeforeCrit.setField("dateArrivee");
			dateArriveeBeforeCrit.setFieldDisplayName(NLS.constants()
					.ravitaillement_field_dateArrivee()
					+ BaseNLS.constants().search_operator_inferior());
			dateArriveeBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateArriveeBeforeCrit.setValue(DateUtil
					.getDate(dateArriveeBeforeBox.getValue()));
			dateArriveeBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateArriveeBeforeBox.getValue()));
			criteria.add(dateArriveeBeforeCrit);
		}

		if (dateArriveeAfterBox.getValue() != null) {
			FilterCriteria dateArriveeAfterCrit = new FilterCriteria();
			dateArriveeAfterCrit.setField("dateArrivee");
			dateArriveeAfterCrit.setFieldDisplayName(NLS.constants()
					.ravitaillement_field_dateArrivee()
					+ BaseNLS.constants().search_operator_superior());
			dateArriveeAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateArriveeAfterCrit.setValue(DateUtil.getDate(dateArriveeAfterBox
					.getValue()));
			dateArriveeAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateArriveeAfterBox.getValue()));
			criteria.add(dateArriveeAfterCrit);
		}

		FilterCriteria deletedEntityCrit = new FilterCriteria();
		deletedEntityCrit.setField("deleted");
		deletedEntityCrit.setFieldDisplayName(BaseNLS.constants()
				.entity_field_deleted());
		if (deletedEntityBox.getValue()) {
			deletedEntityCrit
					.setOperation(CriteriaConstants.OPERATOR_ISNOTNULL);
			deletedEntityCrit.setValue("true");
			deletedEntityCrit.setValueDisplayName(BaseNLS.constants()
					.boolean_true());
		} else {
			deletedEntityCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL);
			deletedEntityCrit.setValue("false");
			deletedEntityCrit.setValueDisplayName(BaseNLS.constants()
					.boolean_false());
		}
		criteria.add(deletedEntityCrit);

		return criteria;
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadCentreDiagTraitDescription())
			cdtdepart_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadRavitaillementInformationsDepart()) {
			dateDepartBeforeFilterBox.setVisible(false);
			dateDepartAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadCentreDiagTraitDescription())
			cdtarrivee_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadRavitaillementInformationArrivee()) {
			dateArriveeBeforeFilterBox.setVisible(false);
			dateArriveeAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
