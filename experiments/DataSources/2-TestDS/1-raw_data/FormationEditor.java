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
import org.imogene.epicam.shared.proxy.FormationProxy;
import org.imogene.epicam.shared.request.FormationRequest;

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

import org.imogene.epicam.client.ui.workflow.panel.CandidatureFormationFormPanel;
import org.imogene.epicam.client.event.save.SaveCandidatureFormationEvent;
import org.imogene.epicam.client.dataprovider.CandidatureFormationDataProvider;
import org.imogene.epicam.client.ui.filter.CandidatureFormationFilterPanel;
import org.imogene.epicam.shared.proxy.CandidatureFormationProxy;

/**
 * Editor that provides the UI components that allow a FormationProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class FormationEditor extends Composite
		implements
			Editor<FormationProxy>,
			HasEditorDelegate<FormationProxy>,
			HasEditorErrors<FormationProxy> {

	interface Binder extends UiBinder<Widget, FormationEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<FormationProxy> delegate;

	private FormationProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;
	private List<String> locales = Arrays.asList("fr", "en");

	/* Description section widgets */
	@UiField
	@Ignore
	FieldGroupPanel descriptionSection;
	@UiField(provided = true)
	ImogLocalizedTextBox libelle;
	@UiField
	ImogDateBox dateDebut;
	@UiField
	ImogDateBox dateFin;
	@UiField(provided = true)
	ImogLocalizedTextBox lieu;
	@UiField(provided = true)
	ImogLocalizedTextAreaBox objectifs;
	@UiField
	ImogBooleanBox effectuee;
	@UiField(provided = true)
	ImogMultiRelationBox<CandidatureFormationProxy> candidatures;
	private CandidatureFormationDataProvider candidaturesDataProvider;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public FormationEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		setRelationFields();

		libelle = new ImogLocalizedTextBox(locales, NLS.constants().locale());
		lieu = new ImogLocalizedTextBox(locales, NLS.constants().locale());
		objectifs = new ImogLocalizedTextAreaBox(locales, NLS.constants()
				.locale());

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public FormationEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Description section widgets */
		descriptionSection.setGroupTitle(NLS.constants()
				.formation_group_description());
		libelle.setLabel(NLS.constants().formation_field_libelle());
		dateDebut.setLabel(NLS.constants().formation_field_dateDebut());
		dateFin.setLabel(NLS.constants().formation_field_dateFin());
		lieu.setLabel(NLS.constants().formation_field_lieu());
		objectifs.setLabel(NLS.constants().formation_field_objectifs());
		effectuee.setLabel(NLS.constants().formation_field_effectuee());
		effectuee.isStrict(true);
		candidatures.setLabel(NLS.constants().formation_field_candidatures());

	}

	/**
	 * Configures the widgets that manage relation fields
	 */
	private void setRelationFields() {

		/* field  candidatures */
		candidaturesDataProvider = new CandidatureFormationDataProvider(
				requestFactory, "formation");
		if (hideButtons) // in popup, relation buttons hidden		
			candidatures = new ImogMultiRelationBox<CandidatureFormationProxy>(
					candidaturesDataProvider, EpicamRenderer.get(), true);
		else {// in wrapper panel, relation buttons enabled
			if (AccessManager.canCreateCandidatureFormation()
					&& AccessManager.canEditCandidatureFormation())
				candidatures = new ImogMultiRelationBox<CandidatureFormationProxy>(
						candidaturesDataProvider, EpicamRenderer.get(), null);
			else
				candidatures = new ImogMultiRelationBox<CandidatureFormationProxy>(
						false, candidaturesDataProvider, EpicamRenderer.get(),
						null);
		}
		candidatures.setPopUpTitle(NLS.constants()
				.candidatureFormation_select_title());
		candidatures.setFilterPanel(new CandidatureFormationFilterPanel());

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

		/* Description section widgets */
		libelle.setEdited(isEdited);
		dateDebut.setEdited(isEdited);
		dateFin.setEdited(isEdited);
		lieu.setEdited(isEdited);
		objectifs.setEdited(isEdited);
		effectuee.setEdited(isEdited);
		candidatures.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canReadFormationDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Description section widgets visibility */
		if (!AccessManager.canEditFormationDescription())
			descriptionSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(FormationRequest ctx) {
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

	/** Widget update for field candidatures */
	private void clearCandidaturesWidget() {
		candidatures.emptyList();
	}

	/**
	 * Configures the handlers of the widgets that manage relation fields
	 */
	private void setRelationHandlers() {

		/* 'Information' button for field Candidatures */
		candidatures.setViewClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (!candidatures.isEmpty()
						&& candidatures.getSelectedEntities().size() > 0) {

					CandidatureFormationProxy value = candidatures
							.getSelectedEntities().get(0);
					RelationPopupPanel relationPopup = new RelationPopupPanel();
					CandidatureFormationFormPanel form = new CandidatureFormationFormPanel(
							requestFactory, value.getId(), relationPopup,
							"candidatures");
					relationPopup.addWidget(form);
					relationPopup.show();
				}
			}
		});

		/* 'Add' button for field Candidatures */
		candidatures.setAddClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				RelationPopupPanel relationPopup = new RelationPopupPanel();
				CandidatureFormationFormPanel form = new CandidatureFormationFormPanel(
						requestFactory, null, relationPopup, "candidatures");
				form.setFormation(editedValue, true);
				/* common fields */

				relationPopup.addWidget(form);
				relationPopup.show();
			}
		});

		/* SaveEvent handler when a CandidatureFormation is created or updated from the relation field Candidatures */
		registrations.add(requestFactory.getEventBus().addHandler(
				SaveCandidatureFormationEvent.TYPE,
				new SaveCandidatureFormationEvent.Handler() {
					@Override
					public void saveCandidatureFormation(
							CandidatureFormationProxy value) {
						if (!candidatures.isPresent(value))
							candidatures.addValue(value);
					}
					public void saveCandidatureFormation(
							CandidatureFormationProxy value, String initField) {
						if (initField.equals("candidatures")) {
							if (candidatures.isPresent(value))
								candidatures.replaceValue(value);
							else
								candidatures.addValue(value, true);
						}
					}
				}));

	}

	/**
	 * Gets the FormationProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public FormationProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the FormationProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(FormationProxy editedValue) {
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

		// dateDebut is a required field
		if (dateDebut.getValueWithoutParseException() == null
				&& dateDebut.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateDebut");
		// dateFin is a required field
		if (dateFin.getValueWithoutParseException() == null
				&& dateFin.isValid())
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"dateFin");
		// objectifs is a required field
		if (objectifs.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"objectifs");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Description field group */
		libelle.setLabelWidth(width);
		dateDebut.setLabelWidth(width);
		dateFin.setLabelWidth(width);
		lieu.setLabelWidth(width);
		objectifs.setLabelWidth(width);
		effectuee.setLabelWidth(width);
		candidatures.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Description field group */
		libelle.setBoxWidth(width);
		lieu.setBoxWidth(width);
		objectifs.setBoxWidth(width);
		candidatures.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<FormationProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> dateDebutFieldErrors = new ArrayList<EditorError>();
			List<EditorError> dateFinFieldErrors = new ArrayList<EditorError>();
			List<EditorError> objectifsFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("dateDebut"))
						dateDebutFieldErrors.add(error);
					if (field.equals("dateFin"))
						dateFinFieldErrors.add(error);
					if (field.equals("objectifs"))
						objectifsFieldErrors.add(error);

				}
			}
			if (dateDebutFieldErrors.size() > 0)
				dateDebut.showErrors(dateDebutFieldErrors);
			if (dateFinFieldErrors.size() > 0)
				dateFin.showErrors(dateFinFieldErrors);
			if (objectifsFieldErrors.size() > 0)
				objectifs.showErrors(objectifsFieldErrors);
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
