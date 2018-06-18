package org.imogene.epicam.client.ui.workflow ; public class IntrantEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<IntrantProxy, IntrantEditor> { }  private EpicamRequestFactory requestFactory ;  private IntrantRequest request ;  public IntrantProxy current ;  private Driver editorDriver ;  private IntrantEditor editor ;  private String initField ;  private boolean showGlassPanel = false ;  * Workflow constructor for the creation of a Intrant instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public IntrantEditorWorkflow(EpicamRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a Intrant instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public IntrantEditorWorkflow(EpicamRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new IntrantEditor(factory, true) ;  this.initField = initField ;  }  else editor = new IntrantEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(NLS.constants().intrant_create_title()) ;  createDriver() ;  createNewIntrant() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing Intrant instance * @param factory the application request factory * @param entityId the id of the Intrant instance to be visualized and edited  * @param titleContainer the Label that will display the workflow title  public IntrantEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer) { this(factory, entityId, titleContainer, null, null) ;  }  * Workflow constructor for the visualization and edition of an existing Intrant instance * @param factory the application request factory * @param entityId the id of the Intrant instance to be visualized and edited  * @param titleContainer the label  * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public IntrantEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new IntrantEditor(factory, true) ;  this.initField = initField ;  }  else editor = new IntrantEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchIntrant(entityId) ;  this.setContent(editor) ;  showGlassPanel = true ;  }  @Override protected void onAttach() { super.onAttach() ;  if (showGlassPanel) { EpicamEntryPoint.GP.showAndAdapt(this) ;  }  }  * Create a new instance of Intrant private void createNewIntrant() { request = requestFactory.intrantRequest() ;  IntrantProxy newIntrant = request.create(IntrantProxy.class) ;  newIntrant.setId(ImogKeyGenerator.generateKeyId("INTR")) ;  current = newIntrant ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  editor.setEdited(true) ;  }  * Get an existing instance of Intrant * @param entityId the id of the IntrantProxy to be fetched private void fetchIntrant(String entityId) { IntrantRequest request = requestFactory.intrantRequest() ;  Request<IntrantProxy> fetchRequest = request.findById(entityId) ;  fetchRequest.to(new Receiver<IntrantProxy>() { @Override public void onSuccess(IntrantProxy entity) { viewIntrant(entity) ;  }  } ).fire() ;  }  * Display the current instance of Intrant in editor * @param entity the IntrantProxy to be displayed private void viewIntrant(IntrantProxy entity) { setTitle(NLS.constants().intrant_name() + ": " + EpicamRenderer.get().getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.intrantRequest() ;  current = request.edit(entity) ;  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  if (AccessManager.canEditIntrant()) setModifiable(true) ;  showGlassPanel = false ;  EpicamEntryPoint.GP.hide() ;  }  * Edit the current instance of Intrant in editor @Override protected void edit() { editor.setEdited(true) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of Intrant @Override protected void save() { editor.validateFields() ;  editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { //Window.alert("Intrant form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent( new SaveIntrantEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { //Window.alert("Intrant form not validated on server") ;  //TODO manage errors on client side when made available by GWT  if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages EpicamRenderer renderer = EpicamRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error .getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the Intrant") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent( new ViewIntrantEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListIntrantEvent()) ;  } }