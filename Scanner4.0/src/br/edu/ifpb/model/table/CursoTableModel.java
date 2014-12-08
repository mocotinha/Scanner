package br.edu.ifpb.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.model.Curso;

public class CursoTableModel extends AbstractTableModel {
	
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;  
    private static final int COL_NIVEL = 1;
    private static final int COL_ANOINICIO = 2;
    private static final int COL_ANOFIM = 3;

 
    
    private List<Curso> valores;
    
    public CursoTableModel(List<Curso> valores) {  
        this.valores = valores;  
  } 
    
	@Override
	public int getColumnCount() {
		return 4;
		
	}

	@Override
	public int getRowCount() {
	
		return valores.size();
	}
	
	public String getColumnName(int column) {  
        
        if (column == COL_NOME) return "Nome";  
        if (column == COL_NIVEL) return "Nivel";
        if (column == COL_ANOINICIO) return "Ano Inicio";
        if (column == COL_ANOFIM) return "Ano Fim";
        
        
        return "";   
    }  

	 public Object getValueAt(int row, int column) {  
          
		 Curso mod = valores.get(row);  
         if (column == COL_NOME) return mod.getNome();  
         else if (column == COL_NIVEL) return mod.getNivel();
         else if (column == COL_ANOINICIO) return mod.getAnoInicio();
         else if (column == COL_ANOFIM) return mod.getAnoFim();
      
         return "";  
     }
	 
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
         return String.class;  
     }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
         return false;  
     }
	 
	 public Curso get(int row) {  
         return valores.get(row);  
     }
}
