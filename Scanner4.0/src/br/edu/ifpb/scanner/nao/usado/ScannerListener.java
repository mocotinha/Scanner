package br.edu.ifpb.scanner.nao.usado;

public interface ScannerListener{
  public void update(ScannerIOMetadata.Type type, ScannerIOMetadata metadata);
}