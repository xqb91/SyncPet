/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.administracion.geografia;

import cl.starlabs.controladores.PaisJpaController;
import cl.starlabs.modelo.Pais;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cetecom
 */
public class AdministradorPaises extends javax.swing.JFrame {
    
    EntityManagerFactory emf = null;
    Pais p = null;
    
    public AdministradorPaises() {
        initComponents();
        
        //centrando ventana
        this.setLocationRelativeTo(null);
        
        //definiendo valores para persistencia
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        
        //deshabilitando campos
        this.panelInfoPais.setEnabled(false);
        lblNombrePais.setEnabled(false);
        btnAccion.setEnabled(false);
        txtNombrePais.setEditable(false);
        btnCancelar.setEnabled(false);
        
        //rellenando valores de los paises
        rellenarTabla();
        
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
        tablaListaPaises = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelInfoPais = new javax.swing.JPanel();
        lblNombrePais = new javax.swing.JLabel();
        txtNombrePais = new javax.swing.JTextField();
        btnAccion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Administración de Paises");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Paises en Sistema"));

        tablaListaPaises.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        tablaListaPaises.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaListaPaisesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaListaPaises);
        if (tablaListaPaises.getColumnModel().getColumnCount() > 0) {
            tablaListaPaises.getColumnModel().getColumn(0).setMinWidth(35);
            tablaListaPaises.getColumnModel().getColumn(0).setPreferredWidth(35);
            tablaListaPaises.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAgregar.setToolTipText("Agregar un nuevo país al sistema");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/delete.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar País seleccionado");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInfoPais.setBorder(javax.swing.BorderFactory.createTitledBorder("Información del País"));

        lblNombrePais.setText("Nombre País");

        btnAccion.setText("Actualizar");
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

        javax.swing.GroupLayout panelInfoPaisLayout = new javax.swing.GroupLayout(panelInfoPais);
        panelInfoPais.setLayout(panelInfoPaisLayout);
        panelInfoPaisLayout.setHorizontalGroup(
            panelInfoPaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPaisLayout.createSequentialGroup()
                .addComponent(lblNombrePais)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelInfoPaisLayout.createSequentialGroup()
                .addComponent(txtNombrePais)
                .addContainerGap())
            .addGroup(panelInfoPaisLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(btnAccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelInfoPaisLayout.setVerticalGroup(
            panelInfoPaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPaisLayout.createSequentialGroup()
                .addComponent(lblNombrePais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombrePais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoPaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInfoPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInfoPais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //seteando valores por defecto
        this.panelInfoPais.setEnabled(true);
        lblNombrePais.setEnabled(true);
        btnAccion.setEnabled(true);
        btnAccion.setText("Registrar");
        txtNombrePais.setEnabled(true);
        txtNombrePais.setText("");
        txtNombrePais.requestFocus();
        btnCancelar.setEnabled(true);
        tablaListaPaises.setEnabled(false);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //seteando valores por defecto
        this.panelInfoPais.setEnabled(false);
        lblNombrePais.setEnabled(false);
        btnAccion.setEnabled(false);
        btnAccion.setText("Actualizar");
        txtNombrePais.setEnabled(false);
        txtNombrePais.requestFocus();
        btnCancelar.setEnabled(false);
        tablaListaPaises.setEnabled(true);
        txtNombrePais.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //comprobando si hay elementos seleccionados en la tabla
        if(tablaListaPaises.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaListaPaises.getModel();
            //se consulta si se desea eliminar el valor seleccionado de la tabla
            int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el pais "+String.valueOf(modelo.getValueAt(tablaListaPaises.getSelectedRow(), 1)).toLowerCase()+"?");
            //si la respuesta es positiva
            if(opcion == 0) {
                try {
                    //se envia el id del pais para destrucción
                    new PaisJpaController(emf).destroy(Integer.parseInt(modelo.getValueAt(tablaListaPaises.getSelectedRow(), 0).toString()));
                    //se informa al usuario
                    JOptionPane.showMessageDialog(null, "País eliminado");
                    //se rellena la tabla desde 0
                    rellenarTabla();
                    //se setean valores por defecto haciendo clic en el boton cancelar
                    btnCancelarActionPerformed(evt);
                } catch (Exception e) {
                    //si ocurre un error, es informado al usuario
                    JOptionPane.showMessageDialog(null, "Error al eliminar país: "+e.getMessage());
                }
            }
        }   
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaListaPaisesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListaPaisesMouseClicked
        if(tablaListaPaises.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaListaPaises.getModel();
            //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
            p = new PaisJpaController(emf).findPais(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaListaPaises.getSelectedRow(), 0))));
            //si el pais no fue encontrado
            if(p == null) {
                //se informa que el pais no fue encontrado
                JOptionPane.showMessageDialog(null, "Error: El país no pudo ser encontrado por el sistema");
            }else{
                //si el pais es encontrado, se definen valores en campos por defecto
                txtNombrePais.setText(p.getNombre());
                this.panelInfoPais.setEnabled(true);
                lblNombrePais.setEnabled(true);
                btnAccion.setEnabled(true);
                btnAccion.setText("Actualizar");
                txtNombrePais.setEditable(true);
                txtNombrePais.requestFocus();
                btnCancelar.setEnabled(true);
                tablaListaPaises.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tablaListaPaisesMouseClicked

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        //se realiza la accion dependiendo del texto del boton
        if(btnAccion.getText().compareToIgnoreCase("registrar") == 0) {
            //si es registrar, se corrobora que el campo de texto de nombre de pais no este vacio
            if(!txtNombrePais.getText().isEmpty()) {
                try {
                    //se crea un pais creando un objeto de tipo pais con los valores predeterminados y enviandolo al controlador
                    new PaisJpaController(emf).create(new Pais(new PaisJpaController(emf).ultimoPais(), txtNombrePais.getText().toUpperCase()));
                    //si la creacion fue correcta, se informa al usuario
                    JOptionPane.showMessageDialog(null, "Pais "+txtNombrePais.getText()+" ha sido registrado con éxito");
                    //se reestablecen los campos a sus valores por defecto
                    btnCancelarActionPerformed(evt);
                    //se rellena la tabla de paises
                    rellenarTabla();
                } catch (Exception e) {
                    //si ocurre un error, se informa al usuario
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el país: "+e.getMessage()); 
                }
            }else{
                //si el campo de texto esta vacío, se informa al usuario y se coloca el cursor para que escriba
                JOptionPane.showMessageDialog(null, "El campo de nombre de país esta vacío");
                txtNombrePais.requestFocus();
            }
        //si desea actualizar un registro...
        }else if(btnAccion.getText().compareToIgnoreCase("actualizar") == 0) {
            try {
                //se verifica que el campo de texto no este vacio
                if(!txtNombrePais.getText().isEmpty()) {
                    //se setea el nombre nuevo desde el campo de texto al objeto pais recuperado
                    p.setNombre(txtNombrePais.getText().toUpperCase());
                    //se envia el objeto con el nombre actualizado al controlador
                    new PaisJpaController(emf).edit(p);
                    //si es actualizado, se informa al usuario
                    JOptionPane.showMessageDialog(null, "El país ha sido actualizado");
                    //se reestablecen los campos a sus valores por defecto
                    btnCancelarActionPerformed(evt);
                    //se rellena la tabla de paises nuevamente
                    rellenarTabla();
                }else{
                    //si el campo de texto esta vacio, se informa al usuarioi
                    JOptionPane.showMessageDialog(null, "El campo de nombre de país esta vacío");
                    //se coloca el cursor para que escriba en el campo
                    txtNombrePais.requestFocus();
                }
            } catch (Exception e) {
                // si ocurre un error, es informado al usuario
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el país: "+e.getMessage()); 
            }
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    
    public void rellenarTabla() {
        
        DefaultTableModel modelo = new DefaultTableModel(new Object [][] { }, new String [] {"ID", "Nombre"});
        //vaciando tabla
        tablaListaPaises.setModel(modelo);
        
        //recargando valores
        for(Pais p : new PaisJpaController(emf).findPaisEntities()) {
            Object[] fila = new Object[5];
            //rellenando los valores del arreglo con los valores que debe contener la tabla
            fila[0] = p.getIdPais();
            fila[1] = p.getNombre();
            modelo.addRow(fila);
        }
        tablaListaPaises.setModel(modelo);
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
            java.util.logging.Logger.getLogger(AdministradorPaises.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradorPaises.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradorPaises.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradorPaises.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorPaises().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombrePais;
    private javax.swing.JPanel panelInfoPais;
    private javax.swing.JTable tablaListaPaises;
    private javax.swing.JTextField txtNombrePais;
    // End of variables declaration//GEN-END:variables
}
