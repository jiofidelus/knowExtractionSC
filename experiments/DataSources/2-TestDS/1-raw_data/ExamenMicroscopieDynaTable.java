package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateExamenMicroscopieEvent;
import org.imogene.epicam.client.event.list.ListExamenMicroscopieEvent;
import org.imogene.epicam.client.event.view.ViewExamenMicroscopieEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.ExamenMicroscopieFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.ExamenMicroscopieProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.ExamenMicroscopieRequest;
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
 * Composite that displays the list of ExamenMicroscopie entries
 * @author MEDES-IMPS
 */
public class ExamenMicroscopieDynaTable
		extends
			ImogDynaTable<ExamenMicroscopieProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public ExamenMicroscopieDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<ExamenMicroscopieProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new ExamenMicroscopieFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadExamenMicroscopieExamen()) {
			Column<ExamenMicroscopieProxy, String> casTbColumn = new CasTbColumn();
			casTbColumn.setSortable(true);
			table.addColumn(casTbColumn, NLS.constants()
					.examenMicroscopie_field_s_casTb());
		}
		if (AccessManager.canReadExamenMicroscopieExamen()) {
			Column<ExamenMicroscopieProxy, String> dateColumn = new DateColumn();
			dateColumn.setSortable(true);
			table.addColumn(dateColumn, NLS.constants()
					.examenMicroscopie_field_s_date());
		}
		if (AccessManager.canReadExamenMicroscopieExamen()) {
			Column<ExamenMicroscopieProxy, String> raisonDepistageColumn = new RaisonDepistageColumn();
			raisonDepistageColumn.setSortable(true);
			table.addColumn(raisonDepistageColumn, NLS.constants()
					.examenMicroscopie_field_s_raisonDepistage());
		}
		if (AccessManager.canReadExamenMicroscopieExamen()) {
			Column<ExamenMicroscopieProxy, String> resultatColumn = new ResultatColumn();
			resultatColumn.setSortable(true);
			table.addColumn(resultatColumn, NLS.constants()
					.examenMicroscopie_field_s_resultat());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(ExamenMicroscopieProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/examenmicroscopie/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "date";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return false;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateExamenMicroscopie()
				&& AccessManager.canEditExamenMicroscopie()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/examenmicroscopie/",
							true);
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

		if (AccessManager.canDeleteExamenMicroscopie()
				&& AccessManager.canEditExamenMicroscopie()) {
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

		if (AccessManager.canDeleteExamenMicroscopie()
				&& AccessManager.canEditExamenMicroscopie()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<ExamenMicroscopieProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().examenMicroscopie_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (ExamenMicroscopieProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getExamenMicroscopieRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected ExamenMicroscopie entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListExamenMicroscopieEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the ExamenMicroscopie entries");
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
	 * Creates the action command that enables to export the ExamenMicroscopie
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportExamenMicroscopie()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.EXAM_MICRO_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=examenMicroscopie_csv" + "&"
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

	private ExamenMicroscopieRequest getExamenMicroscopieRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.examenMicroscopieRequest();
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
	 * Column for field CasTb
	 * @author MEDES-IMPS
	 */
	private class CasTbColumn
			extends
				ImogColumn<ExamenMicroscopieProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CasTbColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenMicroscopieProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCasTb() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getCasTb());
			}
			return value;
		}

		public String getPropertyName() {
			return "casTb";
		}
	}

	/**
	 * Column for field Date
	 * @author MEDES-IMPS
	 */
	private class DateColumn extends ImogColumn<ExamenMicroscopieProxy, String> {

		public DateColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenMicroscopieProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDate() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDate());
			}
			return value;
		}

		public String getPropertyName() {
			return "date";
		}
	}

	/**
	 * Column for field RaisonDepistage
	 * @author MEDES-IMPS
	 */
	private class RaisonDepistageColumn
			extends
				ImogColumn<ExamenMicroscopieProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public RaisonDepistageColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenMicroscopieProxy object) {
			String value = null;
			if (object != null) {
				if (object.getRaisonDepistage() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(
							ExamenMicroscopieProxy.class, "raisonDepistage",
							object.getRaisonDepistage());
			}
			return value;
		}

		public String getPropertyName() {
			return "raisonDepistage";
		}
	}

	/**
	 * Column for field Resultat
	 * @author MEDES-IMPS
	 */
	private class ResultatColumn
			extends
				ImogColumn<ExamenMicroscopieProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public ResultatColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(ExamenMicroscopieProxy object) {
			String value = null;
			if (object != null) {
				if (object.getResultat() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(
							ExamenMicroscopieProxy.class, "resultat",
							object.getResultat());
			}
			return value;
		}

		public String getPropertyName() {
			return "resultat";
		}
	}

}
