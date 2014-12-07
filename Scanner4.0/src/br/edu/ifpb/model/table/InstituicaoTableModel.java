package br.edu.ifpb.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.model.Instituicao;

public class InstituicaoTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = 1L;
	private static final int COL_ID = 0;  
    private static final int COL_NOME = 1;

    
    private List<Instituicao> valores;
    
    public InstituicaoTableModel(List<Instituicao>valores) {
    	 this.valores = valores;  
	}
       
  
    
	@Override
	public int getColumnCount() {
		return 2;
		
	}

	@Override
	public int getRowCount() {
	
		return valores.size();
	}
	
	public String getColumnName(int column) {  
        
        if (column == COL_ID) return "ID";  
        if (column == COL_NOME) return "Instituição";
        return "";   
    }  

	 public Object getValueAt(int row, int column) {  
          
		 Instituicao mod = valores.get(row);  
         if (column == COL_ID) return mod.getId();  
         else if (column == COL_NOME) return mod.getNome();
         return "";  
     }
	 
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
         return String.class;  
     }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
         return false;  
     }
	 
	 public Instituicao get(int row) {  
         return valores.get(row);  
     }
}
