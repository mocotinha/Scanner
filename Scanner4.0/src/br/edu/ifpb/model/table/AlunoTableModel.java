package br.edu.ifpb.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.model.Aluno;

public class AlunoTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = 1L;
	private static final int COL_MAT = 0;  
    private static final int COL_NOME = 1;
    private static final int COL_CPF = 2;
    private static final int COL_RG = 3;
    private static final int COL_MAE = 4;
    private static final int COL_PAI = 5;
    private static final int COL_DTN = 6;

    
    private List<Aluno> valores;
    
    public AlunoTableModel(List<Aluno> valores) {  
        this.valores = valores;  
  } 
    
	@Override
	public int getColumnCount() {
		
		return 7;
	}

	@Override
	public int getRowCount() {
	
		return valores.size();
	}
	
	public String getColumnName(int column) {  
        
        if (column == COL_MAT) return "Matricula";  
        if (column == COL_NOME) return "Nome";
        if (column == COL_CPF) return "CPF";
        if (column == COL_RG) return "RG";
        if (column == COL_MAE) return "Mãe";
        if (column == COL_PAI) return "Pai";
        if (column == COL_DTN) return "Data de Nascimento";
        
        return "";   
    }  

	 public Object getValueAt(int row, int column) {  
          
		 Aluno mod = valores.get(row);  
         if (column == COL_MAT) return mod.getMatricula();  
         else if (column == COL_NOME) return mod.getNome();
         else if (column == COL_CPF) return mod.getCpf();
         else if (column == COL_RG) return mod.getRg();
         else if (column == COL_MAE) return mod.getMae();
         else if (column == COL_PAI) return mod.getPai();
         else if (column == COL_DTN) return mod.getDataNascimento();
         return "";  
     }
	 
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
         return String.class;  
     }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
         return false;  
     }
	 
	 public Aluno get(int row) {  
         return valores.get(row);  
     }
}
