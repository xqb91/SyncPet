/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.fichamedica;

import cl.starlabs.controladores.HospitalizacionJpaController;
import cl.starlabs.controladores.ProcedimientosJpaController;
import cl.starlabs.controladores.TipoProcedimientoJpaController;
import cl.starlabs.herramientas.HerramientasRapidas;
import cl.starlabs.modelo.Hospitalizacion;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.Procedimientos;
import cl.starlabs.modelo.TipoProcedimiento;
import cl.starlabs.modelo.Veterinario;
import java.util.GregorianCalendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;

/**
 *
 * @author Janno
 */
public class VistaProcedimientos extends javax.swing.JFrame {

    Mascota m = null;
    Veterinario v = null;
    HerramientasRapidas hr = new HerramientasRapidas();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SyncPetPU");
    
    public VistaProcedimientos() {
        initComponents();
        this.setLocationRelativeTo(null);
        rellenarTipoPatologia();
    }
    
    public VistaProcedimientos(Mascota mascota, Veterinario veterinario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.m = mascota;
        this.v = veterinario;
        hr.insertarTexto(lblMascota, m.getNombre());
        hr.insertarTexto(lblVeterinario, v.getNombres().split(" ")[0]+" "+v.getApaterno());
        rellenarTipoPatologia();
    }
    
    public void rellenarTipoPatologia()
    {
        cmbTipoDeProcedimiento.removeAllItems();
        for(TipoProcedimiento tp : new TipoProcedimientoJpaController(emf).findTipoProcedimientoEntities())
        {
            hr.insertarTexto(cmbTipoDeProcedimiento,tp.getIdTipoProcedimiento()+": "+tp.getNombreProcedimiento());
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
        jLabel1 = new javax.swing.JLabel();
        cmbTipoDeProcedimiento = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaObservaciones = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblMascota = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblVeterinario = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Procedimientos");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedimientos"));

        jLabel1.setText("Tipo de Procedimiento");

        cmbTipoDeProcedimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));

        jLabel2.setText("Observaciones");

        textAreaObservaciones.setColumns(20);
        textAreaObservaciones.setRows(5);
        jScrollPane1.setViewportView(textAreaObservaciones);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Mascota");

        lblMascota.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblMascota.setText("Mascota no especificada");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Veterinario");

        lblVeterinario.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblVeterinario.setText("Veterinario no especificado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVeterinario)
                            .addComponent(lblMascota))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmbTipoDeProcedimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbTipoDeProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMascota)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblVeterinario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/disk.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        System.gc();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Procedimientos pro = new Procedimientos();
        try {
            //Recuperando Datos
            TipoProcedimiento tp = new TipoProcedimientoJpaController(emf).findTipoProcedimiento(Integer.parseInt(hr.contenido(cmbTipoDeProcedimiento).split(":")[0]));
            pro.setIdProcedimiento(new ProcedimientosJpaController(emf).ultimo());
            pro.setFecha(new GregorianCalendar().getTime());
            pro.setTipoProcedimiento(tp);
            pro.setObservaciones(textAreaObservaciones.getText());
            pro.setMascota(m);
            pro.setVeterinario(v);
            pro.setHospitalizacion(new HospitalizacionJpaController(emf).findHospitalizacion(1));
            
            new ProcedimientosJpaController(emf).create(pro);
            hr.mostrarMensaje("Contraindicacion registrada exitosamente");
            btnCancelarActionPerformed(evt);
        } catch (Exception e) {
            hr.mostrarError("Se produjo un error "+e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

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
                    javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaProcedimientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbTipoDeProcedimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMascota;
    private javax.swing.JLabel lblVeterinario;
    private javax.swing.JTextArea textAreaObservaciones;
    // End of variables declaration//GEN-END:variables
}
