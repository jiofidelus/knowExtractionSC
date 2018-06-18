package org.imogene.epicam.client.ui.workflow ; public class HorsUsageEditorWorkflow extends EditorWorkflowComposite { interface Driver extends RequestFactoryEditorDriver<HorsUsageProxy, HorsUsageEditor> { }  private EpicamRequestFactory requestFactory ;  private HorsUsageRequest request ;  public HorsUsageProxy current ;  private Driver editorDriver ;  private HorsUsageEditor editor ;  private String initField ;  private boolean showGlassPanel = false ;  * Workflow constructor for the creation of a HorsUsage instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title public HorsUsageEditorWorkflow(EpicamRequestFactory factory, Label titleContainer) { this(factory, titleContainer, null, null) ;  }  * Workflow constructor for the creation of a HorsUsage instance * @param factory the application request factory * @param titleContainer the Label that will display the workflow title * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public HorsUsageEditorWorkflow(EpicamRequestFactory factory, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new HorsUsageEditor(factory, true) ;  this.initField = initField ;  }  else editor = new HorsUsageEditor(factory) ;  isNew = true ;  setEditable(true) ;  setTitle(NLS.constants().horsUsage_create_title()) ;  createDriver() ;  createNewHorsUsage() ;  this.setContent(editor) ;  }  * Workflow constructor for the visualization and edition of an existing HorsUsage instance * @param factory the application request factory * @param entityId the id of the HorsUsage instance to be visualized and edited  * @param titleContainer the Label that will display the workflow title  public HorsUsageEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer) { this(factory, entityId, titleContainer, null, null) ;  }  * Workflow constructor for the visualization and edition of an existing HorsUsage instance * @param factory the application request factory * @param entityId the id of the HorsUsage instance to be visualized and edited  * @param titleContainer the label  * @param parent the parent RelationPopupPanel when the workflow is opened from a relation field * @param initField the name of the field that initiated the opening of the workflow when the workflow is opened from a relation field public HorsUsageEditorWorkflow(EpicamRequestFactory factory, String entityId, Label titleContainer, RelationPopupPanel parent, String initField) { super(factory.getEventBus(), titleContainer, parent) ;  requestFactory = factory ;  if (parent != null) { editor = new HorsUsageEditor(factory, true) ;  this.initField = initField ;  }  else editor = new HorsUsageEditor(factory) ;  setModifiable(false) ;  isNew = false ;  setEditable(false) ;  createDriver() ;  fetchHorsUsage(entityId) ;  this.setContent(editor) ;  showGlassPanel = true ;  }  @Override protected void onAttach() { super.onAttach() ;  if (showGlassPanel) { EpicamEntryPoint.GP.showAndAdapt(this) ;  }  }  * Create a new instance of HorsUsage private void createNewHorsUsage() { request = requestFactory.horsUsageRequest() ;  HorsUsageProxy newHorsUsage = request.create(HorsUsageProxy.class) ;  newHorsUsage.setId(ImogKeyGenerator.generateKeyId("HO_US")) ;  //create an instance of SortieLot in editor  SortieLotProxy newLot = request.create(SortieLotProxy.class) ;  newLot.setId(ImogKeyGenerator.generateKeyId("SORT")) ;  newHorsUsage.setLot(newLot) ;  current = newHorsUsage ;  editorDriver.edit(current, request) ;  editor.setRequestContextForListEditors(request) ;  editor.computeVisibility(null, true) ;  editor.setEdited(true) ;  }  * Get an existing instance of HorsUsage * @param entityId the id of the HorsUsageProxy to be fetched private void fetchHorsUsage(String entityId) { HorsUsageRequest request = requestFactory.horsUsageRequest() ;  Request<HorsUsageProxy> fetchRequest = request.findById(entityId) ;  fetchRequest.with("lot") ;  fetchRequest.with("lot.CDT") ;  fetchRequest.with("lot.lot") ;  fetchRequest.with("lot.lot.intrant") ;  fetchRequest.with("lot.lot.medicament") ;  fetchRequest.to(new Receiver<HorsUsageProxy>() { @Override public void onSuccess(HorsUsageProxy entity) { viewHorsUsage(entity) ;  }  } ).fire() ;  }  * Display the current instance of HorsUsage in editor * @param entity the HorsUsageProxy to be displayed private void viewHorsUsage(HorsUsageProxy entity) { setTitle(NLS.constants().horsUsage_name() + ": " + EpicamRenderer.get().getDisplayValue(entity)) ;  setMetaData((ImogBeanProxy) entity) ;  request = requestFactory.horsUsageRequest() ;  current = request.edit(entity) ;  SortieLotProxy lot = current.getLot() ;  if (lot != null) { }  editor.setEditedValue(current) ;  editor.setRequestContextForListEditors(request) ;  editorDriver.edit(current, request) ;  editor.setEdited(false) ;  editor.computeVisibility(null, true) ;  if (AccessManager.canEditHorsUsage()) setModifiable(true) ;  showGlassPanel = false ;  EpicamEntryPoint.GP.hide() ;  }  * Edit the current instance of HorsUsage in editor @Override protected void edit() { editor.setEdited(true) ;  }  * Initialize the editor driver private void createDriver() { if (editorDriver == null) { editorDriver = GWT.create(Driver.class) ;  editorDriver.initialize(requestFactory, editor) ;  }  }  * Persist the current instance of HorsUsage @Override protected void save() { editor.validateFields() ;  editorDriver.flush() ;  // Check for errors on the client side if (editorDriver.hasErrors()) { //Window.alert("HorsUsage form not validated locally") ;  return ;  }  Request<Void> saveRequest = request.save(current, isNew) ;  saveRequest.to(new Receiver<Void>() { @Override public void onSuccess(Void response) { requestFactory.getEventBus().fireEvent( new SaveHorsUsageEvent(current, initField)) ;  closeForm() ;  }  @Override public void onConstraintViolation(Set<ConstraintViolation<?>> errors) { //Window.alert("HorsUsage form not validated on server") ;  //TODO manage errors on client side when made available by GWT  if (errors != null && errors.size() > 0) { // convert ConstraintViolation to get localized messages EpicamRenderer renderer = EpicamRenderer.get() ;  Set<ConstraintViolation<?>> imogErrors = new HashSet<ConstraintViolation<?>>() ;  for (ConstraintViolation<?> error : errors) { ImogConstraintViolation violation = new ImogConstraintViolation() ;  violation.setLeafBean((BaseProxy) error.getLeafBean()) ;  violation.setPropertyPath(error.getPropertyPath()) ;  violation.setRootBean((BaseProxy) error.getRootBean()) ;  violation.setMessage(renderer.getI18nErrorMessage(error .getMessage())) ;  imogErrors.add(violation) ;  }  editorDriver.setConstraintViolations(imogErrors) ;  }  }  @Override public void onFailure(ServerFailure error) { Window.alert("Error updating the HorsUsage") ;  super.onFailure(error) ;  }  } ) ;  request.fire() ;  }  @Override protected void cancel() { if (parent != null) parent.hide() ;  else { if (isNew) requestFactory.getEventBus().fireEvent(closeEvent) ;  else requestFactory.getEventBus().fireEvent( new ViewHorsUsageEvent(current.getId(), closeEvent)) ;  }  }  @Override protected void returnToList() { requestFactory.getEventBus().fireEvent(new ListHorsUsageEvent()) ;  } }