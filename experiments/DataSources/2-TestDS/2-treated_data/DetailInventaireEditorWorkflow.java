package org.imogene.epicam.client.ui.workflow ; public class DetailInventaireEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<DetailInventaireProxy, DetailInventaireEditor> { }  private EpicamRequestFactory requestFactory ;  private DetailInventaireRequest request ;  public DetailInventaireProxy current ;  private Driver editorDriver ;  private DetailInventaireEditor editor ;  private String initField ;  private boolean showGlassPanel = false ;  * Workflow constructor for the creation of a DetailInventaire instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public DetailInventaireEditorWorkflow(EpicamRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a DetailInventaire instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public DetailInventaireEditorWorkflow(EpicamRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new DetailInventaireEditor(factory, true) ;  this.initField = initField ;  }  else editor = new DetailInventaireEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(NLS.constants().detailInventaire_create_title()) ;  createDriver() ;  createNewDetailInventaire() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing DetailInventaire instance * @param factory the application request factory * @param entityId the id of the DetailInventaire instance to be visualized and edited  * @param titleContainer the Label that will display the workflow title  public DetailInventaireEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer) { this(factory, entityId, titleContainer, null, null) ;  }  * Workflow constructor for the visualization and edition of an existing DetailInventaire instance * @param factory the application request factory * @param entityId the id of the DetailInventaire instance to be visualized and edited  * @param titleContainer the label  * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public DetailInventaireEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new DetailInventaireEditor(factory, true) ;  this.initField = initField ;  }  else editor = new DetailInventaireEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchDetailInventaire(entityId) ;  this.setContent(editor) ;  showGlassPanel = true ;  }  @Override protected void onAttach() { super.onAttach() ;  if (showGlassPanel) { EpicamEntryPoint.GP.showAndAdapt(this) ;  }  }  * Create a new instance of DetailInventaire private void createNewDetailInventaire() { request = requestFactory.detailInventaireRequest() ;  DetailInventaireProxy newDetailInventaire = request .create(DetailInventaireProxy.class) ;  newDetailInventaire.setId(ImogKeyGenerator.generateKeyId("DET_INV")) ;  current = newDetailInventaire ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  // Field lot depends on the value of field cDT editor.getLotFilteredByCDT(null) ;  editor.setEdited(true) ;  }  * Get an existing instance of DetailInventaire * @param entityId the id of the DetailInventaireProxy to be fetched private void fetchDetailInventaire(String entityId) { DetailInventaireRequest request = requestFactory .detailInventaireRequest() ;  Request<DetailInventaireProxy> fetchRequest = request .findById(entityId) ;  fetchRequest.with("inventaire") ;  fetchRequest.with("inventaire.CDT") ;  fetchRequest.with("CDT") ;  fetchRequest.with("lot") ;  fetchRequest.with("lot.intrant") ;  fetchRequest.with("lot.medicament") ;  fetchRequest.to(new Receiver<DetailInventaireProxy>() { @Override public void onSuccess(DetailInventaireProxy entity) { viewDetailInventaire(entity) ;  }  } ).fire() ;  }  * Display the current instance of DetailInventaire in editor * @param entity the DetailInventaireProxy to be displayed private void viewDetailInventaire(DetailInventaireProxy entity) { setTitle(NLS.constants().detailInventaire_name() + ": " + EpicamRenderer.get().getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.detailInventaireRequest() ;  current = request.edit(entity) ;  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  if (AccessManager.canEditDetailInventaire()) setModifiable(true) ;  showGlassPanel = false ;  EpicamEntryPoint.GP.hide() ;  }  * Edit the current instance of DetailInventaire in editor @Override protected void edit() { editor.setEdited(true) ;  // Field lot depends on the value of field cDT editor.getLotFilteredByCDT(current.getCDT()) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of DetailInventaire @Override protected void save() { editor.validateFields() ;  editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { //Window.alert("DetailInventaire form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent( new SaveDetailInventaireEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { //Window.alert("DetailInventaire form not validated on server") ;  //TODO manage errors on client side when made available by GWT  if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages EpicamRenderer renderer = EpicamRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error .getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the DetailInventaire") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent( new ViewDetailInventaireEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListDetailInventaireEvent()) ;  }  * Setter to inject a Inventaire value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setInventaire(InventaireProxy value, boolean isLocked) { editor.setInventaire(value, isLocked) ;  }  * Setter to inject a CentreDiagTrait value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setCDT(CentreDiagTraitProxy value, boolean isLocked) { editor.setCDT(value, isLocked) ;  }  * Setter to inject a Lot value * @param value the value to be injected * @param isLocked true if relation field shall be locked (non editable) public void setLot(LotProxy value, boolean isLocked) { editor.setLot(value, isLocked) ;  } }