/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.agenda;

import cl.starlabs.controladores.AgendaDetalleJpaController;
import cl.starlabs.controladores.AgendaJpaController;
import cl.starlabs.controladores.PropietarioJpaController;
import cl.starlabs.herramientas.HerramientasRapidas;
import cl.starlabs.modelo.Agenda;
import cl.starlabs.modelo.Propietario;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Janno
 */
public class AgendarAtencion extends javax.swing.JFrame {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SyncPetPU");
    AgendaJpaController jpa = new AgendaJpaController(emf);
    AgendaDetalleJpaController jpb = new AgendaDetalleJpaController(emf);
    HerramientasRapidas hr = new HerramientasRapidas();
    Calendar cal = new GregorianCalendar();
    
    public AgendarAtencion() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        cal.set(Calendar.SECOND, 0);
        
        
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
        atencionesHoy();
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
    
    //las horas deben ser agendadas cada 15 minutos, se debe calcular la proxima atención
    public Calendar atencionAnterior(Calendar calendario) {
        if(calendario == null) {
            hr.mostrarError("El calendario se encuentra vacío desde el sistema... Consulte a StarLabs");
            return null;
        }else{
            if(calendario.get(Calendar.MINUTE) > 0 && calendario.get(Calendar.MINUTE) < 15) {
                int minuto = 15 - calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, (minuto*-1));
                actualizarDatosAtencion(calendario);
                return calendario;
            }else if(calendario.get(Calendar.MINUTE) > 16 && calendario.get(Calendar.MINUTE) < 30) {
                int minuto = 30 - calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, (minuto*-1));
                actualizarDatosAtencion(calendario);
                return calendario;
            }else if(calendario.get(Calendar.MINUTE) > 31 && calendario.get(Calendar.MINUTE) < 45) {
                int minuto = 45 -calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, (minuto*-1));
                actualizarDatosAtencion(calendario);
                return calendario;
            }else if(calendario.get(Calendar.MINUTE) > 46 && calendario.get(Calendar.MINUTE) < 60) {
                int minuto = 60 - calendario.get(Calendar.MINUTE);
                calendario.add(Calendar.MINUTE, (minuto*-1));
                actualizarDatosAtencion(calendario);
                return calendario;
            }else{
                calendario.add(Calendar.MINUTE, -15);
                actualizarDatosAtencion(calendario);
                return calendario;
            }
        }
    }
    
    //metodo que actualiza los datos de la atencion actual
    public void actualizarDatosAtencion(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMMMM-yyyy HH:mm");
        //hr.insertarTexto(lblFechaAtencion, cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.DAY_OF_MONTH)+" a las "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
        String fecha = df.format(cal.getTime());
        fecha = fecha.replace(" ", " a las ");
        fecha = fecha.replace("-", " de ");
        hr.insertarTexto(lblFechaAtencion, fecha+" Horas");
    }
    
    //metodo que recorre todas las atenciones para el dia de hoy para rellenar tabla
    public void atencionesHoy() {
        Calendar aux = new GregorianCalendar();
        aux.set(Calendar.HOUR_OF_DAY, 8);
        aux.set(Calendar.MINUTE, 00);
        aux.set(Calendar.SECOND, 00);
        aux.set(Calendar.MILLISECOND, 000);
        Calendar aux2 = new GregorianCalendar();
        aux2.set(Calendar.HOUR_OF_DAY, 21);
        aux2.set(Calendar.MINUTE, 00);
        aux2.set(Calendar.SECOND, 00);
        aux2.set(Calendar.MILLISECOND, 000);
        
        //datos para rellenar la tabla
        DefaultTableModel modelo = new DefaultTableModel(new Object [][] { }, new String [] { "Hora", "Detalle" });
        while(aux.before(aux2)) {
            Object [] obj = new Object[2];
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            obj[0] = df.format(aux.getTime());
            if(jpa.eventos(aux.getTime()).size() == 0 ) {
                obj[1] = "(vacío)";
            }else{
                String texto = "";
                for(Agenda a : jpa.eventos(aux.getTime())) {
                    texto = texto + a.getAgendaDetalleList().get(0).getMascota().getPropietario().getNombres().split(" ")[0]+" ";
                    texto = texto + a.getAgendaDetalleList().get(0).getMascota().getPropietario().getApaterno()+" (Paciente: ";
                    texto = texto + a.getAgendaDetalleList().get(0).getMascota().getNombre().toUpperCase()+") - (";
                    texto = texto + "Dr. "+ a.getAgendaDetalleList().get(0).getVeterinario().getNombres().split(" ")[0]+" ";
                    texto = texto + a.getAgendaDetalleList().get(0).getVeterinario().getApaterno()+") ";
                }
                obj[1] = texto;
            }
            aux.add(Calendar.MINUTE, 15);
            modelo.addRow(obj);
            
        }
        horasDisponibles.setModel(modelo);
        horasDisponibles.getColumnModel().getColumn(0).setWidth(55);
        horasDisponibles.getColumnModel().getColumn(0).setMaxWidth(55);
        horasDisponibles.getColumnModel().getColumn(0).setMinWidth(55);
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
        btnHoraAnterio = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblFechaAtencion = new javax.swing.JLabel();
        lblNombrePropietario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblVeterinario = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        horasDisponibles = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Agendar Atención");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de atencion "));

        jLabel1.setText("Fecha de Atención");

        calendario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                calendarioFocusGained(evt);
            }
        });
        calendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                calendarioMousePressed(evt);
            }
        });
        calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarioPropertyChange(evt);
            }
        });
        calendario.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                calendarioVetoableChange(evt);
            }
        });

        horaAtencion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                horaAtencionPropertyChange(evt);
            }
        });

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
        btnValidarRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarRutActionPerformed(evt);
            }
        });

        jLabel5.setText("Hora de Atención");

        btnSiguienteHora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/arrow_right.png"))); // NOI18N
        btnSiguienteHora.setText("Siguiente");
        btnSiguienteHora.setToolTipText("Siguiente Hora");
        btnSiguienteHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteHoraActionPerformed(evt);
            }
        });

        btnHoraAnterio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/arrow_left.png"))); // NOI18N
        btnHoraAnterio.setText("Anterior");
        btnHoraAnterio.setToolTipText("Hora Anterior");
        btnHoraAnterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoraAnterioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHoraAnterio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(horaAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguienteHora, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtRutPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnValidarRut, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(cmbMascota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(cmbVeterinario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnValidarRut, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtRutPropietario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSiguienteHora, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(horaAtencion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnHoraAnterio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(lblVeterinario))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Horas Disponibles"));

        horasDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora", "Detalle"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(horasDisponibles);
        if (horasDisponibles.getColumnModel().getColumnCount() > 0) {
            horasDisponibles.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1))
                .addGap(36, 36, 36))
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

    private void calendarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarioPropertyChange
        
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        ///hr.mostrarMensaje(df.format(calendario.getDate()).split("-")[0]);
        this.cal.set(Calendar.YEAR, Integer.parseInt(df.format(calendario.getDate()).split("-")[2]));
        this.cal.set(Calendar.MONTH, (Integer.parseInt(df.format(calendario.getDate()).split("-")[1])-1));
        this.cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(df.format(calendario.getDate()).split("-")[0]));
        actualizarDatosAtencion(cal);
    }//GEN-LAST:event_calendarioPropertyChange

    private void calendarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarioMouseClicked
        
    }//GEN-LAST:event_calendarioMouseClicked

    private void calendarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_calendarioFocusGained
        
    }//GEN-LAST:event_calendarioFocusGained

    private void calendarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarioMousePressed

    }//GEN-LAST:event_calendarioMousePressed

    private void calendarioVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_calendarioVetoableChange
        
    }//GEN-LAST:event_calendarioVetoableChange

    private void horaAtencionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_horaAtencionPropertyChange

    }//GEN-LAST:event_horaAtencionPropertyChange

    private void btnHoraAnterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoraAnterioActionPerformed
        this.cal = atencionAnterior(this.cal);
        horaAtencion.setTime(cal.getTime());
        calendario.setDate(cal.getTime());
    }//GEN-LAST:event_btnHoraAnterioActionPerformed

    private void btnValidarRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarRutActionPerformed
        //buscar propietario
        PropietarioJpaController jpc = new PropietarioJpaController(emf);
        if(jpc.buscarPorRut(hr.contenido(txtRutPropietario)) == null) {
            hr.mostrarError("El propietario "+hr.contenido(txtRutPropietario).toUpperCase()+" no existe");
            hr.insertarTexto(txtRutPropietario, "");
            hr.focus(txtRutPropietario);
        }else{
            Propietario pro = jpc.buscarPorRut(hr.contenido(txtRutPropietario));
        }
    }//GEN-LAST:event_btnValidarRutActionPerformed

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
    private javax.swing.JButton btnHoraAnterio;
    private javax.swing.JButton btnSiguienteHora;
    private javax.swing.JButton btnValidarRut;
    private com.toedter.calendar.JCalendar calendario;
    private javax.swing.JComboBox<String> cmbMascota;
    private javax.swing.JComboBox<String> cmbVeterinario;
    private lu.tudor.santec.jtimechooser.JTimeChooser horaAtencion;
    private javax.swing.JTable horasDisponibles;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaAtencion;
    private javax.swing.JLabel lblNombrePropietario;
    private javax.swing.JLabel lblVeterinario;
    private javax.swing.JTextField txtRutPropietario;
    // End of variables declaration//GEN-END:variables
}
