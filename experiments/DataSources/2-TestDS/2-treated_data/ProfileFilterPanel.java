package org.imogene.admin.client.ui.filter ; public class ProfileFilterPanel extends ImogFilterPanel { private TextBox nameBox ;  private ImogFilterBox nameFilterBox ;  public ProfileFilterPanel() { super() ;  setFieldReadAccess() ;  }  @Override public void resetFilterWidgets() { nameBox.setValue(null) ;  }  @Override public void createFilterWidgets() { nameBox = new TextBox() ;  nameFilterBox = new ImogFilterBox(nameBox) ;  nameFilterBox.setFilterLabel(AdminNLS.constants().profile_field_name()) ;  addTableFilter(nameFilterBox) ;  }  @Override public List<FilterCriteria> getFilterCriteria() { String locale = AdminNLS.constants().locale() ;  List<FilterCriteria> criteria = new ArrayList<FilterCriteria>() ;  FilterCriteria nameCrit = new FilterCriteria() ;  nameCrit.setField("name") ;  nameCrit.setFieldDisplayName(AdminNLS.constants().profile_field_name()) ;  nameCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS) ;  nameCrit.setValue(nameBox.getValue()) ;  nameCrit.setValueDisplayName(nameBox.getValue()) ;  criteria.add(nameCrit) ;  return criteria ;  }  * Configures the visibility of the fields in view mode depending on the user privileges public void setFieldReadAccess() { if (!ProfileUtil.isAdmin()) nameFilterBox.setVisible(false) ;  } }