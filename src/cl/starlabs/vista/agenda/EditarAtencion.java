/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.agenda;

import cl.starlabs.controladores.PropietarioJpaController;
import cl.starlabs.controladores.VeterinarioJpaController;
import cl.starlabs.herramientas.HerramientasRapidas;
import cl.starlabs.modelo.Agenda;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.Veterinario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;

/**
 *
 * @author cetecom
 */
public class EditarAtencion extends javax.swing.JFrame {

    HerramientasRapidas hr = new HerramientasRapidas();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SyncPetPU");
    VeterinarioJpaController jpa = new VeterinarioJpaController(emf);
    
    //variable global
    Agenda a = null;
    
    public EditarAtencion() {
        initComponents();
        rellenarHora();
        seleccionarHora();
        setearFechaCalendario();
        rellenarProfesional();
        rellenarPacientes();
        this.setLocationRelativeTo(null);
    }
    
    public EditarAtencion(Agenda a) {
        initComponents();
        this.a = a;
        if(a != null) {
            hr.insertarTexto(cmbHora, new SimpleDateFormat("HH:mm").format(a.getFechaEvento()));
        }
        rellenarHora();
        //seleccionarHora();
        setearFechaCalendario();
        rellenarProfesional();
        rellenarPacientes();
        this.setLocationRelativeTo(null);
    }

    public void rellenarHora() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        int contador = 0;
        while(contador < 96) {
            cmbHora.addItem(new SimpleDateFormat("HH:mm").format(cal.getTime()));
            cal.add(Calendar.MINUTE, 15);
            contador++;
        }
    }
    
    public void seleccionarHora() {
        Calendar aux = new GregorianCalendar();
        if(aux.get(Calendar.MINUTE) > 0 && aux.get(Calendar.MINUTE) < 15) {
            int calculo = 15 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, calculo);
        }else if(aux.get(Calendar.MINUTE) > 15 && aux.get(Calendar.MINUTE) < 30) {
            int calculo = 30 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, calculo);
        }else if(aux.get(Calendar.MINUTE) > 30 && aux.get(Calendar.MINUTE) < 45) {
            int calculo = 45 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, calculo);
        }else if(aux.get(Calendar.MINUTE) > 45 && aux.get(Calendar.MINUTE) < 60) {
            int calculo = 60 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, calculo);
        }
        for(int i = 0; i<cmbHora.getItemCount(); i++) {
            if(cmbHora.getItemAt(i).toString().compareToIgnoreCase(new SimpleDateFormat("HH:mm").format(aux.getTime())) == 0) {
                cmbHora.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public void setearFechaCalendario() {
        calendario.setMinSelectableDate(new GregorianCalendar().getTime());
    }
    
    public void rellenarProfesional() {
        if(a != null) {
            hr.insertarTexto(cmbProfesional, a.getAgendaDetalleList().get(0).getVeterinario().getIdVeterinario()+": Dr(a). "+a.getAgendaDetalleList().get(0).getVeterinario().getNombres()+" "+a.getAgendaDetalleList().get(0).getVeterinario().getApaterno()+" "+a.getAgendaDetalleList().get(0).getVeterinario().getAmaterno());
        }
        for(Veterinario v : jpa.findVeterinarioEntities()) {
            if(a != null) {
                if(a.getAgendaDetalleList().get(0).getVeterinario().getIdVeterinario() != Integer.parseInt(hr.contenido(cmbProfesional).split(":")[0])) {
                    hr.insertarTexto(cmbProfesional, v.getIdVeterinario()+": Dr(a). "+v.getNombres()+" "+v.getApaterno()+" "+v.getAmaterno());
                }
            }else{
                hr.insertarTexto(cmbProfesional, v.getIdVeterinario()+": Dr(a). "+v.getNombres()+" "+v.getApaterno()+" "+v.getAmaterno());
            }
                
        }
    }
    
    public void rellenarPacientes() {
        hr.insertarTexto(cmbPaciento, a.getAgendaDetalleList().get(0).getMascota().getIdMascota()+": "+a.getAgendaDetalleList().get(0).getMascota().getNombre());
        for(Mascota m : new PropietarioJpaController(emf).buscarPorRut(a.getAgendaDetalleList().get(0).getMascota().getPropietario().getRut()+"-"+a.getAgendaDetalleList().get(0).getMascota().getPropietario().getDv()).getMascotaList()) {
            if(m.getIdMascota() != Integer.parseInt(hr.contenido(cmbPaciento).split(":")[0])) {
                hr.insertarTexto(cmbPaciento, m.getIdMascota()+": "+m.getNombre());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JCalendar();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbProfesional = new javax.swing.JComboBox<>();
        cmbHora = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbPaciento = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Evento");
        setResizable(false);

        jLabel1.setText("Fecha de Reserva");

        jLabel2.setText("Hora de Atención");

        jLabel3.setText("Profesional");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/accept.png"))); // NOI18N
        btnGuardar.setText("Guardar");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        jLabel4.setText("Paciente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(cmbProfesional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 56, Short.MAX_VALUE))
                            .addComponent(cmbPaciento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(cmbProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPaciento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(EditarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarAtencion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JCalendar calendario;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JComboBox<String> cmbPaciento;
    private javax.swing.JComboBox<String> cmbProfesional;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
