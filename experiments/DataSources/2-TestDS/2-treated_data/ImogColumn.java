package org.imogene.web.client.ui.table ; public abstract class ImogColumn<T,C> extends Column<T,C> { public ImogColumn(Cell<C> cell) { super(cell) ;  }  public abstract String getPropertyName () ;  }