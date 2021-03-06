/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.administracion.config;

import cl.starlabs.controladores.TipoProcedimientoJpaController;
import cl.starlabs.modelo.TipoProcedimiento;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor Manuel Araya
 */
public class AdminProcedimientos extends javax.swing.JFrame {

    TipoProcedimiento tp = null;
    EntityManagerFactory emf = null;
    TipoProcedimientoJpaController jpa;
    
    public AdminProcedimientos() {
        initComponents();
        this.emf = Persistence.createEntityManagerFactory("SyncPetPU");
        this.setLocationRelativeTo(null);
        this.jpa = new TipoProcedimientoJpaController(emf);
        rellenarTabla();
        deshabilitarCampos();
    }


    //libreria básica de métodos para realizar tareas de CRUD de SyncPet
    public void rellenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(new Object [][] { }, new String [] { "ID", "Procedimiento" });
        tablaResultados.getColumnModel().getColumn(0).setResizable(false);
        tablaResultados.getColumnModel().getColumn(1).setResizable(false);
        for(TipoProcedimiento te : jpa.findTipoProcedimientoEntities()) {
            Object[] obj = new Object[2];
            obj[0] = te.getIdTipoProcedimiento();
            obj[1] = te.getNombreProcedimiento();
            modelo.addRow(obj);
        }
        tablaResultados.setModel(modelo);
    }
    
    public void vaciarCampos() {
        txtClasificacion.setText("");
        txtNombre.setText("");
        txtTipoProcedimiento.setText("");
    }
    
    public void rellenarCampos(char clasificacion, String nombreProcedimiento, String tipoProcedimiento) {
        vaciarCampos();
        txtClasificacion.setText(clasificacion+"");
        txtNombre.setText(nombreProcedimiento);
        txtTipoProcedimiento.setText(tipoProcedimiento);
    }
        
    public void habilitarCampos() {
        txtClasificacion.setEnabled(true);
        txtNombre.setEnabled(true);
        txtTipoProcedimiento.setEnabled(true);
        btnAccion.setEnabled(true);
        btnCancelar.setEnabled(true);
        tablaResultados.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnRemover.setEnabled(true);
        txtClasificacion.requestFocus();
    }
    
    public void deshabilitarCampos() {
        txtClasificacion.setEnabled(false);
        txtNombre.setEnabled(false);
        txtTipoProcedimiento.setEnabled(false);
        btnAccion.setEnabled(false);
        btnCancelar.setEnabled(false);
        tablaResultados.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnRemover.setEnabled(false);
        tablaResultados.requestFocus();
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "SyncPet", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public Integer preguntar(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "SyncPet", JOptionPane.YES_NO_OPTION);
    }
    
    public boolean esVacio(JTextField obj) {
        if(obj.getText().isEmpty()){
            this.mostrarError("Hay un campo vacio, por favor rellenelo");
            obj.requestFocus();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean esVacio(JTextArea obj) {
        if(obj.getText().isEmpty()){
            this.mostrarError("Hay un campo vacio, por favor rellenelo");
            obj.requestFocus();
            return true;
        }else{
            return false;
        }
    }
    
    public String contenido(JTextField obj) {
        return obj.getText();
    }
    
    public String contenido(JTextArea obj) {
        return obj.getText();
    }
    
    public Integer contenidoInt(JTextField obj) throws Exception {
        try {
            return Integer.parseInt(obj.getText());
        } catch (Exception e) {
            mostrarError("Las letras en un campo numérico no pueden ser convertidos en números");
            obj.selectAll();
            obj.requestFocus();
            return 0;
        }
    }
    
    public void largoMaximo(JTextField obj ,Integer largo, java.awt.event.KeyEvent evt) {
        if(contenido(obj).length() > (largo-1)) {
            evt.consume();
        }
    }
    
    public String contenido(JComboBox obj) {
        return obj.getSelectedItem().toString();
    }
    
    public Integer contenidoInt(JComboBox obj) {
        try {
            return Integer.parseInt(contenido(obj));
        } catch (Exception e) {
            mostrarError("No se pudo transformar el valor seleccionado a integer");
            obj.requestFocus();
            return 0;
        }
    }
    // fin libreria básica
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtClasificacion = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        btnAccion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTipo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTipoProcedimiento = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Administrar Procedimientos");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Propiedades"));

        txtClasificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClasificacionFocusLost(evt);
            }
        });
        txtClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClasificacionKeyTyped(evt);
            }
        });

        lblNombre.setText("Clasificación (Letra de Prioridad)");

        btnAccion.setText("Guardar");
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblTipo.setText("Nombre Procedimiento");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel1.setText("Tipo Procedimiento");

        txtTipoProcedimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoProcedimientoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtNombre)
                    .addComponent(txtTipoProcedimiento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblTipo))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTipoProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccion)
                    .addComponent(btnCancelar))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Procedimientos"));

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Procedimiento"
            }
        ));
        tablaResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaResultados);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/delete.png"))); // NOI18N
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        habilitarCampos();
        btnRemover.setEnabled(false);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
        //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
        if(jpa.findTipoProcedimiento(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0)))) != null) {
            int opcion = preguntar("¿Esta seguro de eliminar el tipo de procedimiento?");
            if(opcion == 0) {
                try {
                    jpa.destroy(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0))));
                    mostrarMensaje("Eliminado");
                    btnCancelarActionPerformed(evt);
                }catch(Exception e) {
                    mostrarError("No se pudo encontrar el tipo de procedimiento en la base de datos porque ya no existe");
                    btnCancelarActionPerformed(evt);
                }
            }
        }else{
            mostrarError("No se pudo encontrar el tipo de procedimiento en la base de datos porque ya no existe");
            btnCancelarActionPerformed(evt);          
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        vaciarCampos();
        deshabilitarCampos();
        rellenarTabla();
        tp = null;
        btnAccion.setText("Guardar");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaResultadosMouseClicked
        DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
        //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
        tp = jpa.findTipoProcedimiento(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0))));
        if(tp == null) {
            this.mostrarError("El tipo de fármaco no pudo ser hallado por el sistema");
        }else{
            habilitarCampos();
            rellenarCampos(tp.getClasificacion(), tp.getNombreProcedimiento(), tp.getTipoProcedimiento());
            btnAccion.setText("Actualizar");
        }
    }//GEN-LAST:event_tablaResultadosMouseClicked

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        if(btnAccion.getText().compareToIgnoreCase("guardar") == 0) {
            // NUEVO
            if(!esVacio(txtClasificacion)) {
                if(!esVacio(txtNombre)) {
                    if(!esVacio(txtTipoProcedimiento)) {
                        if(jpa.existeTipo(txtNombre.getText())) {
                            this.mostrarError("El tipo de procedimiento "+contenido(txtNombre)+" ya se encuentra registrado en la base de datos");
                            txtNombre.selectAll();
                            txtNombre.requestFocus();
                        }else{
                            try {
                                jpa.create(new TipoProcedimiento(jpa.ultimo(), contenido(txtClasificacion).charAt(0), contenido(txtNombre), contenido(txtTipoProcedimiento)));
                                mostrarMensaje("Procedimiento guardado");
                                btnCancelarActionPerformed(evt);
                            }catch(Exception e) {
                                mostrarError("Ocurrió un error al intentar registrar el tipo de procedimiento en el sistema: "+e.getMessage());
                            }
                        }
                    }
                }
            }
        }else{
            //update
            if(!esVacio(txtClasificacion)) {
                if(!esVacio(txtNombre)) {
                    if(!esVacio(txtTipoProcedimiento)) {
                        if(jpa.existeTipo(txtNombre.getText())) {
                            try {
                                if(jpa.buscarPorNombre(contenido(txtNombre)) == null) {
                                    tp.setClasificacion(contenido(txtClasificacion).charAt(0));
                                    tp.setNombreProcedimiento(contenido(txtNombre));
                                    tp.setTipoProcedimiento(contenido(txtTipoProcedimiento));
                                    jpa.edit(tp);
                                    mostrarMensaje("Actualizado");
                                    tp = null;
                                    btnCancelarActionPerformed(evt);
                                }else{
                                    if(jpa.buscarPorNombre(contenido(txtNombre)).getIdTipoProcedimiento()== tp.getIdTipoProcedimiento()) {
                                        tp.setClasificacion(contenido(txtClasificacion).charAt(0));
                                        tp.setNombreProcedimiento(contenido(txtNombre));
                                        tp.setTipoProcedimiento(contenido(txtTipoProcedimiento));
                                        jpa.edit(tp);
                                        mostrarMensaje("Actualizado");
                                        tp = null;
                                        btnCancelarActionPerformed(evt);
                                    }else{
                                        mostrarError("El tipo de procedimiento ya se encuentra registrado en la base de datos");
                                        txtNombre.selectAll();
                                        txtNombre.requestFocus();
                                    }
                                }
                            }catch(Exception e) {
                                mostrarError("Ha ocurrido un error al intentar actualizar el tipo de procedimiento: "+e.getMessage());
                            }
                        }else{
                            try {
                                tp.setClasificacion(contenido(txtClasificacion).charAt(0));
                                tp.setNombreProcedimiento(contenido(txtNombre));
                                tp.setTipoProcedimiento(contenido(txtTipoProcedimiento));
                                jpa.edit(tp);
                                mostrarMensaje("Actualizado");
                                tp = null;
                                btnCancelarActionPerformed(evt);
                            }catch(Exception e) {
                                mostrarError("Ha ocurrido un error al intentar actualizar el tipo de procedimiento: "+e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void txtClasificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClasificacionKeyTyped
        largoMaximo(txtClasificacion, 1, evt);
    }//GEN-LAST:event_txtClasificacionKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        largoMaximo(txtNombre, 120, evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtTipoProcedimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoProcedimientoKeyTyped
        largoMaximo(txtTipoProcedimiento, 75, evt);
    }//GEN-LAST:event_txtTipoProcedimientoKeyTyped

    private void txtClasificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClasificacionFocusLost
        txtClasificacion.setText(contenido(txtClasificacion).toUpperCase());
    }//GEN-LAST:event_txtClasificacionFocusLost


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
            java.util.logging.Logger.getLogger(AdminProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminProcedimientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtClasificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTipoProcedimiento;
    // End of variables declaration//GEN-END:variables
}
