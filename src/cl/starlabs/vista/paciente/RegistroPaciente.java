/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.paciente;

import javax.swing.UIManager;
import cl.starlabs.modelo.Mascota;
import java.math.BigInteger;
/**
 *
 * @author Victor Manuel Araya
 */
public class RegistroPaciente extends javax.swing.JFrame {

    /**
     * Creates new form RegistroPaciente
     */
    public RegistroPaciente() {
        initComponents();
        //centrando ventana
        this.setLocationRelativeTo(null);
    }
    
    public RegistroPaciente(Mascota m) {
        initComponents();
        //centrando ventana
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatosPaciente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        slcRaza = new javax.swing.JComboBox();
        slcEspecie = new javax.swing.JComboBox();
        slcPelaje = new javax.swing.JComboBox();
        slcCaradcter = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        slcSexo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtChip = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        slcHabitad = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        slcGrupoSanguineo = new javax.swing.JComboBox();
        panelTomaImagen = new javax.swing.JPanel();
        lblImagenPaciente = new javax.swing.JLabel();
        btnCargarImagen = new javax.swing.JButton();
        btnTomarImagen = new javax.swing.JButton();
        btnGuardarImagen = new javax.swing.JButton();
        btnCancelarImagen = new javax.swing.JButton();
        panelInfoPropietario = new javax.swing.JPanel();
        lblTituloInfoPropietario = new javax.swing.JLabel();
        lblInfoPropietario = new javax.swing.JLabel();
        btnBuscarPropietario = new javax.swing.JButton();
        btnCancelarRegistro = new javax.swing.JButton();
        btnGuardarPaciente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Registro de Paciente");
        setResizable(false);

        panelDatosPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Paciente"));

        jLabel1.setText("Nombre");

        jLabel2.setText("Raza");

        jLabel3.setText("Especie");

        jLabel4.setText("Pelaje");

        jLabel5.setText("Caracter");

        jLabel6.setText("Fecha de Nacimiento");

        slcRaza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        slcEspecie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        slcPelaje.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        slcCaradcter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        jLabel8.setText("Sexo");

        slcSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "Hembra", "Macho", "Hermafrodita" }));

        jLabel10.setText("# Chip");

        jLabel11.setText("Habitad");

        slcHabitad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        jLabel15.setText("Grupo Sanguíneo");

        slcGrupoSanguineo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelDatosPacienteLayout = new javax.swing.GroupLayout(panelDatosPaciente);
        panelDatosPaciente.setLayout(panelDatosPacienteLayout);
        panelDatosPacienteLayout.setHorizontalGroup(
            panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosPacienteLayout.createSequentialGroup()
                        .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(61, 61, 61)
                        .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDatosPacienteLayout.createSequentialGroup()
                                .addComponent(slcSexo, 0, 83, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(slcCaradcter, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slcPelaje, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slcEspecie, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slcRaza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelDatosPacienteLayout.createSequentialGroup()
                        .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelDatosPacienteLayout.createSequentialGroup()
                        .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11))
                        .addGap(21, 21, 21)
                        .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slcHabitad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slcGrupoSanguineo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelDatosPacienteLayout.setVerticalGroup(
            panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slcRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(slcEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(slcPelaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(slcCaradcter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(slcSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slcHabitad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(slcGrupoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTomaImagen.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen del Paciente"));

        lblImagenPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/mascota_desconocida.png"))); // NOI18N

        btnCargarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/image_add.png"))); // NOI18N
        btnCargarImagen.setText("Cargar Foto");

        btnTomarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/camera_add.png"))); // NOI18N
        btnTomarImagen.setText("Tomar Foto");
        btnTomarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTomarImagenActionPerformed(evt);
            }
        });

        btnGuardarImagen.setText("Guardar Foto");
        btnGuardarImagen.setEnabled(false);

        btnCancelarImagen.setText("Cancelar");
        btnCancelarImagen.setEnabled(false);

        javax.swing.GroupLayout panelTomaImagenLayout = new javax.swing.GroupLayout(panelTomaImagen);
        panelTomaImagen.setLayout(panelTomaImagenLayout);
        panelTomaImagenLayout.setHorizontalGroup(
            panelTomaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTomaImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTomaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTomaImagenLayout.createSequentialGroup()
                        .addComponent(lblImagenPaciente)
                        .addGap(18, 18, 18)
                        .addGroup(panelTomaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTomarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelTomaImagenLayout.createSequentialGroup()
                        .addComponent(btnGuardarImagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTomaImagenLayout.setVerticalGroup(
            panelTomaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTomaImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTomaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagenPaciente)
                    .addGroup(panelTomaImagenLayout.createSequentialGroup()
                        .addComponent(btnCargarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTomarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTomaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarImagen)
                    .addComponent(btnCancelarImagen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInfoPropietario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Propietario del Paciente")));

        lblTituloInfoPropietario.setText("Información del propietario");

        lblInfoPropietario.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblInfoPropietario.setText("Nombre Rut");

        btnBuscarPropietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        btnBuscarPropietario.setText("Buscar Propietario");
        btnBuscarPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPropietarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoPropietarioLayout = new javax.swing.GroupLayout(panelInfoPropietario);
        panelInfoPropietario.setLayout(panelInfoPropietarioLayout);
        panelInfoPropietarioLayout.setHorizontalGroup(
            panelInfoPropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPropietarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoPropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloInfoPropietario)
                    .addComponent(lblInfoPropietario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPropietarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBuscarPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelInfoPropietarioLayout.setVerticalGroup(
            panelInfoPropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPropietarioLayout.createSequentialGroup()
                .addComponent(lblTituloInfoPropietario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInfoPropietario)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarPropietario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelarRegistro.setText("Cancelar");

        btnGuardarPaciente.setText("Guardar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDatosPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTomaImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInfoPropietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTomaImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelInfoPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelDatosPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarPaciente)
                    .addComponent(btnCancelarRegistro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTomarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTomarImagenActionPerformed
        new TomarFoto().setVisible(true);
        
    }//GEN-LAST:event_btnTomarImagenActionPerformed

    private void btnBuscarPropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPropietarioActionPerformed
        Mascota m = new Mascota();
        m.setNombre(txtNombre.getText());
        m.setFechaNacimiento(txtFechaNacimiento.getDate());
        m.setSexo(slcSexo.getSelectedItem().toString().charAt(0));
        m.setNumeroChip(Integer.parseInt(txtChip.getText()));
        m.setGrupoSanguineo(slcGrupoSanguineo.getSelectedItem().toString());
              
        
    }//GEN-LAST:event_btnBuscarPropietarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPropietario;
    private javax.swing.JButton btnCancelarImagen;
    private javax.swing.JButton btnCancelarRegistro;
    private javax.swing.JButton btnCargarImagen;
    private javax.swing.JButton btnGuardarImagen;
    private javax.swing.JButton btnGuardarPaciente;
    private javax.swing.JButton btnTomarImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblImagenPaciente;
    private javax.swing.JLabel lblInfoPropietario;
    private javax.swing.JLabel lblTituloInfoPropietario;
    private javax.swing.JPanel panelDatosPaciente;
    private javax.swing.JPanel panelInfoPropietario;
    private javax.swing.JPanel panelTomaImagen;
    private javax.swing.JComboBox slcCaradcter;
    private javax.swing.JComboBox slcEspecie;
    private javax.swing.JComboBox slcGrupoSanguineo;
    private javax.swing.JComboBox slcHabitad;
    private javax.swing.JComboBox slcPelaje;
    private javax.swing.JComboBox slcRaza;
    private javax.swing.JComboBox slcSexo;
    private javax.swing.JTextField txtChip;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
