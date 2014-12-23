package br.edu.ifpb.view;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


@SuppressWarnings("serial")
public class LimitarCharacter extends PlainDocument {
	  private int limite;

	  public LimitarCharacter(int limite) {
	    this.limite = limite;
	  }

	  public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		  if (getLength() + str.length() <= limite && Character.isDigit(str.charAt(0))){
			  super.insertString(offset, str, a);
		  }
	  }
}
