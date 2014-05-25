package ifpb.scanner.controller;

import java.awt.image.BufferedImage;

import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata.Type;
import uk.co.mmscomputing.device.scanner.ScannerListener;
import exceptions.NotGetDeviceException;

public class Sistema implements ScannerListener {
	
	public Scanner getDevice() throws NotGetDeviceException, ScannerIOException{
		Scanner scanner = Scanner.getDevice();
		if(scanner != null){
			System.out.println(scanner.getSelectedDeviceName());
			scanner.addListener((ScannerListener) this);
			return scanner;
		}else{
			throw new NotGetDeviceException();
		}
	}

	@Override
    public void update(ScannerIOMetadata.Type type, ScannerIOMetadata siom) {
        if(type.equals(ScannerIOMetadata.ACQUIRED)){            
            //neste ponto o documento foi totalmente digitalizado
            BufferedImage bufferedImage = siom.getImage();
            //bufferedImage esta com a imagem agora.
        }
    }
	
}
