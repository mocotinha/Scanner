package br.edu.ifpb.scanner.nao.usado;

import java.io.*;

@SuppressWarnings("serial")
public class ScannerIOException extends IOException{

  public ScannerIOException(String msg){
    super(msg);
  }

}