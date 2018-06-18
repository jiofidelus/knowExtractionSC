package org.imogene.web.client.ui.field.widget ; public class ImogDecimalTimePicker extends ImogWidget<Double> implements ChangeHandler, HasValueChangeHandlers<Double> { private static TimePickerUiBinder uiBinder = GWT .create(TimePickerUiBinder.class) ;  interface TimePickerUiBinder extends UiBinder<Widget, ImogDecimalTimePicker> { }  private Double value ;  private boolean edited = true ;  private int minuteStep = 1 ;   HandlerManager handlerManager = new HandlerManager(this) ;   @UiField ListBox hours ;  @UiField ListBox minutes ;  public ImogDecimalTimePicker() { initWidget(uiBinder.createAndBindUi(this)) ;  populateWithHours() ;  populateWithMinutes() ;  addChangeHandler(this) ;  }  private void populateWithHours() { hours.addItem("", "") ;  for (int i = 0 ;  i < 24 ;  i++) { hours.addItem(formatMinutes(i), String.valueOf(i)) ;  }  hours.setSelectedIndex(0) ;  }  private void populateWithMinutes() { for (int i = 0 ;  i < 60 ;  i = i + minuteStep) { minutes.addItem(formatMinutes(i), String.valueOf(i)) ;  }  minutes.setSelectedIndex(0) ;  }  public void setValue(Double value) { this.value = value ;  if (value != null) { int valueHours = (int)value.doubleValue() ;  hours.setSelectedIndex(getIndexForValue(hours, String.valueOf(valueHours))) ;  long valueMinutes = Math.round((value-valueHours)*60) ;  minutes.setSelectedIndex(getIndexForValue(minutes, String.valueOf(valueMinutes))) ;  }  else { hours.setSelectedIndex(0) ;  minutes.setSelectedIndex(0) ;  }  }  public Double getValue() {  if(hours.getSelectedIndex() > 0) { double decimalHour = 0 ;  decimalHour = Float.valueOf(hours.getValue(hours.getSelectedIndex())) ;  if(minutes.getSelectedIndex()>0){ int min = Integer.parseInt(minutes.getValue(minutes.getSelectedIndex())) ;  decimalHour = decimalHour + min/60f ;  }   return decimalHour ;  }  else return null ;  }  public boolean isEdited() { return edited ;  }  public void setEnabled(boolean enabled) { this.edited = enabled ;  hours.setEnabled(enabled) ;  minutes.setEnabled(enabled) ;  if (!enabled) { hours.addStyleDependentName("disabled") ;  minutes.addStyleDependentName("disabled") ;  }  else { hours.removeStyleDependentName("disabled") ;  minutes.removeStyleDependentName("disabled") ;  }  }  private static int getIndexForValue(ListBox box, String value) { if (value != null) { for (int i = 0 ;  i < box.getItemCount() ;  i++) { if (value.equals(box.getValue(i))) return i ;  }  }  return -1 ;  }  private String formatMinutes(int minutes) { NumberFormat nf = NumberFormat.getFormat("00") ;  return nf.format(minutes) ;  }  public void addChangeHandler(ChangeHandler changeHandler) { hours.addChangeHandler(changeHandler) ;  minutes.addChangeHandler(changeHandler) ;  }  * Enables to define the different minutes that will be available in the widget * @param minuteStep the step between 2 minute values (default 1) public void setMinuteStep(int minuteStep) { this.minuteStep = minuteStep ;  }   public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Double> changeHandler){ return handlerManager.addHandler(ValueChangeEvent.getType(), changeHandler) ;   }   public void addMouseOutHandler(MouseOutHandler mouseOutHandler){ hours.addMouseOutHandler(mouseOutHandler) ;  minutes.addMouseOutHandler(mouseOutHandler) ;  }    public void addBlurHandler(BlurHandler blurHandler){ hours.addBlurHandler(blurHandler) ;  minutes.addBlurHandler(blurHandler) ;  }  @Override public void onChange(ChangeEvent event) {  ValueChangeEvent.fire(ImogDecimalTimePicker.this, getValue()) ;  }   }