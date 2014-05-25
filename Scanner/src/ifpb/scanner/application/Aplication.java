package ifpb.scanner.application;

import uk.co.mmscomputing.device.scanner.ScannerIOException;
import exceptions.NotGetDeviceException;
import ifpb.scanner.controller.Sistema;

public class Aplication {
    //Teste
	public static void main(String[] args) {
		Sistema system = new Sistema();
		try {
			system.getDevice();
		} catch (NotGetDeviceException | ScannerIOException e) {
			System.out.println(e.getMessage());
		}

	}

}
