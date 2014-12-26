package br.edu.ifpb.controler;

import java.awt.image.BufferedImage;

import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata.Type;
import uk.co.mmscomputing.device.scanner.ScannerListener;

public class SistemaDeDigitalizacao implements ScannerListener {
	
	private BufferedImage imagemCapturada = null;
	private Scanner scan;
	ScannerIOMetadata.Type status = new Type();
	
	public SistemaDeDigitalizacao(){
		
	}
	private void getDevice() throws  ScannerIOException{
		Scanner scanner = null;
		scanner = Scanner.getDevice();
		scanner.addListener(this);
		this.scan = scanner;
		
	}

	@Override
    public void update(ScannerIOMetadata.Type type, ScannerIOMetadata siom) {
        if(type.equals(ScannerIOMetadata.ACQUIRED)){ 
        	
            //neste ponto o documento foi totalmente digitalizado
            this.imagemCapturada = siom.getImage();
            status = type; 
        }
    }
	
	public Scanner getScan() {
		return scan;
	}

	public void capturaImagem() throws ScannerIOException, NotGetDeviceException{
		this.getDevice();
		this.scan.acquire();
		while(!status.equals(ScannerIOMetadata.ACQUIRED)){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}

		
		
	}
	
	public BufferedImage getImagemCapturada() {
		
		return this.imagemCapturada;
	}


	
	

	
}
