package org.imogene.epicam.client.ui.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.web.bindery.requestfactory.shared.Receiver;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.OutBoxProxy;
import org.imogene.epicam.shared.request.OutBoxRequest;

import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.web.shared.request.ImogEntityRequest;
import com.google.web.bindery.requestfactory.shared.Request;

import org.imogene.web.client.event.FieldValueChangeEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.*;
import org.imogene.web.client.ui.field.binary.*;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.field.relation.multi.ImogMultiRelationBox;
import org.imogene.web.client.ui.field.relation.single.ImogSingleRelationBox;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.client.ui.panel.WrapperPanel;
import org.imogene.web.client.util.NumericUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import org.imogene.epicam.client.ui.workflow.panel.PatientFormPanel;
import org.imogene.epicam.client.event.save.SavePatientEvent;
import org.imogene.epicam.client.dataprovider.PatientDataProvider;
import org.imogene.epicam.client.ui.filter.PatientFilterPanel;
import org.imogene.epicam.shared.proxy.PatientProxy;

/**
 * Editor that provides the UI components that allow a OutBoxProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class OutBoxEditor extends Composite
		implements
			Editor<OutBoxProxy>,
			HasEditorDelegate<OutBoxProxy>,
			HasEditorErrors<OutBoxProxy> {

	interface Binder extends UiBinder<Widget, OutBoxEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<OutBoxProxy> delegate;

	private OutBoxProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Adresse section widgets */
	@UiField
	@Ignore
	FieldGroupPanel adresseSection;
	@UiField(provided = true)
	ImogSingleRelationBox<PatientProxy> patient;
	private PatientDataProvider patientDataProvider;

	/* MessageInformation section widgets */
	@UiField
	@Ignore
	FieldGroupPanel messageInformationSection;
	@UiField
	ImogTextAreaBox message;
	@UiField
	ImogTextAreaBox reponse;
	@UiField
	ImogSingleEnumBox statut;
	@UiField
	ImogDateBox dateDernierEssai;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public OutBoxEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public OutBoxEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Adresse section widgets */
		adresseSection.setGroupTitle(NLS.constants().outBox_group_adresse());
		patient.setLabel(NLS.constants().outBox_field_patient());

		/* MessageInformation section widgets */
		messageInformationSection.setGroupTitle(NLS.constants()
				.outBox_group_messageInformation());
		message.setLabel(NLS.constants().outBox_field_message());
		reponse.setLabel(NLS.constants().outBox_field_reponse());
		statut.setLabel(NLS.constants().outBox_field_statut());
		statut.addItem(EpicamEnumConstants.OUTBOX_STATUT_ERREUR, NLS
				.constants().outBox_statut_erreur_option());
		statut.addItem(EpicamEnumConstants.OUTBOX_STATUT_AENVOYER, NLS
				.constants().outBox_statut_aEnvoyer_option());
		statut.addItem(EpicamEnumConstants.OUTBOX_STATUT_SUCCES, NLS
				.constants().outBox_statut_succes_option());
		dateDernierEssai.setLabel(NLS.constants()
				.outBox_field_dateDernierEssai());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  patient */
		patientDataProvider = new PatientDataProvider(requestFactory);
		if (hideButtons) // in popup, relation buttons hidden
			patient = new ImogSingleRelationBox<PatientProxy>(
					patientDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled												
			if (AccessManager.canCreatePatient()
					&& AccessManager.canEditPatient())
				patient = new ImogSingleRelationBox<PatientProxy>(
						patientDataProvider, EpicamRenderer.get());
			else
				patient = new ImogSingleRelationBox<PatientProxy>(false,
						patientDataProvider, EpicamRenderer.get());
		}

	}

	/**
	 * Sets the edition mode
	 * @param isEdited true to enable the edition of the form
	 */
	public void setEdited(boolean isEdited) {

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		/* Adresse section widgets */
		patient.setEdited(isEdited);

		/* MessageInformation section widgets */
		message.setEdited(isEdited);
		// readonly field
		reponse.setEdited(false);
		// readonly field
		statut.setEdited(false);
		// readonly field
		dateDernierEssai.setEdited(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Adresse section widgets visibility */
		if (!AccessManager.canReadOutBoxAdresse())
			adresseSection.setVisible(false);

		/* MessageInformation section widgets visibility */
		if (!AccessManager.canReadOutBoxMessageInformation())
			messageInformationSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Adresse section widgets visibility */
		if (!AccessManager.canEditOutBoxAdresse())
			adresseSection.setVisible(false);

		/* MessageInformation section widgets visibility */
		if (!AccessManager.canEditOutBoxMessageInformation())
			messageInformationSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(OutBoxRequest ctx) {
	}

	/**
	 * Manages editor updates when a field value changes
	 */
	private void setFieldValueChangeHandler() {

		registrations.add(requestFactory.getEventBus().addHandler(
				FieldValueChangeEvent.TYPE,
				new FieldValueChangeEvent.Handler() {
					@Override
					public void onValueChange(ImogField<?> field) {

						// field dependent visibility management
						computeVisibility(field, false);

					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

	}

	/**
	 * Setter to inject a Patient value
	 * @param value the value to be injected into the editor
	 * @param isLocked true if relation field shall be locked (non editable)
	 */
	public void setPatient(PatientProxy value, boolean isLocked) {
		patient.setLocked(isLocked);
		patient.setValue(value);

	}

	/** Widget update for field patient */
	private void clearPatientWidget() {
		patient.clear();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Patient */
		patient.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (patient.getValue() != null) {
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					PatientFormPanel form = new PatientFormPanel(
							requestFactory, patient.getValue().getId(),
							relationPopup, "patient");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Patient */
		patient.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RelationPopupPanel relationPopup = new RelationPopupPanel();
				PatientFormPanel form = new PatientFormPanel(requestFactory,
						null, relationPopup, "patient");
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a Patient is created or updated from the relation field Patient */
		registrations.add(requestFactory.getEventBus().addHandler(
				SavePatientEvent.TYPE, new SavePatientEvent.Handler() {
					@Override
					public void savePatient(PatientProxy value) {
						patient.setValue(value);
					}
					@Override
					public void savePatient(PatientProxy value, String initField) {
						if (initField.equals("patient"))
							patient.setValue(value, true);
					}
				}));

	}

	/**
	 * Gets the OutBoxProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public OutBoxProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the OutBoxProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(OutBoxProxy editedValue) {
		this.editedValue = editedValue;

	}

	/**
	 *
	 */
	public void raiseNotUniqueError(String field) {
		delegate.recordError(BaseNLS.messages().error_not_unique(), null, field);
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

		// message is a required field
		if (message.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"message");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Adresse field group */
		patient.setLabelWidth(width);

		/* MessageInformation field group */
		message.setLabelWidth(width);
		reponse.setLabelWidth(width);
		statut.setLabelWidth(width);
		dateDernierEssai.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Adresse field group */
		patient.setBoxWidth(width);

		/* MessageInformation field group */
		message.setBoxWidth(width);
		reponse.setBoxWidth(width);
		statut.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<OutBoxProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> messageFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("message"))
						messageFieldErrors.add(error);

				}
			}
			if (messageFieldErrors.size() > 0)
				message.showErrors(messageFieldErrors);
		}
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
		setRelationHandlers();
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
