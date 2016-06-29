/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.agenda;

import cl.starlabs.controladores.AgendaDetalleJpaController;
import cl.starlabs.controladores.AgendaJpaController;
import cl.starlabs.controladores.DetalleUsuariosJpaController;
import cl.starlabs.controladores.PropietarioJpaController;
import cl.starlabs.controladores.SucursalJpaController;
import cl.starlabs.herramientas.HerramientasRapidas;
import cl.starlabs.herramientas.HerramientasRut;
import cl.starlabs.modelo.DetalleUsuarios;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.Propietario;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.vista.paciente.RegistroPaciente;
import cl.starlabs.vista.propietario.RegistroPropietario;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;

/**
 *
 * @author Victor Manuel Araya
 */
public class AgendaAtencion extends javax.swing.JFrame {

    // ***********instancia de variables necesarias para el sistema ***************
    //--- tools
    HerramientasRapidas              hr = new HerramientasRapidas();
    HerramientasRut                  ha = new HerramientasRut();
    
    //--- valores globales
    Calendar                        cal = null;
    Sucursal                        suc = null;
    Propietario                     pro = null;
    
    //---- valores de base de datos
    EntityManagerFactory            emf = null;
    DetalleUsuariosJpaController    jpa = null;
    AgendaJpaController             jpb = null;
    AgendaDetalleJpaController      jpc = null;
    SucursalJpaController           jpd = null;
    PropietarioJpaController        jpe = null;
    
    public AgendaAtencion() {
        initComponents();
        //centrando ventana
        this.setLocationRelativeTo(null);
        //colocando icono a ventana
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cl/starlabs/imagenes/iconos/calendar_add.png"));
        setIconImage(icon);
        
        //seteando los valores globales de hora y seteando valores en calendario y agendamiento de hora
        inicializarAgendamiento();
        //seteando datos de persistencia y controladores
        this.emf = Persistence.createEntityManagerFactory("SyncPetPU");
        this.jpa = new DetalleUsuariosJpaController(emf);
        this.jpb = new AgendaJpaController(emf);
        this.jpc = new AgendaDetalleJpaController(emf);
        this.jpd = new SucursalJpaController(emf);
        this.jpe = new PropietarioJpaController(emf);
        
        //setenado valores de prueba de aplicación
        this.suc = jpd.findSucursal(1);
        
        //rellenando lista de veterinarios de la sucursal
        rellenarVeterinarios(suc);
        if(cmbVeterinario.getItemCount() == 0) {
            deshabilitarCamposSeleccion();
        }else{
            deshabilitarCamposSeleccion();
            hr.activar(lblRunPropietario);
            hr.activar(txtRun);
            hr.activar(btnFind);
            hr.focus(txtRun);
        }
    }
    

    // --- libreria de métodos utiles de esta aplicacion
    
    //deshabilita los campos cuando la ventana se prepara para agendar una hora
    public void deshabilitarCamposSeleccion() {
        hr.desactivar(lblRunPropietario);
        hr.desactivar(lblPropietario);
        hr.desactivar(txtRun);
        hr.desactivar(btnFind);
        hr.desactivar(lblPropietarioData);
        hr.desactivar(lblDireccion);
        hr.desactivar(lblDireccionData);
        hr.desactivar(lblCiudad);
        hr.desactivar(lblCiudadData);
        hr.desactivar(lblPaciente);
        hr.desactivar(cmbPaciente);
        hr.desactivar(lblFechaAgendar);
        hr.desactivar(lblHoraAgendar);
        hr.desactivar(btnMas);
        hr.desactivar(btnMenos);
        //calendario.setEnabled(false);
        hora.setEnabled(false);
    }
    
    //habilita los campos cuando la ventana se prepara para agendar una hora
    public void habilitarCamposSeleccion() {
        hr.activar(lblRunPropietario);
        hr.activar(lblPropietario);
        hr.activar(txtRun);
        hr.activar(btnFind);
        hr.activar(lblPropietarioData);
        hr.activar(lblDireccion);
        hr.activar(lblDireccionData);
        hr.activar(lblCiudad);
        hr.activar(lblCiudadData);
        hr.activar(lblPaciente);
        hr.activar(cmbPaciente);
        hr.activar(lblFechaAgendar);
        hr.activar(lblHoraAgendar);
        hr.activar(btnMas);
        hr.activar(btnMenos);
        //calendario.setEnabled(true);
        hora.setEnabled(true);
    }
    
    //setea los valores por defecto para el calendario y el sistema en general
    public void inicializarAgendamiento() {
        //seteando la variable de fecha global
        this.cal = new GregorianCalendar();
        cal.set(Calendar.MILLISECOND, 000);
        //seteando la fecha minima de seleccion al calendario
        this.calendario.setMinSelectableDate(new GregorianCalendar().getTime());
        //seteando la fecha máxima de seleccion al calendario (máximo 5 meses)
        Calendar aux = cal;
        aux.add(Calendar.MONTH, 5);
        aux.set(Calendar.SECOND, 0);
        this.calendario.setMaxSelectableDate(aux.getTime());
        //devolviendo la fecha auxiliar a la actual
        aux.add(Calendar.MONTH, -5);
        aux.set(Calendar.MILLISECOND, 000);
        //seteando valor de la hora a agendar (debe encontrarse en intervalos de 15 minutos)
        if(aux.get(Calendar.MINUTE) > 0 && aux.get(Calendar.MINUTE) < 15) {
            int valor = 15 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, valor);
            hora.setTime(aux.getTime());
        }else if(aux.get(Calendar.MINUTE) > 15 && aux.get(Calendar.MINUTE) < 30) {
            int valor = 30 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, valor);
            hora.setTime(aux.getTime());
        }else if(aux.get(Calendar.MINUTE) > 30 && aux.get(Calendar.MINUTE) < 45) {
            int valor = 45 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, valor);
            hora.setTime(aux.getTime()); 
        }else{
            int valor = 60 - aux.get(Calendar.MINUTE);
            aux.add(Calendar.MINUTE, valor);
            hora.setTime(aux.getTime());
        }
        cal.set(Calendar.HOUR_OF_DAY, aux.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, aux.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, 00);
    }
    
    //setea los valores para los veterinarios de esta sucursal
    public void rellenarVeterinarios(Sucursal suc) {
        for(DetalleUsuarios dt : jpa.buscarPorSucursal(suc)) {
            if(dt.getVeterinario() != null) {
                hr.insertarTexto(cmbVeterinario, dt.getVeterinario().getIdVeterinario()+": Dr. "+dt.getVeterinario().getNombres().split(" ")[0]+" "+dt.getVeterinario().getApaterno());
            }
        }
    }
    
    //setea los valores para los pacientes de un propietario
    public void rellenaPacientes() {
        if(pro != null) {
            for(Mascota m : pro.getMascotaList()) {
                hr.insertarTexto(cmbPaciente, m.getIdMascota()+": "+m.getNombre());
            }
        }
    }
    
    public void vaciarVeterinarios() {
        cmbVeterinario.removeAllItems();
    }
    
    public void vaciarPacientes() {
        cmbPaciente.removeAllItems();
    }
    
    public void actualizarProcesoRegistroPaciente() {
        btnFindActionPerformed(null);
    }
    
    
    // --- fin de la libreria de metodos
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblMedicoVeterinario = new javax.swing.JLabel();
        lblRunPropietario = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();
        cmbVeterinario = new javax.swing.JComboBox();
        txtRun = new javax.swing.JTextField();
        cmbPaciente = new javax.swing.JComboBox();
        btnFind = new javax.swing.JButton();
        lblPropietario = new javax.swing.JLabel();
        lblPropietarioData = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblDireccionData = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblCiudadData = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        calendario = new com.toedter.calendar.JCalendar();
        hora = new lu.tudor.santec.jtimechooser.JTimeChooser();
        btnMenos = new javax.swing.JButton();
        btnMas = new javax.swing.JButton();
        lblHoraAgendar = new javax.swing.JLabel();
        lblFechaAgendar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblEventosDetail = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Agendamiento de Horas");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        lblMedicoVeterinario.setText("Médico Veterinario");

        lblRunPropietario.setText("RUN Propietario");

        lblPaciente.setText("Paciente");

        txtRun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRunFocusLost(evt);
            }
        });

        cmbPaciente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbPacienteFocusGained(evt);
            }
        });

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        lblPropietario.setText("Propietario");

        lblPropietarioData.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblPropietarioData.setText("No especificado");

        lblDireccion.setText("Dirección");

        lblDireccionData.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblDireccionData.setText("No especificado");

        lblCiudad.setText("Ciudad");

        lblCiudadData.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblCiudadData.setText("No especificado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMedicoVeterinario)
                    .addComponent(lblPaciente)
                    .addComponent(lblRunPropietario)
                    .addComponent(lblPropietario)
                    .addComponent(lblDireccion)
                    .addComponent(lblCiudad))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtRun)
                        .addGap(10, 10, 10)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPropietarioData)
                            .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccionData)
                            .addComponent(lblCiudadData))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicoVeterinario)
                    .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRunPropietario)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPropietario)
                    .addComponent(lblPropietarioData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDireccion)
                    .addComponent(lblDireccionData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCiudad)
                    .addComponent(lblCiudadData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaciente)
                    .addComponent(cmbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agendamiento"));

        btnMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/delete.png"))); // NOI18N
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed(evt);
            }
        });

        btnMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });

        lblHoraAgendar.setText("Hora a agendar");

        lblFechaAgendar.setText("Fecha a Agendar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaAgendar)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHoraAgendar)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaAgendar)
                    .addComponent(lblHoraAgendar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios"));

        lblEventosDetail.setText("Eventos para : dd de mm de yyyy");

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora", "Detalle"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaResultados);
        if (tablaResultados.getColumnModel().getColumnCount() > 0) {
            tablaResultados.getColumnModel().getColumn(0).setResizable(false);
            tablaResultados.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblEventosDetail)
                        .addGap(0, 182, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblEventosDetail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        //atrasa la hora a agendar en 15 minutos
        Calendar aux = new GregorianCalendar();
        Calendar aux3 = new GregorianCalendar();
        if( (aux3.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) && (aux3.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && (aux3.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) ) {
            //se esta tratando de atrasar en 15 minutos la hora del día actual (pero no podrá retrasarse menos de 15 minutos de la hora actual) porque no se permite
            //registrar horas en el pasado
            Calendar aux2 = hora.getCalendarWithTime(aux);
            if(aux3.getTime().before(this.cal.getTime())) {
                cal.add(Calendar.MINUTE, -15);
                calendario.setDate(cal.getTime());
                hora.setTime(cal.getTime());
            }else{
                hr.mostrarError("Estas tratando de viajar en el tiempo: No se pueden seleccionar horas pasadas para agendar un evento.");
            }
        }else{
            if((aux3.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH)) && (aux3.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && (aux3.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) ) {
                hora.getCalendarWithTime(aux);
                if(aux3.before(aux)) {
                    hr.mostrarError("Estas tratando de viajar en el tiempo: No se pueden seleccionar horas pasadas para agendar un evento.");
                }else{
                    cal.add(Calendar.MINUTE, -15);
                    calendario.setDate(cal.getTime());
                    hora.setTime(cal.getTime());  
                }
            }else{
                cal.add(Calendar.MINUTE, -15);
                calendario.setDate(cal.getTime());
                hora.setTime(cal.getTime());
            }
        }
    }//GEN-LAST:event_btnMenosActionPerformed

    private void txtRunFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRunFocusLost
        //formanteando y validando el rut escrito del propietario
        if(!hr.contenido(txtRun).isEmpty()) {
            hr.insertarTexto(txtRun, ha.formatear(hr.contenido(txtRun)));
            //validando
            if(ha.validar(hr.contenido(txtRun))) {
                btnFindActionPerformed(null);
            }else{
                hr.mostrarError("Rut erroneo");
                txtRun.selectAll();
                hr.focus(txtRun);
            }
        }
    }//GEN-LAST:event_txtRunFocusLost

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if(!hr.contenido(txtRun).isEmpty()) {
            hr.insertarTexto(txtRun, ha.formatear(hr.contenido(txtRun)));
            //validando
            if(ha.validar(hr.contenido(txtRun))) {
                //buscar los datos del propietario y almacenarlo en la variable global
                this.pro = jpe.buscarPorRut(hr.contenido(txtRun));
                if(pro == null) {
                    if(hr.preguntar("El propietario con RUN "+hr.contenido(txtRun)+" no se encuentra registrado.") == 0) {
                        RegistroPropietario rpro = new RegistroPropietario(suc);
                        rpro.setRun(hr.contenido(txtRun));
                        rpro.setVisible(true);
                    }else{
                        hr.insertarTexto(txtRun, "");
                        hr.focus(txtRun);
                        //Estado original
                        hr.desactivar(lblPropietario);
                        hr.desactivar(lblPropietarioData);
                        hr.insertarTexto(lblPropietarioData, "No especificado");
                        hr.desactivar(lblDireccion);
                        hr.desactivar(lblDireccionData);
                        hr.insertarTexto(lblDireccionData, "No especificado");
                        hr.desactivar(lblCiudad);
                        hr.desactivar(lblCiudadData);
                        hr.insertarTexto(lblCiudadData, "No especificado");
                        vaciarPacientes();
                        hr.activar(lblPaciente);
                        hr.desactivar(cmbPaciente);
                    }
                }else{
                    if(pro.getMascotaList().size() == 0) {
                        int respuesta = hr.preguntar("El propietario no tiene pacientes asociados. ¿Desea registrar un paciente asociado a este propietario?");
                        if(respuesta == 0) {
                            RegistroPaciente rpp = new RegistroPaciente(this);
                            rpp.setRun(hr.contenido(txtRun));
                            rpp.setVisible(true);
                            this.dispose();
                        }else{
                            //Estado original
                            hr.desactivar(lblPropietario);
                            hr.desactivar(lblPropietarioData);
                            hr.insertarTexto(lblPropietarioData, "No especificado");
                            hr.desactivar(lblDireccion);
                            hr.desactivar(lblDireccionData);
                            hr.insertarTexto(lblDireccionData, "No especificado");
                            hr.desactivar(lblCiudad);
                            hr.desactivar(lblCiudadData);
                            hr.insertarTexto(lblCiudadData, "No especificado");
                            vaciarPacientes();
                            hr.desactivar(cmbPaciente);
                            hr.desactivar(lblPaciente);
                            pro = null;
                        }
                    }else{
                        //rellenando datos y habilitando campos
                        hr.activar(lblPropietario);
                        hr.activar(lblPropietarioData);
                        hr.insertarTexto(lblPropietarioData, pro.getNombres().split(" ")[0]+" "+pro.getApaterno()+" "+pro.getAmaterno());
                        hr.activar(lblDireccion);
                        hr.activar(lblDireccionData);
                        hr.insertarTexto(lblDireccionData, pro.getDireccion());
                        hr.activar(lblCiudad);
                        hr.activar(lblCiudadData);
                        hr.insertarTexto(lblCiudadData, pro.getComuna().getNombre());
                        vaciarPacientes();
                        hr.activar(cmbPaciente);
                        rellenaPacientes();
                        hr.focus(cmbPaciente);
                        hr.activar(lblPaciente);
                        pro = null;
                    }
                }
            }else{
                hr.mostrarError("Rut erroneo");
                txtRun.selectAll();
                hr.focus(txtRun);
            }
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void cmbPacienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPacienteFocusGained
        if(cmbPaciente.getItemCount() != 0) {
            hr.activar(lblFechaAgendar);
            hr.activar(lblHoraAgendar);
            //calendario.setEnabled(true);
            hora.setEnabled(true);
            hr.activar(btnMas);
            hr.activar(btnMenos);
        }else{
            hr.desactivar(lblFechaAgendar);
            hr.desactivar(lblHoraAgendar);
            //calendario.setEnabled(false);
            hora.setEnabled(false);
            hr.desactivar(btnMas);
            hr.desactivar(btnMenos);
        }
    }//GEN-LAST:event_cmbPacienteFocusGained

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        cal.add(Calendar.MINUTE, 15);
        calendario.setDate(cal.getTime());
        hora.setTime(cal.getTime());
    }//GEN-LAST:event_btnMasActionPerformed

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
            java.util.logging.Logger.getLogger(AgendaAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaAtencion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnMas;
    private javax.swing.JButton btnMenos;
    private com.toedter.calendar.JCalendar calendario;
    private javax.swing.JComboBox cmbPaciente;
    private javax.swing.JComboBox cmbVeterinario;
    private lu.tudor.santec.jtimechooser.JTimeChooser hora;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblCiudadData;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDireccionData;
    private javax.swing.JLabel lblEventosDetail;
    private javax.swing.JLabel lblFechaAgendar;
    private javax.swing.JLabel lblHoraAgendar;
    private javax.swing.JLabel lblMedicoVeterinario;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JLabel lblPropietarioData;
    private javax.swing.JLabel lblRunPropietario;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtRun;
    // End of variables declaration//GEN-END:variables
}
