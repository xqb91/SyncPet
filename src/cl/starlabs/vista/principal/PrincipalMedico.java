/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.principal;

import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Usuarios;
import cl.starlabs.vista.login.PantallaBloqueo;
import cl.starlabs.vista.paciente.ListarPacientes;
import cl.starlabs.vista.paciente.RegistroPaciente;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.UIManager;

/**
 *
 * @author Victor Manuel Araya
 */
public class PrincipalMedico extends javax.swing.JFrame {

    Usuarios u = null;
    Sucursal s = null;
        
    public PrincipalMedico() {
        initComponents();
        //centrando ventana
        this.setLocationRelativeTo(null);
        //colocando icono a ventana
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_renovado.png"));
        setIconImage(icon);
        setVisible(true);
    }

    public PrincipalMedico(Usuarios u, Sucursal s) {
        initComponents();
        //seteando usuario
        this.u = u;
        this.s = s;
        //centrando ventana
        this.setLocationRelativeTo(null);
        //seteando el titulo de la ventana
        this.setTitle("SyncPet Veterinario :: Conectado como "+u.getUsuario()+" ("+s.getNombre()+")");
        //colocando icono a ventana
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_renovado.png"));
        setIconImage(icon);
        setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProximasAtenciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAtencionesProximas = new javax.swing.JTable();
        btnAddPaciente = new javax.swing.JButton();
        btnAddPropietario = new javax.swing.JButton();
        btnAddEvento = new javax.swing.JButton();
        btnBloquearTerminal = new javax.swing.JButton();
        btnModoUrgencia = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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
        menPropietarios_paciente = new javax.swing.JMenuItem();
        menAgenda = new javax.swing.JMenu();
        menAgenda_calendario = new javax.swing.JMenuItem();
        menAgenda_hoy = new javax.swing.JMenuItem();
        menAgenda_add = new javax.swing.JMenuItem();
        menAgenda_find = new javax.swing.JMenuItem();
        menAgenda_historial = new javax.swing.JMenuItem();
        menAgenda_urgencia = new javax.swing.JMenuItem();
        menFicha = new javax.swing.JMenu();
        menFicha_anamnesis = new javax.swing.JMenuItem();
        menFicha_find = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: conectado como usuario@nombre_clínica (Sucursal)");
        setResizable(false);

        panelProximasAtenciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Próximas Atenciones Agendadas"));

        tablaAtencionesProximas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
            .addGroup(panelProximasAtencionesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelProximasAtencionesLayout.setVerticalGroup(
            panelProximasAtencionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnAddPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAddPaciente.setText("Agregar Paciente");

        btnAddPropietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAddPropietario.setText("Agregar Propietario");

        btnAddEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/date.png"))); // NOI18N
        btnAddEvento.setText("Agendar Evento");

        btnBloquearTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/sistema/logo_mini.png"))); // NOI18N
        btnBloquearTerminal.setText("Bloquear Terminal");

        btnModoUrgencia.setBackground(new java.awt.Color(255, 153, 153));
        btnModoUrgencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/exclamation.png"))); // NOI18N
        btnModoUrgencia.setText("Modo Atención Urgencia");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Atención Actual"));

        jButton1.setBackground(new java.awt.Color(153, 204, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/accept.png"))); // NOI18N
        jButton1.setText("Confirmar Atención");

        jButton2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/error.png"))); // NOI18N
        jButton2.setText("Paciente Equivocado");

        jButton3.setBackground(new java.awt.Color(255, 204, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/error.png"))); // NOI18N
        jButton3.setText("Propietario Equivocado");

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/action_stop.gif"))); // NOI18N
        jButton4.setText("Cancelar Atención");

        jButton5.setBackground(new java.awt.Color(153, 204, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/bell_go.png"))); // NOI18N
        jButton5.setText("Cerrar Atención");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

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
        menPacientes.add(menPacientes_tree);

        jMenuBar1.add(menPacientes);

        menPropietarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/user.png"))); // NOI18N
        menPropietarios.setText("Propietarios");

        menPropietarios_add.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        menPropietarios_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/user_add.png"))); // NOI18N
        menPropietarios_add.setText("Registrar Propietario");
        menPropietarios.add(menPropietarios_add);

        menPropietarios_find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        menPropietarios_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        menPropietarios_find.setText("Buscar Propietario");
        menPropietarios.add(menPropietarios_find);

        menPropietarios_admin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menPropietarios_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/application.png"))); // NOI18N
        menPropietarios_admin.setText("Administrar Propietarios");
        menPropietarios.add(menPropietarios_admin);

        menPropietarios_paciente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menPropietarios_paciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/pet.png"))); // NOI18N
        menPropietarios_paciente.setText("Asociar Paciente");
        menPropietarios.add(menPropietarios_paciente);

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

        menFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/book.png"))); // NOI18N
        menFicha.setText("Ficha Clínica");

        menFicha_anamnesis.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menFicha_anamnesis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/book_go.png"))); // NOI18N
        menFicha_anamnesis.setText("Anamnesis");
        menFicha.add(menFicha_anamnesis);

        menFicha_find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menFicha_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/find.png"))); // NOI18N
        menFicha_find.setText("Busca Paciente");
        menFicha.add(menFicha_find);

        jMenuBar1.add(menFicha);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBloquearTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModoUrgencia))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelProximasAtenciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelProximasAtenciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBloquearTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModoUrgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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

    private void menPacientes_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPacientes_adminActionPerformed
        new ListarPacientes(u, s).setVisible(true);
    }//GEN-LAST:event_menPacientes_adminActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PrincipalMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenSyncpet;
    private javax.swing.JButton btnAddEvento;
    private javax.swing.JButton btnAddPaciente;
    private javax.swing.JButton btnAddPropietario;
    private javax.swing.JButton btnBloquearTerminal;
    private javax.swing.JButton btnModoUrgencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menAgenda;
    private javax.swing.JMenuItem menAgenda_add;
    private javax.swing.JMenuItem menAgenda_calendario;
    private javax.swing.JMenuItem menAgenda_find;
    private javax.swing.JMenuItem menAgenda_historial;
    private javax.swing.JMenuItem menAgenda_hoy;
    private javax.swing.JMenuItem menAgenda_urgencia;
    private javax.swing.JMenu menFicha;
    private javax.swing.JMenuItem menFicha_anamnesis;
    private javax.swing.JMenuItem menFicha_find;
    private javax.swing.JMenu menPacientes;
    private javax.swing.JMenuItem menPacientes_add;
    private javax.swing.JMenuItem menPacientes_admin;
    private javax.swing.JMenuItem menPacientes_find;
    private javax.swing.JMenuItem menPacientes_tree;
    private javax.swing.JMenu menPropietarios;
    private javax.swing.JMenuItem menPropietarios_add;
    private javax.swing.JMenuItem menPropietarios_admin;
    private javax.swing.JMenuItem menPropietarios_find;
    private javax.swing.JMenuItem menPropietarios_paciente;
    private javax.swing.JPanel panelProximasAtenciones;
    private javax.swing.JMenuItem syncmen_lock;
    private javax.swing.JMenuItem syncmen_logout;
    private javax.swing.JMenuItem syncmen_salir;
    private javax.swing.JMenuItem syncmen_server;
    private javax.swing.JTable tablaAtencionesProximas;
    // End of variables declaration//GEN-END:variables
}
