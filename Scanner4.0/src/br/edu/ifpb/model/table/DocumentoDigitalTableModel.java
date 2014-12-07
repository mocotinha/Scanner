package br.edu.ifpb.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.model.DocumentoDigital;


public class DocumentoDigitalTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = 1L;
	private static final int COL_INSTITUICAO = 0;  
    private static final int COL_ALUNO = 1;
    private static final int COL_DOC = 2;
    private static final int COL_USUARIO = 3;
    private static final int COL_QNTIMAGENS = 4;
    //private static final int COL_EDITAR = 5;
    
    private List<DocumentoDigital> valores;
    
    public DocumentoDigitalTableModel(List<DocumentoDigital> valores) {  
        this.valores = new ArrayList<DocumentoDigital>(valores);  
  } 
    
	@Override
	public int getColumnCount() {
		return 5;
		//return 6;
	}

	@Override
	public int getRowCount() {
	
		return valores.size();
	}
	
	public String getColumnName(int column) {  
        
        if (column == COL_INSTITUICAO) return "Instituição";  
        if (column == COL_ALUNO) return "Aluno";
        if (column == COL_DOC) return "Documento";
        if (column == COL_USUARIO) return "Cadastrado por";
        if (column == COL_QNTIMAGENS) return "Quantidade de Imagens";
        //if (column == COL_EDITAR) return "Editar";
        
        return "";   
    }  

	 public Object getValueAt(int row, int column) {  
          
		 DocumentoDigital mod = valores.get(row);  
         if (column == COL_INSTITUICAO) return mod.getDossie().getInstituicao().getNome();  
         else if (column == COL_ALUNO) return mod.getDossie().getAluno().getNome();
         else if (column == COL_DOC) return mod.getDescricao();
         else if (column == COL_USUARIO) return mod.getUserResponsavel().getNome();
         else if (column == COL_QNTIMAGENS) return "qntImagens";//Sistema.getQntImagens(mod.getId());
         //else if (column == COL_EDITAR) return "linkParaEditar";
         return "";  
     }
	 
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
         return String.class;  
     }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
         return false;  
     }
	 
	 public DocumentoDigital get(int row) {  
         return valores.get(row);  
     }
}
