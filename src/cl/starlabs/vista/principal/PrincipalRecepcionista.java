/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.principal;

import cl.starlabs.controladores.MascotaJpaController;
import cl.starlabs.controladores.PropietarioJpaController;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Usuarios;
import cl.starlabs.vista.login.PantallaBloqueo;
import cl.starlabs.vista.paciente.BuscarPaciente;
import cl.starlabs.vista.paciente.DetalleProgenitores;
import cl.starlabs.vista.paciente.ListarPacientes;
import cl.starlabs.vista.paciente.RegistroPaciente;
import cl.starlabs.vista.propietario.BuscarPropietario;
import cl.starlabs.vista.propietario.ListarPropietarios;
import cl.starlabs.vista.propietario.RegistroPropietario;
import java.awt.Image;
import java.awt.Toolkit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.UIManager;

/**
 *
 * @author Victor Manuel Araya
 */
public class PrincipalRecepcionista extends javax.swing.JFrame {

    Usuarios u = null;
    Sucursal s = null;
    EntityManagerFactory emf = null;
    
    public PrincipalRecepcionista() {
        initComponents();
        //centrando ventana
        this.setLocationRelativeTo(null);
        //seteando EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        //colocando icono a ventana
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_renovado.png"));
        setIconImage(icon);
        setVisible(true);
    }
    
    public PrincipalRecepcionista(Usuarios u, Sucursal s) {
        initComponents();
        //seteando usuario
        this.u = u;
        //seteando sucursal
        this.s = s;
        //seteando el titulo de la ventana
        this.setTitle("SyncPet Recepcionista :: Conectado como "+u.getUsuario()+" ("+s.getNombre()+")");
        //centrando ventana
        this.setLocationRelativeTo(null);
        //colocando icono a ventana
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_renovado.png"));
        setIconImage(icon);
        setVisible(true);
        //seteando EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        
        //seteando estadisticas de la ventana
        lblNumPropietarios.setText(new PropietarioJpaController(emf).findPropietarioEntities().size()+ " Propietarios");
        lblNumPacientes.setText(new MascotaJpaController(emf).findMascotaEntities().size() +" Pacientes");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelResumen = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNumPropietarios = new javax.swing.JLabel();
        lblNumPacientes = new javax.swing.JLabel();
        lblNumCumpleaños = new javax.swing.JLabel();
        lblNumHospitalizados = new javax.swing.JLabel();
        lblHoraSistema = new javax.swing.JLabel();
        panelProximasAtenciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAtencionesProximas = new javax.swing.JTable();
        btnAddPaciente = new javax.swing.JButton();
        btnAddPropietario = new javax.swing.JButton();
        btnAddEvento = new javax.swing.JButton();
        btnBloquearTerminal = new javax.swing.JButton();
        btnCambiarSucursal = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenSyncpet = new javax.swing.JMenu();
        syncmen_server = new javax.swing.JMenuItem();
        syncmen_logout = new javax.swing.JMenuItem();
        syncmen_lock = new javax.swing.JMenuItem();
        syncmen_salir = new javax.swing.JMenuItem();
        menPacientes = new javax.swing.JMenu();
        menPacientes_add = new javax.swing.JMenuItem();
        menPacientes_find = new javax.swing.JMenuItem();
        menPacientes_admin = new javax.swing.JMenuItem();
        menPacientes_tree = new javax.swing.JMenuItem();
        menPropietarios = new javax.swing.JMenu();
        menPropietarios_add = new javax.swing.JMenuItem();
        menPropietarios_find = new javax.swing.JMenuItem();
        menPropietarios_admin = new javax.swing.JMenuItem();
        menAgenda = new javax.swing.JMenu();
        menAgenda_calendario = new javax.swing.JMenuItem();
        menAgenda_hoy = new javax.swing.JMenuItem();
        menAgenda_add = new javax.swing.JMenuItem();
        menAgenda_find = new javax.swing.JMenuItem();
        menAgenda_historial = new javax.swing.JMenuItem();
        menAgenda_urgencia = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: conectado como usuario@nombre_clínica (Sucursal)");
        setResizable(false);

        panelResumen.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen del Sistema"));

        jLabel1.setText("Propietarios Registrados");

        jLabel2.setText("Pacientes Registrados");

        jLabel3.setText("Cumpleaños de Pacientes");

        jLabel4.setText("Pacientes Hospitalizados");

        jLabel5.setText("Hora del Servidor");

        lblNumPropietarios.setText("0 Propietarios");

        lblNumPacientes.setText("0 Pacientes");

        lblNumCumpleaños.setText("0 Cumpleaños hoy");

        lblNumHospitalizados.setText("0 Pacientes hospitalizados");

        lblHoraSistema.setText("00:00:00 del 00 de mes de 0000");

        javax.swing.GroupLayout panelResumenLayout = new javax.swing.GroupLayout(panelResumen);
        panelResumen.setLayout(panelResumenLayout);
        panelResumenLayout.setHorizontalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(31, 31, 31)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumPropietarios)
                            .addComponent(lblNumPacientes)))
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoraSistema)
                            .addComponent(lblNumHospitalizados)
                            .addComponent(lblNumCumpleaños))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        panelResumenLayout.setVerticalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNumPropietarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNumPacientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNumCumpleaños))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblNumHospitalizados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblHoraSistema))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelProximasAtenciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Próximas Atenciones Agendadas"));

        tablaAtencionesProximas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Paciente", "Propietario", "Hora", "Acciones"
            }
        ));
        jScrollPane1.setViewportView(tablaAtencionesProximas);

        javax.swing.GroupLayout panelProximasAtencionesLayout = new javax.swing.GroupLayout(panelProximasAtenciones);
        panelProximasAtenciones.setLayout(panelProximasAtencionesLayout);
        panelProximasAtencionesLayout.setHorizontalGroup(
            panelProximasAtencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        panelProximasAtencionesLayout.setVerticalGroup(
            panelProximasAtencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnAddPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAddPaciente.setText("Agregar Paciente");
        btnAddPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPacienteActionPerformed(evt);
            }
        });

        btnAddPropietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAddPropietario.setText("Agregar Propietario");
        btnAddPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPropietarioActionPerformed(evt);
            }
        });

        btnAddEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/date.png"))); // NOI18N
        btnAddEvento.setText("Agendar Evento");

        btnBloquearTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_mini.png"))); // NOI18N
        btnBloquearTerminal.setText("Bloquear Terminal");
        btnBloquearTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBloquearTerminalActionPerformed(evt);
            }
        });

        btnCambiarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_mini.png"))); // NOI18N
        btnCambiarSucursal.setText("Cambiar de Sucursal");
        btnCambiarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarSucursalActionPerformed(evt);
            }
        });

        MenSyncpet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_mini.png"))); // NOI18N
        MenSyncpet.setText("SyncPet");

        syncmen_server.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        syncmen_server.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/server.png"))); // NOI18N
        syncmen_server.setText("Estado del Servidor");
        MenSyncpet.add(syncmen_server);

        syncmen_logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        syncmen_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/disconnect.png"))); // NOI18N
        syncmen_logout.setText("Cerrar Sesión");
        syncmen_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncmen_logoutActionPerformed(evt);
            }
        });
        MenSyncpet.add(syncmen_logout);

        syncmen_lock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        syncmen_lock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/lock.png"))); // NOI18N
        syncmen_lock.setText("Bloquear Terminal");
        syncmen_lock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncmen_lockActionPerformed(evt);
            }
        });
        MenSyncpet.add(syncmen_lock);

        syncmen_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        syncmen_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/door_out.png"))); // NOI18N
        syncmen_salir.setText("Salir");
        MenSyncpet.add(syncmen_salir);

        jMenuBar1.add(MenSyncpet);

        menPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/pet.png"))); // NOI18N
        menPacientes.setText("Pacientes");

        menPacientes_add.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menPacientes_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        menPacientes_add.setText("Registrar Paciente");
        menPacientes_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPacientes_addActionPerformed(evt);
            }
        });
        menPacientes.add(menPacientes_add);

        menPacientes_find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menPacientes_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        menPacientes_find.setText("Buscar Paciente");
        menPacientes_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPacientes_findActionPerformed(evt);
            }
        });
        menPacientes.add(menPacientes_find);

        menPacientes_admin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menPacientes_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/application.png"))); // NOI18N
        menPacientes_admin.setText("Administrar Pacientes");
        menPacientes_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPacientes_adminActionPerformed(evt);
            }
        });
        menPacientes.add(menPacientes_admin);

        menPacientes_tree.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menPacientes_tree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/chart_organisation.png"))); // NOI18N
        menPacientes_tree.setText("Especificar Progenitores");
        menPacientes_tree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPacientes_treeActionPerformed(evt);
            }
        });
        menPacientes.add(menPacientes_tree);

        jMenuBar1.add(menPacientes);

        menPropietarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/user.png"))); // NOI18N
        menPropietarios.setText("Propietarios");

        menPropietarios_add.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        menPropietarios_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/user_add.png"))); // NOI18N
        menPropietarios_add.setText("Registrar Propietario");
        menPropietarios_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPropietarios_addActionPerformed(evt);
            }
        });
        menPropietarios.add(menPropietarios_add);

        menPropietarios_find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        menPropietarios_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        menPropietarios_find.setText("Buscar Propietario");
        menPropietarios_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPropietarios_findActionPerformed(evt);
            }
        });
        menPropietarios.add(menPropietarios_find);

        menPropietarios_admin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menPropietarios_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/application.png"))); // NOI18N
        menPropietarios_admin.setText("Administrar Propietarios");
        menPropietarios_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPropietarios_adminActionPerformed(evt);
            }
        });
        menPropietarios.add(menPropietarios_admin);

        jMenuBar1.add(menPropietarios);

        menAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/calendar.png"))); // NOI18N
        menAgenda.setText("Agenda");

        menAgenda_calendario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menAgenda_calendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/date.png"))); // NOI18N
        menAgenda_calendario.setText("Calendario");
        menAgenda.add(menAgenda_calendario);

        menAgenda_hoy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menAgenda_hoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/bell.png"))); // NOI18N
        menAgenda_hoy.setText("Eventos para hoy");
        menAgenda.add(menAgenda_hoy);

        menAgenda_add.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        menAgenda_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/calendar_add.png"))); // NOI18N
        menAgenda_add.setText("Agendar Atención");
        menAgenda.add(menAgenda_add);

        menAgenda_find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menAgenda_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        menAgenda_find.setText("Buscar Atención");
        menAgenda.add(menAgenda_find);

        menAgenda_historial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menAgenda_historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/hourglass.png"))); // NOI18N
        menAgenda_historial.setText("Historial de Atención");
        menAgenda.add(menAgenda_historial);

        menAgenda_urgencia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menAgenda_urgencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/exclamation.png"))); // NOI18N
        menAgenda_urgencia.setText("Atención de Urgencia");
        menAgenda.add(menAgenda_urgencia);

        jMenuBar1.add(menAgenda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelProximasAtenciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnAddPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBloquearTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCambiarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProximasAtenciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBloquearTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void syncmen_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncmen_logoutActionPerformed
        u = null;
        s = null;
        new cl.starlabs.vista.login.IniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_syncmen_logoutActionPerformed

    private void syncmen_lockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncmen_lockActionPerformed
        new PantallaBloqueo(this, u).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_syncmen_lockActionPerformed

    private void menPacientes_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPacientes_addActionPerformed
        new RegistroPaciente(u).setVisible(true);
    }//GEN-LAST:event_menPacientes_addActionPerformed

    private void menPacientes_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPacientes_findActionPerformed
        new BuscarPaciente(u).setVisible(true);
    }//GEN-LAST:event_menPacientes_findActionPerformed

    private void menPacientes_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPacientes_adminActionPerformed
        new ListarPacientes(u, s).setVisible(true);
    }//GEN-LAST:event_menPacientes_adminActionPerformed

    private void menPacientes_treeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPacientes_treeActionPerformed
        new DetalleProgenitores().setVisible(true);
    }//GEN-LAST:event_menPacientes_treeActionPerformed

    private void menPropietarios_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPropietarios_addActionPerformed
        new RegistroPropietario(s).setVisible(true);
    }//GEN-LAST:event_menPropietarios_addActionPerformed

    private void menPropietarios_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPropietarios_findActionPerformed
        new BuscarPropietario(s).setVisible(true);
    }//GEN-LAST:event_menPropietarios_findActionPerformed

    private void menPropietarios_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPropietarios_adminActionPerformed
        new ListarPropietarios(s).setVisible(true);
    }//GEN-LAST:event_menPropietarios_adminActionPerformed

    private void btnAddPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPacienteActionPerformed
        new RegistroPaciente(u).setVisible(true);
    }//GEN-LAST:event_btnAddPacienteActionPerformed

    private void btnAddPropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPropietarioActionPerformed
        new RegistroPropietario(s).setVisible(true);
    }//GEN-LAST:event_btnAddPropietarioActionPerformed

    private void btnBloquearTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBloquearTerminalActionPerformed
        new PantallaBloqueo(this, u).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBloquearTerminalActionPerformed

    private void btnCambiarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarSucursalActionPerformed
        u = null;
        s = null;
        new cl.starlabs.vista.login.IniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarSucursalActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalRecepcionista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenSyncpet;
    private javax.swing.JButton btnAddEvento;
    private javax.swing.JButton btnAddPaciente;
    private javax.swing.JButton btnAddPropietario;
    private javax.swing.JButton btnBloquearTerminal;
    private javax.swing.JButton btnCambiarSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHoraSistema;
    private javax.swing.JLabel lblNumCumpleaños;
    private javax.swing.JLabel lblNumHospitalizados;
    private javax.swing.JLabel lblNumPacientes;
    private javax.swing.JLabel lblNumPropietarios;
    private javax.swing.JMenu menAgenda;
    private javax.swing.JMenuItem menAgenda_add;
    private javax.swing.JMenuItem menAgenda_calendario;
    private javax.swing.JMenuItem menAgenda_find;
    private javax.swing.JMenuItem menAgenda_historial;
    private javax.swing.JMenuItem menAgenda_hoy;
    private javax.swing.JMenuItem menAgenda_urgencia;
    private javax.swing.JMenu menPacientes;
    private javax.swing.JMenuItem menPacientes_add;
    private javax.swing.JMenuItem menPacientes_admin;
    private javax.swing.JMenuItem menPacientes_find;
    private javax.swing.JMenuItem menPacientes_tree;
    private javax.swing.JMenu menPropietarios;
    private javax.swing.JMenuItem menPropietarios_add;
    private javax.swing.JMenuItem menPropietarios_admin;
    private javax.swing.JMenuItem menPropietarios_find;
    private javax.swing.JPanel panelProximasAtenciones;
    private javax.swing.JPanel panelResumen;
    private javax.swing.JMenuItem syncmen_lock;
    private javax.swing.JMenuItem syncmen_logout;
    private javax.swing.JMenuItem syncmen_salir;
    private javax.swing.JMenuItem syncmen_server;
    private javax.swing.JTable tablaAtencionesProximas;
    // End of variables declaration//GEN-END:variables
}
