/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.administracion.geografia;

import cl.starlabs.controladores.ProvinciaJpaController;
import cl.starlabs.controladores.RegionJpaController;
import cl.starlabs.controladores.PaisJpaController;
import cl.starlabs.modelo.Provincia;
import cl.starlabs.modelo.Region;
import cl.starlabs.modelo.Pais;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Janno
 */
public class AdministradorProvincias extends javax.swing.JFrame {

    EntityManagerFactory emf = null;
    Provincia pp = null;
    Region r = null;
    Pais p = null;
    
    public AdministradorProvincias() {
        initComponents();
        
        //centrando ventana
        this.setLocationRelativeTo(null);
        
        //definiendo valores para persistencia
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        
        //desahibilitando campos
        this.panelInfoProvincia.setEnabled(false);
        lblRegion.setEnabled(false);
        lblPais.setEnabled(false);
        txtNombreProvincia.setEditable(false);
        btnRegistrar.setEnabled(false);
        btnCancelar.setEnabled(false);
       
        //seteando valores por defecto
        for(Pais p : new PaisJpaController(emf).findPaisEntities()) {
            cmbPais.addItem(p.getIdPais()+": "+p.getNombre());
        }
        for(Region r : new RegionJpaController(emf).findRegionEntities()){
            cmbRegion.addItem(r.getIdRegion()+": "+r.getNombre());
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
        cmbRegion = new javax.swing.JComboBox<>();
        btnSeleccionarRegion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaProvincias = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cmbPais = new javax.swing.JComboBox<>();
        panelInfoProvincia = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblRegion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreProvincia = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Provincias en el Sistema"));

        cmbRegion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Region..." }));

        btnSeleccionarRegion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N

        tablaListaProvincias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        tablaListaProvincias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaListaProvinciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaListaProvincias);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/delete.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        cmbPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Pais..." }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionarRegion)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarRegion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)))
        );

        panelInfoProvincia.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de la Provincia"));

        jLabel1.setText("Region");

        lblRegion.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblRegion.setText("Seleccione Region");

        jLabel3.setText("Nombre");

        btnRegistrar.setText("Registrar");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Pais");

        lblPais.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblPais.setText("Seleccione Pais");

        javax.swing.GroupLayout panelInfoProvinciaLayout = new javax.swing.GroupLayout(panelInfoProvincia);
        panelInfoProvincia.setLayout(panelInfoProvinciaLayout);
        panelInfoProvinciaLayout.setHorizontalGroup(
            panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoProvinciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreProvincia)
                    .addGroup(panelInfoProvinciaLayout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addGroup(panelInfoProvinciaLayout.createSequentialGroup()
                        .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(panelInfoProvinciaLayout.createSequentialGroup()
                                .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPais)
                                    .addComponent(lblRegion))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInfoProvinciaLayout.setVerticalGroup(
            panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoProvinciaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblPais))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblRegion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoProvinciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfoProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInfoProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //seteando valores por defecto
        this.panelInfoProvincia.setEnabled(true);
        lblRegion.setEnabled(true);
        lblPais.setEnabled(true);
        txtNombreProvincia.setEnabled(true);
        txtNombreProvincia.setText("");
        txtNombreProvincia.requestFocus();
        btnRegistrar.setEnabled(true);
        btnCancelar.setEnabled(true);
        tablaListaProvincias.setEnabled(false);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //
        if(tablaListaProvincias.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaListaProvincias.getModel();
            //se consulta si se desea eliminar el valor seleccionado de la tabla
            int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la Provincia "+String.valueOf(modelo.getValueAt(tablaListaProvincias.getSelectedRow(), 1)).toLowerCase()+"?");
            //si la respuesta es positiva
            if(opcion == 0) {
                try {
                    //se envia el id de la provincia para destrucción
                    new ProvinciaJpaController(emf).destroy(Integer.parseInt(modelo.getValueAt(tablaListaProvincias.getSelectedRow(), 0).toString()));
                    //se informa al usuario
                    JOptionPane.showMessageDialog(null, "Provincia eliminado");
                    //se rellena la tabla desde 0
                    rellenarTabla(r.getIdRegion()+"");
                    //se setean valores por defecto haciendo clic en el boton cancelar
                    btnCancelarActionPerformed(evt);
                } catch (Exception e) {
                    //si ocurre un error, es informado al usuario
                    JOptionPane.showMessageDialog(null, "Error al eliminar Provincia: "+e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // 
        this.panelInfoProvincia.setEnabled(false);
        lblRegion.setEnabled(false);
        txtNombreProvincia.setEnabled(false);
        txtNombreProvincia.setText("");
        txtNombreProvincia.requestFocus();
        btnRegistrar.setEnabled(false);
        btnCancelar.setEnabled(false);
        tablaListaProvincias.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaListaProvinciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListaProvinciasMouseClicked
        // 
        if(tablaListaProvincias.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaListaProvincias.getModel();
            //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
            pp = new ProvinciaJpaController(emf).findProvincia(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaListaProvincias.getSelectedRow(), 0))));
            //si el pais no fue encontrado
            if(pp == null) {
                //se informa que el pais no fue encontrado
                JOptionPane.showMessageDialog(null, "Error: La Provincia no pudo ser encontrado por el sistema");
            }else{
                //si el pais es encontrado, se definen valores en campos por defecto
                this.panelInfoProvincia.setEnabled(true);
                lblPais.setEnabled(true);
                lblRegion.setEnabled(true);
                txtNombreProvincia.setEnabled(true);
                txtNombreProvincia.setText("");
                txtNombreProvincia.requestFocus();
                btnRegistrar.setEnabled(true);
                btnCancelar.setEnabled(true);
                tablaListaProvincias.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tablaListaProvinciasMouseClicked
    
    public void rellenarTabla(String valor){
        r = new RegionJpaController(emf).findRegion(Integer.parseInt(valor));
        DefaultTableModel modelo = new DefaultTableModel(new Object[][] { }, new String [] {"ID", "Nombre"});
        if(r != null){
            for(Provincia pp : r.getProvinciaList()){
                Object[] fila = new Object[5];
                fila[0] = pp.getIdProvincia();
                fila[1] = pp.getNombre();
                modelo.addRow(fila);
            }
        }
        tablaListaProvincias.setModel(modelo);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdministradorProvincias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradorProvincias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradorProvincias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradorProvincias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorProvincias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSeleccionarRegion;
    private javax.swing.JComboBox<String> cmbPais;
    private javax.swing.JComboBox<String> cmbRegion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblRegion;
    private javax.swing.JPanel panelInfoProvincia;
    private javax.swing.JTable tablaListaProvincias;
    private javax.swing.JTextField txtNombreProvincia;
    // End of variables declaration//GEN-END:variables
}