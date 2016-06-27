/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.agenda;

import cl.starlabs.controladores.AgendaJpaController;
import cl.starlabs.herramientas.HerramientasRapidas;
import cl.starlabs.modelo.Agenda;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;

/**
 *
 * @author Janno
 */
public class AgendarAtencion extends javax.swing.JFrame {

    AgendaJpaController jpa;
    EntityManagerFactory emf;
    HerramientasRapidas hr = new HerramientasRapidas();
    Calendar cal = new GregorianCalendar();
    
    public AgendarAtencion() {
        initComponents();
        this.emf = Persistence.createEntityManagerFactory("SyncPetPU");
        this.jpa = new AgendaJpaController(emf);
        
        cal.set(Calendar.SECOND, 0);
        
        asignaHoraInicialAutomatica(cal);
        
        btnValidarRut.setEnabled(false);
        cmbMascota.setEnabled(false);
        cmbVeterinario.setEnabled(false);
        
        //seteando valores del calendario
        calendario.setTodayButtonVisible(true);
        calendario.setDecorationBordersVisible(false);
        calendario.setMaxDayCharacters(2);
        calendario.setMinSelectableDate(cal.getTime());
        
        //setenado valores de la hora a tomar
        horaAtencion.setShowSeconds(false);
        cal = proximaAtencion(cal);
        horaAtencion.setTime(cal.getTime());
    }

    //las horas deben ser agendadas cada 15 minutos, se debe calcular la proxima atención
    public Calendar proximaAtencion(Calendar calendario) {
        if(calendario == null) {
            hr.mostrarError("El calendario se encuentra vacío desde el sistema... Consulte a StarLabs");
            return null;
        }else{
            if(calendario.get(Calendar.MINUTE) > 0 && calendario.get(Calendar.MINUTE) < 15) {
                int minuto = 15 - calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, minuto);
                actualizarDatosAtencion(calendario);
                return calendario;
            }else if(calendario.get(Calendar.MINUTE) > 16 && calendario.get(Calendar.MINUTE) < 30) {
                int minuto = 30 - calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, minuto);
                actualizarDatosAtencion(calendario);
                return calendario;
            }else if(calendario.get(Calendar.MINUTE) > 31 && calendario.get(Calendar.MINUTE) < 45) {
                int minuto = 45 -calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, minuto);
                actualizarDatosAtencion(calendario);
                return calendario;
            }else if(calendario.get(Calendar.MINUTE) > 46 && calendario.get(Calendar.MINUTE) < 60) {
                int minuto = 60 - calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, minuto);
                actualizarDatosAtencion(calendario);
                return calendario;
            }else{
                calendario.add(Calendar.MINUTE, 15);
                actualizarDatosAtencion(calendario);
                return calendario;
            }
        }
    }
    
    public void actualizarDatosAtencion(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMMMM-yyyy HH:mm");
        //hr.insertarTexto(lblFechaAtencion, cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.DAY_OF_MONTH)+" a las "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
        String fecha = df.format(cal.getTime());
        fecha = fecha.replace(" ", " a las ");
        fecha = fecha.replace("-", " de ");
        hr.insertarTexto(lblFechaAtencion, fecha);
    }
    
    //verificar que la hora no este ocupada
    public void asignaHoraInicialAutomatica(Calendar cal) {
        //se recupera el calendario para saber en que día consultar si hay horas disponibles
        //fecha actual es cal
        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);
       
        if(jpa.eventosPorFecha(cal.getTime(), cal2.getTime()).size() == 0) {
            //horas disponibles
            Calendar cal3 = new GregorianCalendar();
            cal3.set(Calendar.HOUR_OF_DAY, 00);
            cal3.set(Calendar.MINUTE, 00);
            cal3.set(Calendar.SECOND, 00);
            Agenda ag = null;
            for(Agenda a : jpa.eventosPorFecha(cal.getTime(), cal2.getTime())) {
                for(Agenda b : jpa.eventosPorFecha(cal3.getTime(), cal2.getTime()))
                {
                    if(a.getAgendaDetalleList().get(0).getMascota().getIdMascota() == b.getAgendaDetalleList().get(0).getMascota().getIdMascota()) {
                        ag = a;
                        break;
                    }
                }
            }
            if(ag == null) {
                //todo ok
            }else{
                hr.mostrarMensaje("El paciente ya tiene una atención agendada para hoy: "+);
            }
        }else{
            hr.mostrarError("No hay horas disponibles para hoy");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JCalendar();
        horaAtencion = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRutPropietario = new javax.swing.JTextField();
        cmbMascota = new javax.swing.JComboBox<String>();
        cmbVeterinario = new javax.swing.JComboBox<String>();
        btnValidarRut = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnSiguienteHora = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblFechaAtencion = new javax.swing.JLabel();
        lblNombrePropietario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblVeterinario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Agendar Atención");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de atencion "));

        jLabel1.setText("Fecha de Atención");

        jLabel2.setText("Rut propietario");

        jLabel3.setText("Paciente");

        jLabel4.setText("Veterinario");

        txtRutPropietario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRutPropietarioFocusLost(evt);
            }
        });

        cmbMascota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        cmbVeterinario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        btnValidarRut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N

        jLabel5.setText("Hora de Atención");

        btnSiguienteHora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/arrow_right.png"))); // NOI18N
        btnSiguienteHora.setToolTipText("Siguiente Hora");
        btnSiguienteHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteHoraActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRutPropietario)
                            .addComponent(horaAtencion, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnValidarRut)
                            .addComponent(btnSiguienteHora))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(calendario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(63, 63, 63)
                                        .addComponent(cmbMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(horaAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtRutPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addComponent(btnValidarRut))
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSiguienteHora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/disk.png"))); // NOI18N
        jButton1.setText("Agendar Atención");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de Atención"));

        jLabel6.setText("Fecha de Atención");

        lblFechaAtencion.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblFechaAtencion.setText("--");

        lblNombrePropietario.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNombrePropietario.setText("--");

        jLabel8.setText("Propietario");

        jLabel7.setText("Veterinario");

        lblVeterinario.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblVeterinario.setText("--");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lblFechaAtencion)
                    .addComponent(lblNombrePropietario)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(lblVeterinario))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaAtencion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombrePropietario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVeterinario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRutPropietarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRutPropietarioFocusLost
        if(!txtRutPropietario.getText().isEmpty())
        {
            txtRutPropietario.setText(cl.starlabs.herramientas.HerramientasRut.formatear(txtRutPropietario.getText()));
            if(cl.starlabs.herramientas.HerramientasRut.validar(txtRutPropietario.getText()))
            {
                btnValidarRut.setEnabled(true);
                txtRutPropietario.setForeground(Color.black);
                btnValidarRut.requestFocus();
                cmbMascota.setEnabled(true);
                cmbVeterinario.setEnabled(true);
            }else{
                btnValidarRut.setEnabled(false);
                txtRutPropietario.setForeground(Color.red);
                txtRutPropietario.requestFocus();
            }
        }
    }//GEN-LAST:event_txtRutPropietarioFocusLost

    private void btnSiguienteHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteHoraActionPerformed
        this.cal = proximaAtencion(this.cal);
        horaAtencion.setTime(cal.getTime());
        calendario.setDate(cal.getTime());
    }//GEN-LAST:event_btnSiguienteHoraActionPerformed

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
            java.util.logging.Logger.getLogger(AgendarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendarAtencion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSiguienteHora;
    private javax.swing.JButton btnValidarRut;
    private com.toedter.calendar.JCalendar calendario;
    private javax.swing.JComboBox<String> cmbMascota;
    private javax.swing.JComboBox<String> cmbVeterinario;
    private lu.tudor.santec.jtimechooser.JTimeChooser horaAtencion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFechaAtencion;
    private javax.swing.JLabel lblNombrePropietario;
    private javax.swing.JLabel lblVeterinario;
    private javax.swing.JTextField txtRutPropietario;
    // End of variables declaration//GEN-END:variables
}
