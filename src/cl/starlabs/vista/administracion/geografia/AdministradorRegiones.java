/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.administracion.geografia;

import cl.starlabs.controladores.PaisJpaController;
import cl.starlabs.controladores.RegionJpaController;
import java.util.List;
import cl.starlabs.modelo.Pais;
import cl.starlabs.modelo.Region;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cetecom
 */
public class AdministradorRegiones extends javax.swing.JFrame {

    EntityManagerFactory emf = null;
    Pais p = null;
    Region r = null;
    
    public AdministradorRegiones() {
        initComponents();
        
        //centrar pantalla
        this.setLocationRelativeTo(null);
        
        //deshabilitando campos
        this.panelInfoRegion.setEnabled(false);
        lblPais.setEnabled(false);
        lblPaisNombre.setEnabled(false);
        btnAccion.setEnabled(false);
        txtNombreRegion.setEditable(false);
        btnCancelar.setEnabled(false);
        
        //seteando persistencia
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        
        //seteando valores por defecto
        for(Pais p : new PaisJpaController(emf).findPaisEntities()) {
            slcPais.addItem(p.getIdPais()+": "+p.getNombre());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        slcPais = new javax.swing.JComboBox<String>();
        btnSeleccionaPais = new javax.swing.JButton();
        panelInfoRegion = new javax.swing.JPanel();
        lblPais = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombreRegion = new javax.swing.JTextField();
        btnAccion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblPaisNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Administrar Regiones");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Regiones en el sistema"));

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaResultados);
        if (tablaResultados.getColumnModel().getColumnCount() > 0) {
            tablaResultados.getColumnModel().getColumn(0).setResizable(false);
            tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(15);
            tablaResultados.getColumnModel().getColumn(1).setResizable(false);
        }

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

        slcPais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione País..." }));

        btnSeleccionaPais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        btnSeleccionaPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionaPaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(slcPais, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionaPais, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slcPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionaPais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelInfoRegion.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de Región"));

        lblPais.setText("País");

        lblNombre.setText("Nombre");

        txtNombreRegion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRegionKeyTyped(evt);
            }
        });

        btnAccion.setText("Registrar");
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

        lblPaisNombre.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblPaisNombre.setText("Seleccione País");

        javax.swing.GroupLayout panelInfoRegionLayout = new javax.swing.GroupLayout(panelInfoRegion);
        panelInfoRegion.setLayout(panelInfoRegionLayout);
        panelInfoRegionLayout.setHorizontalGroup(
            panelInfoRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoRegionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreRegion)
                    .addGroup(panelInfoRegionLayout.createSequentialGroup()
                        .addGroup(panelInfoRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoRegionLayout.createSequentialGroup()
                                .addComponent(lblPais)
                                .addGap(18, 18, 18)
                                .addComponent(lblPaisNombre))
                            .addComponent(lblNombre)
                            .addGroup(panelInfoRegionLayout.createSequentialGroup()
                                .addComponent(btnAccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInfoRegionLayout.setVerticalGroup(
            panelInfoRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoRegionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais)
                    .addComponent(lblPaisNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(panelInfoRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccion)
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
                .addComponent(panelInfoRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelInfoRegion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionaPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionaPaisActionPerformed
        if(slcPais.getSelectedItem().toString().compareToIgnoreCase("Seleccione país...") == 0)
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un país");
            slcPais.requestFocus();
            p = null;
        }else{
            rellenarTabla(slcPais.getSelectedItem().toString().split(":")[0]);
        }
    }//GEN-LAST:event_btnSeleccionaPaisActionPerformed

    private void tablaResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaResultadosMouseClicked
        if(tablaResultados.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
            //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
            r = new RegionJpaController(emf).findRegion(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0))));
            //si el pais no fue encontrado
            if(r == null) {
                //se informa que el pais no fue encontrado
                JOptionPane.showMessageDialog(null, "Error: La región no pudo ser encontrado por el sistema");
            }else{
                //si el pais es encontrado, se definen valores en campos por defecto
                txtNombreRegion.setText(r.getNombre());
                this.panelInfoRegion.setEnabled(true);
                lblPais.setEnabled(true);
                lblPaisNombre.setEnabled(true);
                lblPaisNombre.setText(p.getNombre());
                btnAccion.setEnabled(true);
                btnAccion.setText("Actualizar");
                txtNombreRegion.setEditable(true);
                txtNombreRegion.requestFocus();
                btnCancelar.setEnabled(true);
                tablaResultados.setEnabled(false);
                lblNombre.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tablaResultadosMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //seteando valores por defecto
                txtNombreRegion.setText("");
                this.panelInfoRegion.setEnabled(false);
                lblPais.setEnabled(false);
                lblPaisNombre.setEnabled(false);
                lblPaisNombre.setText("");
                btnAccion.setEnabled(false);
                btnAccion.setText("Actualizar");
                txtNombreRegion.setEditable(false);
                btnCancelar.setEnabled(false);
                tablaResultados.setEnabled(true);
                lblNombre.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //seteando valores por defecto
        if(slcPais.getSelectedItem().toString().compareToIgnoreCase("Seleccione país...") == 0)
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un país");
            slcPais.requestFocus();
            p = null;
        }else{
            p = new PaisJpaController(emf).findPais(Integer.parseInt(slcPais.getSelectedItem().toString().split(":")[0]));
            this.panelInfoRegion.setEnabled(true);
            lblNombre.setEnabled(true);
            lblPais.setEnabled(true);
            lblPaisNombre.setText(p.getNombre());
            lblPaisNombre.setEnabled(true);
            btnAccion.setEnabled(true);
            btnAccion.setText("Registrar");
            txtNombreRegion.setEditable(true);
            txtNombreRegion.setText("");
            txtNombreRegion.requestFocus();
            btnCancelar.setEnabled(true);
            tablaResultados.setEnabled(false);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //comprobando si hay elementos seleccionados en la tabla
        if(tablaResultados.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
            //se consulta si se desea eliminar el valor seleccionado de la tabla
            int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la región "+String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 1)).toLowerCase()+"?");
            //si la respuesta es positiva
            if(opcion == 0) {
                try {
                    //se envia el id del pais para destrucción
                    new RegionJpaController(emf).destroy(Integer.parseInt(modelo.getValueAt(tablaResultados.getSelectedRow(), 0).toString()));
                    //se informa al usuario
                    JOptionPane.showMessageDialog(null, "Región eliminada");
                    //se rellena la tabla desde 0
                    rellenarTabla(p.getIdPais()+"");
                    //se setean valores por defecto haciendo clic en el boton cancelar
                    btnCancelarActionPerformed(evt);
                } catch (Exception e) {
                    //si ocurre un error, es informado al usuario
                    JOptionPane.showMessageDialog(null, "Error al eliminar la región: "+e.getMessage());
                }
            }else if(opcion == 1) {
                btnCancelarActionPerformed(evt);
            }
        }   
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        //se realiza la accion dependiendo del texto del boton
        if(btnAccion.getText().compareToIgnoreCase("registrar") == 0) {
            //si es registrar, se corrobora que el campo de texto de nombre de pais no este vacio
            if(!txtNombreRegion.getText().isEmpty() && p != null) {
                try {
                    //se crea un pais creando un objeto de tipo pais con los valores predeterminados y enviandolo al controlador
                    new RegionJpaController(emf).create(new Region(new RegionJpaController(emf).ultimo(), txtNombreRegion.getText(), p));
                    //si la creacion fue correcta, se informa al usuario
                    JOptionPane.showMessageDialog(null, "Elemento "+txtNombreRegion.getText()+" ha sido registrado con éxito");
                    //se reestablecen los campos a sus valores por defecto
                    btnCancelarActionPerformed(evt);
                    //se rellena la tabla de paises
                    rellenarTabla(p.getIdPais()+"");
                } catch (Exception e) {
                    //si ocurre un error, se informa al usuario
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar la región: "+e.getMessage()); 
                }
            }else{
                //si el campo de texto esta vacío, se informa al usuario y se coloca el cursor para que escriba
                JOptionPane.showMessageDialog(null, "El campo de nombre de región esta vacío o el país no se encuentra seleccionado");
                txtNombreRegion.requestFocus();
            }
        //si desea actualizar un registro...
        }else if(btnAccion.getText().compareToIgnoreCase("actualizar") == 0) {
            try {
                //se verifica que el campo de texto no este vacio
                if(!txtNombreRegion.getText().isEmpty() && p != null) {
                    //se setea el nombre nuevo desde el campo de texto al objeto pais recuperado
                    r.setNombre(txtNombreRegion.getText());
                    //se envia el objeto con el nombre actualizado al controlador
                    new RegionJpaController(emf).edit(r);
                    //si es actualizado, se informa al usuario
                    JOptionPane.showMessageDialog(null, "La región ha sido actualizada");
                    //se reestablecen los campos a sus valores por defecto
                    btnCancelarActionPerformed(evt);
                    //se rellena la tabla de paises nuevamente
                    rellenarTabla(p.getIdPais()+"");
                }else{
                    //si el campo de texto esta vacio, se informa al usuarioi
                    JOptionPane.showMessageDialog(null, "El campo de región esta vacío");
                    //se coloca el cursor para que escriba en el campo
                    txtNombreRegion.requestFocus();
                }
            } catch (Exception e) {
                // si ocurre un error, es informado al usuario
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar actualizar la región: "+e.getMessage()); 
            }
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void txtNombreRegionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRegionKeyTyped
        if(txtNombreRegion.getText().length() >= 250) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreRegionKeyTyped

    public void rellenarTabla(String valor) {
        p = new PaisJpaController(emf).findPais(Integer.parseInt(valor));
        DefaultTableModel modelo = new DefaultTableModel(new Object [][] { }, new String [] {"ID", "Nombre"});
        if(p != null) {
            for(Region re : p.getRegionList()) {
                Object[] fila = new Object[5];
                fila[0] = re.getIdRegion();
                fila[1] = re.getNombre();
                modelo.addRow(fila);
            }
        }
        tablaResultados.setModel(modelo);
    }
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
            java.util.logging.Logger.getLogger(AdministradorRegiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradorRegiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradorRegiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradorRegiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorRegiones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSeleccionaPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPaisNombre;
    private javax.swing.JPanel panelInfoRegion;
    private javax.swing.JComboBox<String> slcPais;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtNombreRegion;
    // End of variables declaration//GEN-END:variables
}
