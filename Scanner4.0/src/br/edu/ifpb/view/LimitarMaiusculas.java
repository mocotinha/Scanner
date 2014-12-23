package br.edu.ifpb.view;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


@SuppressWarnings("serial")
public class LimitarMaiusculas extends PlainDocument {
	

	  public LimitarMaiusculas() {
	    
	  }

	  public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
			  super.insertString(offset,str.toUpperCase(), a);

	  }
}
