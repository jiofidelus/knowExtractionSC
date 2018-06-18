package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateLaboratoireReferenceEvent;
import org.imogene.epicam.client.event.list.ListLaboratoireReferenceEvent;
import org.imogene.epicam.client.event.view.ViewLaboratoireReferenceEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.LaboratoireReferenceFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.LaboratoireReferenceProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.LaboratoireReferenceRequest;
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
 * Composite that displays the list of LaboratoireReference entries
 * @author MEDES-IMPS
 */
public class LaboratoireReferenceDynaTable
		extends
			ImogDynaTable<LaboratoireReferenceProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public LaboratoireReferenceDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<LaboratoireReferenceProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new LaboratoireReferenceFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadLaboratoireReferenceDescription()) {
			Column<LaboratoireReferenceProxy, String> nomColumn = new NomColumn();
			nomColumn.setSortable(true);
			table.addColumn(nomColumn, NLS.constants()
					.laboratoireReference_field_s_nom());
		}
		if (AccessManager.canReadLaboratoireReferenceDescription()) {
			Column<LaboratoireReferenceProxy, String> natureColumn = new NatureColumn();
			natureColumn.setSortable(true);
			table.addColumn(natureColumn, NLS.constants()
					.laboratoireReference_field_s_nature());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(LaboratoireReferenceProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/laboratoirereference/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "nom";
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

		if (AccessManager.canCreateLaboratoireReference()
				&& AccessManager.canEditLaboratoireReference()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW
							+ "/laboratoirereference/", true);
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

		if (AccessManager.canDeleteLaboratoireReference()
				&& AccessManager.canEditLaboratoireReference()) {
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

		if (AccessManager.canDeleteLaboratoireReference()
				&& AccessManager.canEditLaboratoireReference()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<LaboratoireReferenceProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().laboratoireReference_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (LaboratoireReferenceProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getLaboratoireReferenceRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected LaboratoireReference entries have been deleted");
									requestFactory
											.getEventBus()
											.fireEvent(
													new ListLaboratoireReferenceEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the LaboratoireReference entries");
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
	 * Creates the action command that enables to export the LaboratoireReference
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportLaboratoireReference()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.LAB_REF_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=laboratoireReference_csv" + "&"
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

	private LaboratoireReferenceRequest getLaboratoireReferenceRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.laboratoireReferenceRequest();
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
	 * Column for field Nom
	 * @author MEDES-IMPS
	 */
	private class NomColumn
			extends
				ImogColumn<LaboratoireReferenceProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public NomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LaboratoireReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNom() == null)
					value = "";
				else
					value = renderer.getLocalizedText(object.getNom());
			}
			return value;
		}

		public String getPropertyName() {
			String locale = NLS.constants().locale();
			if (locale.equals("fr"))
				return "nom.francais";
			if (locale.equals("en"))
				return "nom.english";
			return "nom";
		}
	}

	/**
	 * Column for field Nature
	 * @author MEDES-IMPS
	 */
	private class NatureColumn
			extends
				ImogColumn<LaboratoireReferenceProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public NatureColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(LaboratoireReferenceProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNature() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(
							LaboratoireReferenceProxy.class, "nature",
							object.getNature());
			}
			return value;
		}

		public String getPropertyName() {
			return "nature";
		}
	}

}