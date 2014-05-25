import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata;
import uk.co.mmscomputing.device.scanner.ScannerListener;


public class ScannerImageTest extends JFrame implements ScannerListener{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -728820948908708721L;
	Scanner scanner;
    ImagePanel imagePanel;
    JButton capturar;
     
    public ScannerImageTest() {                        
        setUpJFrame();
        //busca um scanner conectado à máquina local
        scanner = uk.co.mmscomputing.device.scanner.Scanner.getDevice();
        
        //registra um objeto ScannerListener para capturar os eventos
        scanner.addListener(this);
        setVisible(true);        
    }        
 
    private void setUpJFrame() {
        imagePanel = new ImagePanel();        
        capturar = new JButton("Capturar Imagem");
        capturar.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //inicia a digitalização da imagem
                    scanner.acquire();
                } catch (ScannerIOException ex) {
                    ex.printStackTrace();
                }
            }
        });                    
         
        setSize(410, 510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());                
        getContentPane().add(capturar, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);
        setTitle("Hello Scanner!");
}
 
    //chamado automaticamente pelo listener do scanner
    @Override
    public void update(ScannerIOMetadata.Type type, ScannerIOMetadata siom) {
        if(type.equals(ScannerIOMetadata.ACQUIRED)){            
            //neste ponto o documento foi totalmente digitalizado
            BufferedImage bufferedImage = siom.getImage();
            imagePanel.setScannedImage(bufferedImage);
        }
    }
     
    public static void main(String[] args) {
        //inicia a aplicação
        new ScannerImageTest();
    }
}
 
class ImagePanel extends JPanel{
     
    Image scannedImage;
 
    public void setScannedImage(Image scannedImage) {
        this.scannedImage = scannedImage;
        if(scannedImage != null){
            this.scannedImage = scannedImage.getScaledInstance(400, 500, 0);
        }
        super.repaint();
    }
     
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);  
      if(scannedImage != null){        
        Graphics2D g2d = (Graphics2D) g.create();        
        g2d.drawImage(scannedImage, 0, 0, null);
        g2d.dispose();
      }
    }        
}