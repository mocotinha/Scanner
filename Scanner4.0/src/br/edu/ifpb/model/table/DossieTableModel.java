package br.edu.ifpb.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.model.Dossie;


public class DossieTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = 1L;
	private static final int COL_INSTITUICAO = 0;  
    private static final int COL_ALUNO = 1;
    private static final int COL_CUR = 2;
    private static final int COL_QNTDOC = 3;

    
    private List<Dossie> valores;
    
    public DossieTableModel(List<Dossie> valores) {  
        this.valores = new ArrayList<Dossie>(valores);  
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
        
        if (column == COL_INSTITUICAO) return "Instituição";  
        if (column == COL_ALUNO) return "Aluno";
        if (column == COL_CUR) return "Curso";
        if (column == COL_QNTDOC) return "Quantidade de Documentos";
   

        
        return "";   
    }  

	 public Object getValueAt(int row, int column) {  
          
		 Dossie mod = valores.get(row);  
         if (column == COL_INSTITUICAO) return mod.getInstituicao().getNome();  
         else if (column == COL_ALUNO) return mod.getAluno().getNome();
         else if (column == COL_CUR) return mod.getCurso().getNome();
         else if (column == COL_QNTDOC) return mod.getDocumentos().size();

         return "";  
     }
	 
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {} 
	 
	 public Class<?> getColumnClass(int columnIndex) {   
         return String.class;  
     }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
         return false;  
     }
	 
	 public Dossie get(int row) {  
         return valores.get(row);  
     }
}
