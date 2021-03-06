/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.inicia;

import cl.starlabs.vista.login.IniciarSesion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Victor Manuel Araya
 */
public class Splash extends javax.swing.JPanel {

    ImageIcon imagen; // imagen para mostrar 
    BorderLayout esquema;
    
    public Splash() {
        initComponents();
        //tipo de ventana
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
                javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        lblEstado.setForeground(Color.BLACK);   
        Random rnd = new Random();
        int numero = (int) (rnd.nextDouble() * 3 + 1);
        if(numero == 1) {
            imagen = new ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/Splash_2.png")); // se obtiene la imagen 
        }else if(numero == 2) {
            imagen = new ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/Splash2.png")); // se obtiene la imagen 
        }else{
            imagen = new ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/Splash3.png")); // se obtiene la imagen 
        }
        this.setSize(imagen.getIconWidth(), imagen.getIconHeight()); // se establece el tamaño del panel 
        // mediante la dimencion de la imagen ancho y alto
    }

        @Override
    public void paintComponent(Graphics g){ // metodo paintComponent para dibujar la imagen 
        super.paintComponent(g); // para que el panel se despligue sin errores 
        g.drawImage(imagen.getImage(), 0, 0, imagen.getIconWidth(), imagen.getIconHeight(), this); // se dibuja la imagen 
        this.setOpaque(false); // se establece el panel transparente para que se pueda ver la imagen 
    } 

    public void comprobacionInicial() throws InterruptedException{ 
        
        // motodo para controlar la barra 
        for(int i = 0; i<=100; i++){ 
            // establece el recorrido de la barra de progreso 
            Thread.sleep(40); 
            barCarga.setForeground(Color.RED); // color de la barra de progreso 
            barCarga.setValue(i); // se asigna el valor de la barra 
            if (i == 20) {
                lblEstado.setText("Comprobando conexión con base de datos...");
                String urlConexion = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=syncpet;user=syncpet;password=alfacentauro$$;";
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(urlConexion);
                }catch(Exception e) {
                    String mensaje = "<html><body><div width='200px' align='justify'>"+e.getMessage()+"<br />Contacte a StarLabs para soporte técnico</div></body></html>";
                    JLabel mensajeLabel = new JLabel(mensaje);
                    JOptionPane.showMessageDialog(null, mensajeLabel);
                    System.exit(0);
                }
                lblEstado.setText("Conexión Correcta...");
            }
            if (i == 55) {
                lblEstado.setText("Preparando servicios de SyncPet...");
            }
            
            if(i == 85) {
                lblEstado.setText("Esperando Inicio de Sesión...");
            }
            if(i == 100) {
                IniciarSesion ini = new IniciarSesion();
                ini.setLocationRelativeTo(null);
                ini.setVisible(true);
            }
        } 
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barCarga = new javax.swing.JProgressBar();
        lblEstado = new javax.swing.JLabel();

        lblEstado.setText("Iniciando Syncpet...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barCarga, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblEstado)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 277, Short.MAX_VALUE)
                .addComponent(barCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblEstado))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barCarga;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables
}
