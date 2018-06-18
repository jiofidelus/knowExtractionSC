package org.imogene.epicam.client.ui.filter ; public class CentreDiagTraitFilterPanel extends ImogFilterPanel { private TextBox region_nomBox ;  private ImogFilterBox region_nomFilterBox ;  private TextBox districtsante_nomBox ;  private ImogFilterBox districtsante_nomFilterBox ;  private TextBox nomBox ;  private ImogFilterBox nomFilterBox ;  private ImogBooleanAsRadio deletedEntityBox ;  private ImogFilterBox deletedEntityBoxFilterBox ;  public CentreDiagTraitFilterPanel() { super() ;  setFieldReadAccess() ;  }  @Override public void resetFilterWidgets() { region_nomBox.setValue(null) ;  districtsante_nomBox.setValue(null) ;  nomBox.setValue(null) ;  deletedEntityBox.setValue(false) ;  }  @Override public void createFilterWidgets() { region_nomBox = new TextBox() ;  region_nomFilterBox = new ImogFilterBox(region_nomBox) ;  region_nomFilterBox.setFilterLabel(NLS.constants().region_field_nom()) ;  addTableFilter(region_nomFilterBox) ;  districtsante_nomBox = new TextBox() ;  districtsante_nomFilterBox = new ImogFilterBox(districtsante_nomBox) ;  districtsante_nomFilterBox.setFilterLabel(NLS.constants() .districtSante_field_nom()) ;  addTableFilter(districtsante_nomFilterBox) ;  nomBox = new TextBox() ;  nomFilterBox = new ImogFilterBox(nomBox) ;  nomFilterBox .setFilterLabel(NLS.constants().centreDiagTrait_field_nom()) ;  addTableFilter(nomFilterBox) ;  deletedEntityBox = new ImogBooleanAsRadio() ;  deletedEntityBox.isStrict(true) ;  deletedEntityBox.setEnabled(true) ;  deletedEntityBox.setValue(false) ;  deletedEntityBoxFilterBox = new ImogFilterBox(deletedEntityBox) ;  deletedEntityBoxFilterBox.setFilterLabel(BaseNLS.constants() .entity_field_deleted()) ;  addTableFilter(deletedEntityBoxFilterBox) ;  }  @Override public List<FilterCriteria> getFilterCriteria() { String locale = NLS.constants().locale() ;  List<FilterCriteria> criteria = new ArrayList<FilterCriteria>() ;  FilterCriteria region_nomCrit = new FilterCriteria() ;  if (locale.equals("fr")) region_nomCrit.setField("region.nom.francais") ;  if (locale.equals("en")) region_nomCrit.setField("region.nom.english") ;  region_nomCrit.setFieldDisplayName(NLS.constants().region_field_nom()) ;  region_nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS) ;  region_nomCrit.setValue(region_nomBox.getValue()) ;  region_nomCrit.setValueDisplayName(region_nomBox.getValue()) ;  criteria.add(region_nomCrit) ;  FilterCriteria districtsante_nomCrit = new FilterCriteria() ;  if (locale.equals("fr")) districtsante_nomCrit.setField("districtSante.nom.francais") ;  if (locale.equals("en")) districtsante_nomCrit.setField("districtSante.nom.english") ;  districtsante_nomCrit.setFieldDisplayName(NLS.constants() .districtSante_field_nom()) ;  districtsante_nomCrit .setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS) ;  districtsante_nomCrit.setValue(districtsante_nomBox.getValue()) ;  districtsante_nomCrit.setValueDisplayName(districtsante_nomBox .getValue()) ;  criteria.add(districtsante_nomCrit) ;  FilterCriteria nomCrit = new FilterCriteria() ;  nomCrit.setField("nom") ;  nomCrit.setFieldDisplayName(NLS.constants().centreDiagTrait_field_nom()) ;  nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS) ;  nomCrit.setValue(nomBox.getValue()) ;  nomCrit.setValueDisplayName(nomBox.getValue()) ;  criteria.add(nomCrit) ;  FilterCriteria deletedEntityCrit = new FilterCriteria() ;  deletedEntityCrit.setField("deleted") ;  deletedEntityCrit.setFieldDisplayName(BaseNLS.constants() .entity_field_deleted()) ;  if (deletedEntityBox.getValue()) { deletedEntityCrit .setOperation(CriteriaConstants.OPERATOR_ISNOTNULL) ;  deletedEntityCrit.setValue("true") ;  deletedEntityCrit.setValueDisplayName(BaseNLS.constants() .boolean_true()) ;  }  else { deletedEntityCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL) ;  deletedEntityCrit.setValue("false") ;  deletedEntityCrit.setValueDisplayName(BaseNLS.constants() .boolean_false()) ;  }  criteria.add(deletedEntityCrit) ;  return criteria ;  }  * Configures the visibility of the fields  * in view mode depending on the user privileges public void setFieldReadAccess() { if (!AccessManager.canReadRegionDescription()) region_nomFilterBox.setVisible(false) ;  if (!AccessManager.canReadDistrictSanteDescription()) districtsante_nomFilterBox.setVisible(false) ;  if (!AccessManager.canReadCentreDiagTraitDescription()) nomFilterBox.setVisible(false) ;  if (!AccessManager.isAdmin()) deletedEntityBoxFilterBox.setVisible(false) ;  } }