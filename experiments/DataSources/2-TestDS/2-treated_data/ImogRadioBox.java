package org.imogene.web.client.ui.field ; public class ImogRadioBox extends Composite implements ImogField<String>, LeafValueEditor<String>, HasEditorErrors<String>, ValueChangeHandler<Boolean>{ private static final Binder uiBinder = GWT.create(Binder.class) ;  interface Binder extends UiBinder<Widget, ImogRadioBox> { }  protected boolean notifyChange = false ;  protected EventBus eventBus = null ;  private boolean edited = false ;  private List<RadioButton> choices = new Vector<RadioButton>() ;  @UiField ImogErrorLabel errorLabel ;  @UiField @Ignore ImogFieldAbstract fieldBox ;  @UiField @Ignore VerticalPanel layout ;  public ImogRadioBox() { initWidget(uiBinder.createAndBindUi(this)) ;  }  @Override public void setLabel(String label) { fieldBox.setLabel(label) ;  }   public void setLabel(String label, HorizontalAlignmentConstant alignment) { fieldBox.setLabel(label, alignment) ;  }  @Override public boolean isEdited() { return edited ;  }  public String getValue() {// StringBuffer result = new StringBuffer() ;  String result = null ;  for(RadioButton choice : choices){ if(choice.getValue()){ result = choice.getFormValue() ;  }  }  return result ;  }  public void setValue(String value) { if (value == null) { for (RadioButton choice : choices) choice.setValue(false) ;  }  else { for (RadioButton choice : choices) { if (value.equals(choice.getFormValue())) { choice.setValue(true) ;  choice.setVisible(true) ;  }  //else { //if (!edited && !choice.getValue()) // choice.setVisible(false) ;  //} }  }  }  public void setEdited(boolean enabled) {  for(RadioButton choice: choices){ choice.setEnabled(enabled) ; // if(enabled)// choice.setVisible(true) ;  }   edited = enabled ;  }  public void clear(){ layout.clear() ;  }   * Add a choice item widget * @param choice the widget to add public void addItem(RadioButton choice){ choice.setStyleName("imogene-Radio") ;  choices.add(choice) ;  choice.addValueChangeHandler(this) ;  layout.add(choice) ;  }   * Defines that the field shall notify value changes * @param eventBus the event bus that will be used to fire the value change events public void notifyChanges(final EventBus eventBus) {  if(eventBus!=null) { this.eventBus = eventBus ;  notifyChange = true ;  }  }  @Override public void showErrors(List<EditorError> errors) { errorLabel.showErrors(errors) ;  }   public void setLabelWidth(String width) { fieldBox.setLabelWidth(width) ;  }   * Sets the widget's width public void setBoxWidth(int width) { layout.getElement().getStyle().setProperty("width", width + "px") ;  }  @Override public void onValueChange(ValueChangeEvent<Boolean> event) { if(notifyChange) { eventBus.fireEvent(new FieldValueChangeEvent(ImogRadioBox.this)) ;  }  } }