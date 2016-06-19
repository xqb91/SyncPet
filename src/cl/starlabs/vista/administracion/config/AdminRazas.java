/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.administracion.config;

import cl.starlabs.controladores.EspecieJpaController;
import cl.starlabs.controladores.RazaJpaController;
import cl.starlabs.modelo.Especie;
import cl.starlabs.modelo.Raza;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor Manuel Araya
 */
public class AdminRazas extends javax.swing.JFrame {

    EntityManagerFactory emf = null;
    Raza r = null;
    
    public AdminRazas() {
        initComponents();
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        rellenarTabla();
        deshabilitaCampos();
        this.setLocationRelativeTo(null);
    }

    public void deshabilitaCampos() {
        lblNombre.setEnabled(false);
        txtNombre.setEnabled(false);
        btnAccion.setEnabled(false);
        cmbEspecies.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    public void habilitarCampos() {
        lblNombre.setEnabled(true);
        txtNombre.setEnabled(true);
        btnAccion.setEnabled(true);
        cmbEspecies.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    public void rellenarEspecies() {
        for(Especie e : new EspecieJpaController(emf).findEspecieEntities()) {
            cmbEspecies.addItem(e.getIdEspecie()+": "+e.getNombre());
        }
    }
    
    public void rellenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(new Object [][] { }, new String [] { "ID", "Nombre" });
        tablaResultados.getColumnModel().getColumn(0).setMinWidth(25);
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(25);
        tablaResultados.getColumnModel().getColumn(0).setMaxWidth(25);
        for(Raza r : new RazaJpaController(emf).findRazaEntities()) {
            Object[] obj = new Object[2];
            obj[0] = r.getIdRaza();
            obj[1] = r.getNombre();
            modelo.addRow(obj);
        }
        tablaResultados.setModel(modelo);
    }
    
    public void vaciarCampos() {
        txtNombre.setText("");
        cmbEspecies.removeAllItems();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        btnAccion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbEspecies = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Administrar Razas");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Razas"));

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Raza"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addComponent(btnRemover)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Propiedades"));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lblNombre.setText("Nombre de Raza");

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

        jLabel1.setText("Especie");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbEspecies, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(cmbEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccion)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if(txtNombre.getText().length() > 43) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void tablaResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaResultadosMouseClicked
        if(tablaResultados.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
            //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
            r = new RazaJpaController(emf).findRaza(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0))));
            //si el tipo de alergia no fue encontrado
            if(r == null) {
                //se informa que el pais no fue encontrado
                JOptionPane.showMessageDialog(null, "Error: La raza no pudo ser encontrada por el sistema");
            }else{
                //si el pais es encontrado, se definen valores en campos por defecto
                habilitarCampos();
                txtNombre.setText(r.getNombre());
                cmbEspecies.addItem(r.getEspecie().getIdEspecie()+": "+r.getEspecie().getNombre());
                rellenarEspecies();
                btnAccion.setText("Actualizar");
            }
        }
    }//GEN-LAST:event_tablaResultadosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        habilitarCampos();
        vaciarCampos();
        txtNombre.requestFocus();
        rellenarEspecies();
        btnAccion.setText("Guardar");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        if(btnAccion.getText().compareToIgnoreCase("guardar") == 0) {
            //crear
            if(txtNombre.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "El campo de nombre de tipo de alergia se encuentra vacío");
                txtNombre.requestFocus();
            }else{
                try {
                    RazaJpaController tjc = new RazaJpaController(emf);
                    if(!tjc.existeTipoAlergia(txtNombre.getText())) {
                        Especie e = new EspecieJpaController(emf).findEspecie(Integer.parseInt(cmbEspecies.getSelectedItem().toString().split(":")[0]));
                        tjc.create(new Raza(tjc.ultimo(), txtNombre.getText(), e));
                        JOptionPane.showMessageDialog(null, "Raza registrada con éxito");
                        btnCancelarActionPerformed(evt);
                    }else{
                        JOptionPane.showMessageDialog(null, "La raza ya se encuentra registrado");
                        txtNombre.setText("");
                        txtNombre.requestFocus();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al intentar registrar una nueva raza: "+e.getMessage());
                }
            }
        }else{
            //actualizar
            if(txtNombre.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "El campo de nombre de tipo de raza se encuentra vacío");
                txtNombre.requestFocus();
            }else{
                try {
                    RazaJpaController tjc = new RazaJpaController(emf);
                    Especie e = new EspecieJpaController(emf).findEspecie(Integer.parseInt(cmbEspecies.getSelectedItem().toString().split(":")[0]));
                    new RazaJpaController(emf).edit(new Raza(r.getIdRaza(), txtNombre.getText(), e));
                    JOptionPane.showMessageDialog(null, "Raza actualizada con éxito");
                    btnCancelarActionPerformed(evt);
                    r = null;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al intentar actualizar la raza: "+e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        vaciarCampos();
        rellenarTabla();
        deshabilitaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if(tablaResultados.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
            //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
            r = new RazaJpaController(emf).findRaza(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0))));
            //si el tipo de alergia no fue encontrado
            int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la raza seleccionada?");
            if(opcion == 0) {
                //se informa que el pais no fue encontrado
                try {
                    new RazaJpaController(emf).destroy(r.getIdRaza());
                    JOptionPane.showMessageDialog(null, "Eliminada");
                    btnCancelarActionPerformed(evt);
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar la raza");
                }
            }else{
                btnCancelarActionPerformed(evt);
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

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
            java.util.logging.Logger.getLogger(AdminRazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminRazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminRazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminRazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminRazas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox cmbEspecies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
