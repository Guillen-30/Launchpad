import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioPermission;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class launchpad {
    private
        File Path1 = null;
        File Path2 = null;
        File Path3 = null;
        File Path4 = null;
        File Path5 = null;
        File Path6 = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new launchpad();
        });
    }

    public launchpad() {
        // Crear un nuevo frame (JFrame)
        JFrame frame = new JFrame("Ventana con Botones");
        frame.setSize(400, 400); // Establecer el tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana

        // Crear un panel principal con GridLayout 2x3 para los pares de botones y archivos
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 3));

        // Crear 6 pares de botones cuadrados y archivos
        for (int i = 1; i <= 6; i++) {
            JPanel panel = new JPanel(new BorderLayout());

            JButton boton = crearBotonCuadrado("Botón " + i, i);
            boton.setPreferredSize(new Dimension(100, 100));
            

            JButton fileButton = crearBotonFile("Archivo " + i,i);
            fileButton.setPreferredSize(new Dimension(60, 40)); // Ajustar el tamaño de los archivos

            panel.add(boton, BorderLayout.NORTH);
            panel.add(fileButton, BorderLayout.CENTER);

            panelPrincipal.add(panel);
        }

        // Agregar el panel principal al frame
        frame.add(panelPrincipal);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    // Función para crear un botón cuadrado con ActionListener
    private JButton crearBotonCuadrado(String texto, int numero) {
        JButton boton = new JButton(texto);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la función presionar_boton_n con el número de botón
                presionar_boton_n(numero);
            }
        });
        return boton;
    }

    private JButton crearBotonFile(String texto, int numero) {
        JButton fileButton = new JButton(texto);
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la función presionar_boton_n con el número de botón
                presionar_boton_file_n(numero);
            }
        });
        return fileButton;
    }
    
    // Debes definir esta función según tus necesidades
    public void presionar_boton_n(int numero) {
        File filePath = null;
        // Aquí implementa la lógica que deseas cuando se presiona un botón
        switch (numero) {
            case 1:
                filePath=Path1;
                break;
            case 2:
                filePath=Path2;
                break;
            case 3:
                filePath=Path3;
                break;
            case 4:
                filePath=Path4;
                break;
            case 5:
                filePath=Path5;
                break;
            case 6:
                filePath=Path6;
                break;
            default:
                System.out.println("Número fuera de rango.");
                break;
        }
        System.out.println("Botón " + numero + " presionado\nFile: "+ filePath);
        try {
            AudioInputStream aui = AudioSystem.getAudioInputStream(filePath);

            try {
                
                Clip clip = AudioSystem.getClip(null);
                clip.open(aui);
                clip.start();

            } catch (Exception e) {
                System.out.println("Clip "+e);
            }
        } catch (Exception e) {
            System.out.println("Aui "+e);
        }
  
    }

    public void presionar_boton_file_n(int numero){
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            switch (numero) {
                case 1:
                    Path1 = file;
                    break;
                case 2:
                    Path2 = file;
                    break;
                case 3:
                    Path3 = file;
                    break;
                case 4:
                    Path4 = file;
                    break;
                case 5:
                    Path5 = file;
                    break;
                case 6:
                    Path6 = file;
                    break;
                default:
                    System.out.println("Error inesperado");
                    break;
            }        
        }
        System.out.println("Seleccion archivo "+numero);
    }

}

    
