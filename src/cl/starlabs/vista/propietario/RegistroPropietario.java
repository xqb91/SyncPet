/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.propietario;

import cl.starlabs.controladores.ComunaJpaController;
import cl.starlabs.controladores.PropietarioJpaController;
import cl.starlabs.modelo.Comuna;
import cl.starlabs.modelo.Propietario;
import java.awt.Color;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Janno
 */
public class RegistroPropietario extends javax.swing.JFrame {
    
    EntityManagerFactory emf = null;
    
    public RegistroPropietario() {
        initComponents();
        //iniciando entitymanagerfactory
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        //centrando ventana
        this.setLocationRelativeTo(null);
        //rellenando campos necesarios
        
        for(Comuna c : new ComunaJpaController(emf).listar()) {
            cmbCiudad.addItem(c.getNombre());
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

        jPanel1 = new javax.swing.JPanel();
        lblRunPropietario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTelefonoFijo = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRun = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApaterno = new javax.swing.JTextField();
        txtAmaterno = new javax.swing.JTextField();
        cmbEstadoCivil = new javax.swing.JComboBox<String>();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cmbCiudad = new javax.swing.JComboBox<String>();
        jPanel2 = new javax.swing.JPanel();
        lblImagenUsuario = new javax.swing.JLabel();
        btnCargarFoto = new javax.swing.JButton();
        btnTomarFoto = new javax.swing.JButton();
        btnGuardarFoto = new javax.swing.JButton();
        btnCancelarFoto = new javax.swing.JButton();
        btnGuardarFormulario = new javax.swing.JButton();
        btnCancelarFormulario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Registro de Propietario");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del propietario"));

        lblRunPropietario.setText("Run");

        jLabel2.setText("Nombres");

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Apellido Materno");

        jLabel5.setText("Dirección");

        jLabel6.setText("Ciudad");

        lblEmail.setText("Correo electrónico");

        lblTelefonoFijo.setText("Teléfono fijo");

        lblCelular.setText("Teléfono Celular");

        jLabel10.setText("Estado Civil");

        txtRun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRunFocusLost(evt);
            }
        });
        txtRun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRunKeyTyped(evt);
            }
        });

        txtNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombresFocusLost(evt);
            }
        });

        txtApaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApaternoFocusLost(evt);
            }
        });

        txtAmaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAmaternoFocusLost(evt);
            }
        });

        cmbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "Casado", "Soltero", "Viudo", "Separado", "En Convivencia" }));

        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });

        txtTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelefonoFocusLost(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCelularFocusLost(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        cmbCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(lblRunPropietario))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(lblTelefonoFijo)
                            .addComponent(lblCelular)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCorreo)
                            .addComponent(txtTelefono)
                            .addComponent(txtCelular)
                            .addComponent(txtDireccion)
                            .addComponent(cmbCiudad, 0, 141, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRunPropietario)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefonoFijo)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCelular)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen de propietario"));

        lblImagenUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/usuario_desconocido.jpg"))); // NOI18N

        btnCargarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/image_add.png"))); // NOI18N
        btnCargarFoto.setText("Cargar Foto");

        btnTomarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/camera_add.png"))); // NOI18N
        btnTomarFoto.setText("Tomar Foto");
        btnTomarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTomarFotoActionPerformed(evt);
            }
        });

        btnGuardarFoto.setText("Guardar Foto");
        btnGuardarFoto.setEnabled(false);

        btnCancelarFoto.setText("Cancelar");
        btnCancelarFoto.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagenUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(btnTomarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancelarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagenUsuario)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCargarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTomarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarFoto)
                    .addComponent(btnCancelarFoto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardarFormulario.setText("Guardar");
        btnGuardarFormulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFormularioActionPerformed(evt);
            }
        });

        btnCancelarFormulario.setText("Cancelar");
        btnCancelarFormulario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnCancelarFormularioItemStateChanged(evt);
            }
        });
        btnCancelarFormulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFormularioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardarFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelarFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarFormularioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnCancelarFormularioItemStateChanged
        
    }//GEN-LAST:event_btnCancelarFormularioItemStateChanged

    private void txtRunKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRunKeyTyped
        //comprobacion de caracteres validos para ingreso de rut
        char c = evt.getKeyChar();
        if((!Character.isDigit(c)) && (!((c == 'K') || (c == 'k') || (c == '-') || (c == '.'))))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRunKeyTyped

    private void txtRunFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRunFocusLost
        //formateo del rut del propietario
        if(!txtRun.getText().isEmpty()) {
            this.txtRun.setText(cl.starlabs.herramientas.HerramientasRut.formatear(txtRun.getText()).toUpperCase());
            //validando rut
            if(!cl.starlabs.herramientas.HerramientasRut.validar(txtRun.getText())){
                txtRun.requestFocus();
                lblRunPropietario.setForeground(Color.red);
                txtRun.setForeground(Color.white);
                txtRun.setBackground(Color.red);
            }else{
                lblRunPropietario.setForeground(Color.black);
                txtRun.setForeground(Color.black);
                txtRun.setBackground(Color.white);
            }
        }
    }//GEN-LAST:event_txtRunFocusLost

    private void txtNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusLost
        //formateando nombre
        txtNombres.setText(txtNombres.getText().toUpperCase());
    }//GEN-LAST:event_txtNombresFocusLost

    private void txtApaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApaternoFocusLost
        //formateando apellido
        txtApaterno.setText(txtApaterno.getText().toUpperCase());
    }//GEN-LAST:event_txtApaternoFocusLost

    private void txtAmaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAmaternoFocusLost
        //formateando apellido
        txtAmaterno.setText(txtAmaterno.getText().toUpperCase());
    }//GEN-LAST:event_txtAmaternoFocusLost

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        //validacion de correo electrónico
        if(!txtCorreo.getText().isEmpty()) {
            if(!cl.starlabs.herramientas.HerramientasCorreo.validarEmail(txtCorreo.getText())) {
                lblEmail.setForeground(Color.red);
                txtCorreo.setForeground(Color.white);
                txtCorreo.setBackground(Color.red);
                txtCorreo.requestFocus();
            }else{
                lblEmail.setForeground(Color.black);
                txtCorreo.setForeground(Color.black);
                txtCorreo.setBackground(Color.white);
                txtCorreo.setText(txtCorreo.getText().toUpperCase());
            }
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        //Aceptando valores validos para numero telefonico
        char c = evt.getKeyChar();
        if((!Character.isDigit(c)) && (!(c == '+')) ){
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefonoFocusLost
        //comprobando telefono valido
        if(!txtTelefono.getText().isEmpty()) {
            if(!cl.starlabs.herramientas.HerramientasTelefono.validarTelefono(txtTelefono.getText())) {
                lblTelefonoFijo.setForeground(Color.red);
                txtTelefono.setBackground(Color.red);
                txtTelefono.setForeground(Color.white);
                txtTelefono.requestFocus();
            }else{
                //formateando telefono valido
                txtTelefono.setText(cl.starlabs.herramientas.HerramientasTelefono.formatearTelefono(txtTelefono.getText()));
                lblTelefonoFijo.setForeground(Color.black);
                txtTelefono.setBackground(Color.white);
                txtTelefono.setForeground(Color.black);
            }
        }
    }//GEN-LAST:event_txtTelefonoFocusLost

    private void txtCelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCelularFocusLost
        //comprobando celular valido
        if(!txtCelular.getText().isEmpty()){
            if(!cl.starlabs.herramientas.HerramientasTelefono.validarCelular(txtCelular.getText())) {
                lblCelular.setForeground(Color.red);
                txtCelular.setBackground(Color.red);
                txtCelular.setForeground(Color.white);
                txtCelular.requestFocus();
            }else{
                txtCelular.setText(cl.starlabs.herramientas.HerramientasTelefono.formatearCelular(txtCelular.getText()));
                lblCelular.setForeground(Color.black);
                txtCelular.setBackground(Color.white);
                txtCelular.setForeground(Color.black);
            }
        }
    }//GEN-LAST:event_txtCelularFocusLost

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        //Aceptando valores validos para numero telefonico
        char c = evt.getKeyChar();
        if((!Character.isDigit(c)) && (!(c == '+')) ){
            evt.consume();
        }
    }//GEN-LAST:event_txtCelularKeyTyped

    private void btnCancelarFormularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFormularioActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarFormularioActionPerformed

    private void btnTomarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTomarFotoActionPerformed
        new TomarFoto().setVisible(true);
    }//GEN-LAST:event_btnTomarFotoActionPerformed

    private void btnGuardarFormularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFormularioActionPerformed
        //verificación antes de guardar al propietario
        Propietario prop = new Propietario();
        try {
            if(txtRun.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El rut está vacío");
                txtRun.requestFocus();
            }else{
                //seteando valores de rut
                String rut = txtRun.getText().split("-")[0];
                char dv  = (txtRun.getText().split("-")[1]).charAt(0);
                rut = rut.replace(".", "");
                prop.setRut(Integer.parseInt(rut));
                prop.setDv(dv);
                //---//
                if(txtNombres.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe especificar el nombre del propietario");
                    txtNombres.requestFocus();
                }else{
                    //seteando valores de nombre
                    prop.setNombres(txtNombres.getText());
                    //--//

                    if(txtApaterno.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe especificar el apellido paterno de "+txtNombres.getText());
                        txtApaterno.requestFocus();
                    }else{
                        //seteando valores de apellido paterno
                        prop.setApaterno(txtApaterno.getText());

                        if(txtAmaterno.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe especificar el apellido materno de "+txtNombres.getText()+" "+txtApaterno.getText());
                            txtAmaterno.requestFocus();
                        }else{
                            prop.setAmaterno(txtAmaterno.getText());

                            if(cmbEstadoCivil.getSelectedIndex() == 0) {
                                JOptionPane.showMessageDialog(null, "Debe especificar el estado civil del propietario");
                                cmbEstadoCivil.requestFocus();
                            }else{
                                if(txtCorreo.getText().isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Debe especificar el correo electrónico del propietario");
                                    txtCorreo.requestFocus();
                                }else{
                                    prop.setEmail(txtCorreo.getText());

                                    if(txtTelefono.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Debe especificar el teléfono del propietario");
                                        txtTelefono.requestFocus();
                                    }else{
                                        String telefono = txtTelefono.getText().replace("+562", "");
                                        prop.setTelefono(Integer.parseInt(telefono));

                                        if(txtCelular.getText().isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "Debe especificar el celular del propietario");
                                            txtCelular.requestFocus();
                                        }else{
                                            String celular = txtCelular.getText().replace("+569", "");
                                            prop.setCelular(Integer.parseInt(celular));

                                            if(txtDireccion.getText().isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Debe especificar la dirección del propietario");
                                                txtDireccion.requestFocus();
                                            }else{
                                                prop.setDireccion(txtDireccion.getText());

                                                if(cmbCiudad.getSelectedIndex() == 0) {
                                                    JOptionPane.showMessageDialog(null, "Debe especificar la ciudad de residencia del propietario");
                                                    cmbCiudad.requestFocus();
                                                }else{
                                                    Comuna c = new ComunaJpaController(emf).buscarComunaPorNombre(cmbCiudad.getSelectedItem().toString().trim());
                                                    if(c == null) {
                                                        JOptionPane.showMessageDialog(null, "Error interno: No se pudo hallar la ciudad");
                                                    }else{
                                                        prop.setComuna(c);
                                                        //registro de propietario
                                                        try {
                                                            prop.setIdPropietario(new PropietarioJpaController(emf).ultimoIdentificador());
                                                            new PropietarioJpaController(emf).create(prop);
                                                            JOptionPane.showConfirmDialog(null, "Propietario Registrado con éxito");
                                                            this.dispose();
                                                        } catch (Exception e) {
                                                            JOptionPane.showMessageDialog(null, "Error: El servidor dijo... "+e.getMessage());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error interno... SyncPet ha dicho: "+e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarFormularioActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPropietario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarFormulario;
    private javax.swing.JButton btnCancelarFoto;
    private javax.swing.JButton btnCargarFoto;
    private javax.swing.JButton btnGuardarFormulario;
    private javax.swing.JButton btnGuardarFoto;
    private javax.swing.JButton btnTomarFoto;
    private javax.swing.JComboBox<String> cmbCiudad;
    private javax.swing.JComboBox<String> cmbEstadoCivil;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblImagenUsuario;
    private javax.swing.JLabel lblRunPropietario;
    private javax.swing.JLabel lblTelefonoFijo;
    private javax.swing.JTextField txtAmaterno;
    private javax.swing.JTextField txtApaterno;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtRun;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
