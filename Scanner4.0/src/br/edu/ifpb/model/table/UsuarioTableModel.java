package br.edu.ifpb.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.model.Usuario;

public class UsuarioTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;  
    private static final int COL_LOGIN = 1;
    private static final int COL_TIPO = 2;
    
 private List<Usuario> valores;
    
    public UsuarioTableModel(List<Usuario> valores) {  
        this.valores = valores;  
  } 
    

	public int getColumnCount() {
		
		return 3;
	}

	
	public int getRowCount() {
	
		return valores.size();
	}
	
	public String getColumnName(int column) {  
        
        if (column == COL_NOME) return "Nome";  
        if (column == COL_LOGIN) return "Login";
        if (column == COL_TIPO) return "Tipo de Acesso";       
        return "";   
    }  

	 public Object getValueAt(int row, int column) {  
          
		 Usuario mod = valores.get(row);  
         if (column == COL_NOME) return mod.getNome();  
         else if (column == COL_LOGIN) return mod.getLogin();
         else if (column == COL_TIPO) return mod.getTipo();
         return "";  
     }
	 
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
         return String.class;  
     }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
         return false;  
     }
	 
	 public Usuario get(int row) {  
         return valores.get(row);  
     }

}
