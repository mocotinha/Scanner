package br.edu.ifpb.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

@SuppressWarnings("serial")
public class AbstractComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String>{

	 private String selectedItem;

	  private ArrayList<String> anArrayList;

	  public  AbstractComboBoxModel(List<String> arrayList) {
	    anArrayList = (ArrayList<String>) arrayList;
	  }

	  public Object getSelectedItem() {
	    return selectedItem;
	  }

	  public void setSelectedItem(Object newValue) {
	    selectedItem = (String) newValue;
	  }

	  public int getSize() {
	    return anArrayList.size();
	  }

	  public String getElementAt(int i) {
	    return anArrayList.get(i);
	  }

	
	
}