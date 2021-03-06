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
 * Filter panel to filter the Reception entries
 * @author MEDES-IMPS
 */
public class ReceptionFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox cdt_nomBox;
	private ImogFilterBox cdt_nomFilterBox;
	private TextBox commande_cdt_nomBox;
	private ImogFilterBox commande_cdt_nomFilterBox;
	private DateBox commande_dateBeforeBox;
	private ImogFilterBox commande_dateBeforeFilterBox;
	private DateBox commande_dateAfterBox;
	private ImogFilterBox commande_dateAfterFilterBox;
	private DateBox dateReceptionBeforeBox;
	private ImogFilterBox dateReceptionBeforeFilterBox;
	private DateBox dateReceptionAfterBox;
	private ImogFilterBox dateReceptionAfterFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public ReceptionFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		cdt_nomBox.setValue(null);
		commande_cdt_nomBox.setValue(null);
		commande_dateBeforeBox.setValue(null);
		commande_dateAfterBox.setValue(null);
		dateReceptionBeforeBox.setValue(null);
		dateReceptionAfterBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		cdt_nomBox = new TextBox();
		cdt_nomFilterBox = new ImogFilterBox(cdt_nomBox);
		cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdt_nomFilterBox);

		commande_cdt_nomBox = new TextBox();
		commande_cdt_nomFilterBox = new ImogFilterBox(commande_cdt_nomBox);
		commande_cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(commande_cdt_nomFilterBox);
		commande_dateAfterBox = new DateBox();
		commande_dateAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		commande_dateAfterFilterBox = new ImogFilterBox(commande_dateAfterBox);
		commande_dateAfterFilterBox.setFilterLabel(NLS.constants()
				.commande_field_date()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(commande_dateAfterFilterBox);

		commande_dateBeforeBox = new DateBox();
		commande_dateBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		commande_dateBeforeFilterBox = new ImogFilterBox(commande_dateBeforeBox);
		commande_dateBeforeFilterBox.setFilterLabel(NLS.constants()
				.commande_field_date()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(commande_dateBeforeFilterBox);

		dateReceptionAfterBox = new DateBox();
		dateReceptionAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateReceptionAfterFilterBox = new ImogFilterBox(dateReceptionAfterBox);
		dateReceptionAfterFilterBox.setFilterLabel(NLS.constants()
				.reception_field_dateReception()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateReceptionAfterFilterBox);

		dateReceptionBeforeBox = new DateBox();
		dateReceptionBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateReceptionBeforeFilterBox = new ImogFilterBox(dateReceptionBeforeBox);
		dateReceptionBeforeFilterBox.setFilterLabel(NLS.constants()
				.reception_field_dateReception()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateReceptionBeforeFilterBox);

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

		FilterCriteria cdt_nomCrit = new FilterCriteria();
		cdt_nomCrit.setField("cDT.nom");
		cdt_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		cdt_nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		cdt_nomCrit.setValue(cdt_nomBox.getValue());
		cdt_nomCrit.setValueDisplayName(cdt_nomBox.getValue());
		criteria.add(cdt_nomCrit);

		FilterCriteria commande_cdt_nomCrit = new FilterCriteria();
		commande_cdt_nomCrit.setField("commande.cDT.nom");
		commande_cdt_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		commande_cdt_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		commande_cdt_nomCrit.setValue(commande_cdt_nomBox.getValue());
		commande_cdt_nomCrit
				.setValueDisplayName(commande_cdt_nomBox.getValue());
		criteria.add(commande_cdt_nomCrit);

		if (commande_dateBeforeBox.getValue() != null) {
			FilterCriteria commande_dateBeforeCrit = new FilterCriteria();
			commande_dateBeforeCrit.setField("commande.date");
			commande_dateBeforeCrit.setFieldDisplayName(NLS.constants()
					.commande_field_date()
					+ BaseNLS.constants().search_operator_inferior());
			commande_dateBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			commande_dateBeforeCrit.setValue(DateUtil
					.getDate(commande_dateBeforeBox.getValue()));
			commande_dateBeforeCrit.setValueDisplayName(DateUtil
					.getDate(commande_dateBeforeBox.getValue()));
			criteria.add(commande_dateBeforeCrit);
		}

		if (commande_dateAfterBox.getValue() != null) {
			FilterCriteria commande_dateAfterCrit = new FilterCriteria();
			commande_dateAfterCrit.setField("commande.date");
			commande_dateAfterCrit.setFieldDisplayName(NLS.constants()
					.commande_field_date()
					+ BaseNLS.constants().search_operator_superior());
			commande_dateAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			commande_dateAfterCrit.setValue(DateUtil
					.getDate(commande_dateAfterBox.getValue()));
			commande_dateAfterCrit.setValueDisplayName(DateUtil
					.getDate(commande_dateAfterBox.getValue()));
			criteria.add(commande_dateAfterCrit);
		}

		if (dateReceptionBeforeBox.getValue() != null) {
			FilterCriteria dateReceptionBeforeCrit = new FilterCriteria();
			dateReceptionBeforeCrit.setField("dateReception");
			dateReceptionBeforeCrit.setFieldDisplayName(NLS.constants()
					.reception_field_dateReception()
					+ BaseNLS.constants().search_operator_inferior());
			dateReceptionBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateReceptionBeforeCrit.setValue(DateUtil
					.getDate(dateReceptionBeforeBox.getValue()));
			dateReceptionBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateReceptionBeforeBox.getValue()));
			criteria.add(dateReceptionBeforeCrit);
		}

		if (dateReceptionAfterBox.getValue() != null) {
			FilterCriteria dateReceptionAfterCrit = new FilterCriteria();
			dateReceptionAfterCrit.setField("dateReception");
			dateReceptionAfterCrit.setFieldDisplayName(NLS.constants()
					.reception_field_dateReception()
					+ BaseNLS.constants().search_operator_superior());
			dateReceptionAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateReceptionAfterCrit.setValue(DateUtil
					.getDate(dateReceptionAfterBox.getValue()));
			dateReceptionAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateReceptionAfterBox.getValue()));
			criteria.add(dateReceptionAfterCrit);
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
			cdt_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadCentreDiagTraitDescription())
			commande_cdt_nomFilterBox.setVisible(false);
		if (!AccessManager.canReadCommandeInformationsDepart()) {
			commande_dateBeforeFilterBox.setVisible(false);
			commande_dateAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadReceptionDescription()) {
			dateReceptionBeforeFilterBox.setVisible(false);
			dateReceptionAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
