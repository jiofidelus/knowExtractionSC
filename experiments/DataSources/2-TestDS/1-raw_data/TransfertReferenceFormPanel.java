package org.imogene.epicam.client.ui.workflow.panel;

import org.imogene.epicam.client.EpicamIconConstants;
import org.imogene.epicam.client.event.list.ListTransfertReferenceEvent;
import org.imogene.epicam.client.ui.workflow.TransfertReferenceEditorWorkflow;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.epicam.shared.proxy.DistrictSanteProxy;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.epicam.shared.proxy.PatientProxy;
import org.imogene.epicam.shared.proxy.RegionProxy;
import org.imogene.epicam.shared.proxy.DistrictSanteProxy;
import org.imogene.epicam.shared.proxy.CentreDiagTraitProxy;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.client.ui.panel.WrapperPanel;
import org.imogene.web.client.event.GoHomeEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel that contains the TransfertReference Workflow
 * @author Medes-IMPS
 */
public class TransfertReferenceFormPanel extends Composite {

	private static final Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, TransfertReferenceFormPanel> {
	}

	@UiField(provided = true)
	WrapperPanel wrapperPanel;
	@UiField(provided = true)
	TransfertReferenceEditorWorkflow editorWorkflow;

	/**
	 * Constructor
	 * @param requestFactory the application requestFactory
	 * @param entityId the id of the entity to be managed by the workflow. null if an entity is being created
	 * @param parent parent composite if the panel is contained in a RelationPopupPanel
	 * @param initField the field that initiated the display in a RelationPopupPanel
	 * @param returnToList true if after closing the wokflow, the application shall display the list of entities, false otherwise
	 */
	public TransfertReferenceFormPanel(EpicamRequestFactory requestFactory,
			String entityId, RelationPopupPanel parent, String initField) {

		wrapperPanel = new WrapperPanel();
		wrapperPanel.setWidth("90%");
		Label titleContainer = wrapperPanel.getTitleLabel();

		if (entityId != null) {
			if (parent == null)
				editorWorkflow = new TransfertReferenceEditorWorkflow(
						requestFactory, entityId, titleContainer);
			else {
				editorWorkflow = new TransfertReferenceEditorWorkflow(
						requestFactory, entityId, titleContainer, parent,
						initField);
			}
		} else {
			if (parent == null) {
				editorWorkflow = new TransfertReferenceEditorWorkflow(
						requestFactory, titleContainer);
			} else {
				editorWorkflow = new TransfertReferenceEditorWorkflow(
						requestFactory, titleContainer, parent, initField);
			}
		}

		if (EpicamIconConstants.TRANSFERTREFERENCE_ICON != null)
			wrapperPanel.setIcon(EpicamIconConstants.TRANSFERTREFERENCE_ICON);

		if (editorWorkflow.getEditButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getEditButton());
		if (editorWorkflow.getCloseButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getCloseButton());
		if (editorWorkflow.getSaveButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getSaveButton());
		if (editorWorkflow.getCancelButton() != null)
			wrapperPanel.addHeaderWidget(editorWorkflow.getCancelButton());

		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Constructor
	 * @param requestFactory the application requestFactory
	 * @param entityId the id of the entity to be managed by the workflow. null if an entity is being created	 
	 */
	public TransfertReferenceFormPanel(EpicamRequestFactory requestFactory,
			String entityId) {
		this(requestFactory, entityId, null, null);
	}

	public void setCloseEvent(GwtEvent<?> closeEvent) {
		editorWorkflow.setCloseEvent(closeEvent);
	}

	/**
	 * Setter to inject a Region value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegion(RegionProxy value, boolean isLocked) {
		editorWorkflow.setRegion(value, isLocked);
	}

	/**
	 * Setter to inject a Region value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setRegion(RegionProxy value) {
		setRegion(value, false);
	}
	/**
	 * Setter to inject a DistrictSante value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDistrictSante(DistrictSanteProxy value, boolean isLocked) {
		editorWorkflow.setDistrictSante(value, isLocked);
	}

	/**
	 * Setter to inject a DistrictSante value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setDistrictSante(DistrictSanteProxy value) {
		setDistrictSante(value, false);
	}
	/**
	 * Setter to inject a CentreDiagTrait value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDTDepart(CentreDiagTraitProxy value, boolean isLocked) {
		editorWorkflow.setCDTDepart(value, isLocked);
	}

	/**
	 * Setter to inject a CentreDiagTrait value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setCDTDepart(CentreDiagTraitProxy value) {
		setCDTDepart(value, false);
	}
	/**
	 * Setter to inject a Patient value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPatient(PatientProxy value, boolean isLocked) {
		editorWorkflow.setPatient(value, isLocked);
	}

	/**
	 * Setter to inject a Patient value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setPatient(PatientProxy value) {
		setPatient(value, false);
	}
	/**
	 * Setter to inject a Region value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setRegionArrivee(RegionProxy value, boolean isLocked) {
		editorWorkflow.setRegionArrivee(value, isLocked);
	}

	/**
	 * Setter to inject a Region value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setRegionArrivee(RegionProxy value) {
		setRegionArrivee(value, false);
	}
	/**
	 * Setter to inject a DistrictSante value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setDistrictSanteArrivee(DistrictSanteProxy value,
			boolean isLocked) {
		editorWorkflow.setDistrictSanteArrivee(value, isLocked);
	}

	/**
	 * Setter to inject a DistrictSante value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setDistrictSanteArrivee(DistrictSanteProxy value) {
		setDistrictSanteArrivee(value, false);
	}
	/**
	 * Setter to inject a CentreDiagTrait value into the workflow and the editor
	 * @param value the value to be injected
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setCDTArrivee(CentreDiagTraitProxy value, boolean isLocked) {
		editorWorkflow.setCDTArrivee(value, isLocked);
	}

	/**
	 * Setter to inject a CentreDiagTrait value into the workflow and the editor
	 * @param value the value to be injected
	 */
	public void setCDTArrivee(CentreDiagTraitProxy value) {
		setCDTArrivee(value, false);
	}

}
