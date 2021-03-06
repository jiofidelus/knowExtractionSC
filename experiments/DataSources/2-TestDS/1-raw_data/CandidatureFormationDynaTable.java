package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateCandidatureFormationEvent;
import org.imogene.epicam.client.event.list.ListCandidatureFormationEvent;
import org.imogene.epicam.client.event.view.ViewCandidatureFormationEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.CandidatureFormationFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.CandidatureFormationRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.event.SelectionChangedInTableEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.client.ui.table.ImogColumn;
import org.imogene.web.client.ui.table.ImogDynaTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.epicam.shared.constants.EpicamBirtConstants;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Composite that displays the list of CandidatureFormation entries
 * @author MEDES-IMPS
 */
public class CandidatureFormationDynaTable
		extends
			ImogDynaTable<CandidatureFormationProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public CandidatureFormationDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<CandidatureFormationProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new CandidatureFormationFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadCandidatureFormationDescription()) {
			Column<CandidatureFormationProxy, String> personnelColumn = new PersonnelColumn();
			personnelColumn.setSortable(true);
			table.addColumn(personnelColumn, NLS.constants()
					.candidatureFormation_field_s_personnel());
		}
		if (AccessManager.canReadCandidatureFormationDescription()) {
			Column<CandidatureFormationProxy, String> cDTColumn = new CDTColumn();
			cDTColumn.setSortable(true);
			table.addColumn(cDTColumn, NLS.constants()
					.candidatureFormation_field_s_cDT());
		}
		if (AccessManager.canReadCandidatureFormationRegionApprobation()) {
			Column<CandidatureFormationProxy, String> approuveeRegionColumn = new ApprouveeRegionColumn();
			approuveeRegionColumn.setSortable(true);
			table.addColumn(approuveeRegionColumn, NLS.constants()
					.candidatureFormation_field_s_approuveeRegion());
		}
		if (AccessManager.canReadCandidatureFormationGtcApprobation()) {
			Column<CandidatureFormationProxy, String> approuveeGTCColumn = new ApprouveeGTCColumn();
			approuveeGTCColumn.setSortable(true);
			table.addColumn(approuveeGTCColumn, NLS.constants()
					.candidatureFormation_field_s_approuveeGTC());
		}
		if (AccessManager.canReadCandidatureFormationDescription()) {
			Column<CandidatureFormationProxy, String> districtSanteColumn = new DistrictSanteColumn();
			districtSanteColumn.setSortable(true);
			table.addColumn(districtSanteColumn, NLS.constants()
					.candidatureFormation_field_s_districtSante());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(CandidatureFormationProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/candidatureformation/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "personnel";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return true;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateCandidatureFormation()
				&& AccessManager.canEditCandidatureFormation()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW
							+ "/candidatureformation/", true);
				}
			};
			return command;
		} else
			return null;
	}

	/**
	 * Creates the Delete action command for the entity
	 * @return the delete command
	 */
	public PushButton getDeleteButton() {

		if (AccessManager.canDeleteCandidatureFormation()
				&& AccessManager.canEditCandidatureFormation()) {
			deleteButton = new PushButton(BaseNLS.constants().button_delete());
			deleteButton.setStyleName(imogResources.imogStyle().imogButton());
			deleteButton.addStyleName("Dynatable-Button");
			deleteButton.setVisible(false);
			return deleteButton;
		}

		return null;
	}

	/**
	 * Creates the Handlers linked to the delete button
	 */
	private void setDeleteButtonHandlers() {

		if (AccessManager.canDeleteCandidatureFormation()
				&& AccessManager.canEditCandidatureFormation()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<CandidatureFormationProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().candidatureFormation_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (CandidatureFormationProxy entity : selectedEntities) {
							if (count == 1 || i == count - 1)
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "' ?");
							else
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "', ");
							i = i + 1;
						}

						boolean toDelete = Window.confirm(msg.toString());
						if (toDelete) {

							Request<Void> deleteRequest = getCandidatureFormationRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected CandidatureFormation entries have been deleted");
									requestFactory
											.getEventBus()
											.fireEvent(
													new ListCandidatureFormationEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the CandidatureFormation entries");
									super.onFailure(error);
								}
							});
						}
					}

				}
			}));

			// Selection changed handler	
			registrations.add(requestFactory.getEventBus().addHandler(
					SelectionChangedInTableEvent.TYPE,
					new SelectionChangedInTableEvent.Handler() {
						@Override
						public void noticeSelectionChange(int selectedItems) {
							if (selectedItems > 0)
								deleteButton.setVisible(true);
							else
								deleteButton.setVisible(false);
						}
					}));
		}
	}

	/**
	 * Creates the action command that enables to export the CandidatureFormation
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportCandidatureFormation()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.CAND_FORM_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=candidatureFormation_csv" + "&"
							+ EpicamBirtConstants.REPORT_LOCALE + "="
							+ NLS.constants().locale() + "&"
							+ EpicamBirtConstants.REPORT_FORMAT + "="
							+ EpicamBirtConstants.CSV;

					if (beanDataProvider.getSearchCriterions() != null)
						url = url + getDataProviderCriteria();

					Window.open(url, "_blank", "");
				}
			};
			return command;

		} else
			return null;
	}

	private CandidatureFormationRequest getCandidatureFormationRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.candidatureFormationRequest();
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setDeleteButtonHandlers();
		super.onLoad();
	}

	/**
	 * --------------------- * Internal classes * ----------------------
	 */

	/**
	 * Column for field Personnel
	 * @author MEDES-IMPS
	 */
	private class PersonnelColumn
			extends
				ImogColumn<CandidatureFormationProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public PersonnelColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CandidatureFormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPersonnel() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getPersonnel());
			}
			return value;
		}

		public String getPropertyName() {
			return "personnel";
		}
	}

	/**
	 * Column for field CDT
	 * @author MEDES-IMPS
	 */
	private class CDTColumn
			extends
				ImogColumn<CandidatureFormationProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CDTColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CandidatureFormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCDT() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getCDT());
			}
			return value;
		}

		public String getPropertyName() {
			return "CDT";
		}
	}

	/**
	 * Column for field ApprouveeRegion
	 * @author MEDES-IMPS
	 */
	private class ApprouveeRegionColumn
			extends
				ImogColumn<CandidatureFormationProxy, String> {

		public ApprouveeRegionColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CandidatureFormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getApprouveeRegion() == null)
					value = "";
				else
					value = BooleanUtil.getBooleanDisplayValue(object
							.getApprouveeRegion());
			}
			return value;
		}

		public String getPropertyName() {
			return "approuveeRegion";
		}
	}

	/**
	 * Column for field ApprouveeGTC
	 * @author MEDES-IMPS
	 */
	private class ApprouveeGTCColumn
			extends
				ImogColumn<CandidatureFormationProxy, String> {

		public ApprouveeGTCColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CandidatureFormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getApprouveeGTC() == null)
					value = "";
				else
					value = BooleanUtil.getBooleanDisplayValue(object
							.getApprouveeGTC());
			}
			return value;
		}

		public String getPropertyName() {
			return "approuveeGTC";
		}
	}

	/**
	 * Column for field DistrictSante
	 * @author MEDES-IMPS
	 */
	private class DistrictSanteColumn
			extends
				ImogColumn<CandidatureFormationProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public DistrictSanteColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(CandidatureFormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDistrictSante() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getDistrictSante());
			}
			return value;
		}

		public String getPropertyName() {
			return "districtSante";
		}
	}

}
