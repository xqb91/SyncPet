/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.vista.administracion.trabajadores;

import cl.starlabs.controladores.DetalleUsuariosJpaController;
import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.PerfilesJpaController;
import cl.starlabs.controladores.SucursalJpaController;
import cl.starlabs.controladores.UsuariosJpaController;
import cl.starlabs.controladores.VeterinarioJpaController;
import cl.starlabs.modelo.Clinica;
import cl.starlabs.modelo.DetalleUsuarios;
import cl.starlabs.modelo.Perfiles;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Usuarios;
import cl.starlabs.modelo.Veterinario;
import java.awt.Color;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cetecom
 */
public class AdministradorTrabajadores extends javax.swing.JFrame  {

    EntityManagerFactory emf = null;
    Sucursal suc = null;
    Usuarios us  = null;
    boolean update = false;
    
    public AdministradorTrabajadores() {
        initComponents();
    }
    
    public AdministradorTrabajadores(Sucursal suc) {
        initComponents();
        emf = Persistence.createEntityManagerFactory("SyncPetPU");
        this.suc = suc;
        this.setTitle("SyncPet :: Administrador de Trabajadores -- "+suc.getClinica().getNombreReal()+" --");
        this.setLocationRelativeTo(null);
        
        //deshabilitar campos
        lblUsuario.setEnabled(false);
        lblPassword.setEnabled(false);
        lblNombres.setEnabled(false);
        lblApaterno.setEnabled(false);
        lblAmaterno.setEnabled(false);
        lblCorreo.setEnabled(false);
        lblEstado.setEnabled(false);
        lblTipoUsuario.setEnabled(false);
        lblEspecialidad.setEnabled(false);
        lblRun.setEnabled(false);
        txtUsuario.setEnabled(false);
        txtContrasena.setEnabled(false);
        txtNombres.setEnabled(false);
        txtApaterno.setEnabled(false);
        txtAmaterno.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtRun.setEnabled(false);
        cmbTipoUsuario.setEnabled(false);
        txtEspecialidad.setEnabled(false);
        cmbEstado.setEnabled(false);
        btnAccion.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        //rellenando valores
        rellenarTabla();
        
    }

    
    public void rellenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(new Object [][] { }, new String [] { "ID", "Usuario", "Nombre" });
        tablaResultados.getColumnModel().getColumn(0).setResizable(false);
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(10);
        tablaResultados.getColumnModel().getColumn(1).setResizable(false);
        tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(25);
        tablaResultados.getColumnModel().getColumn(2).setResizable(false);
        for(Usuarios us : new UsuariosJpaController(emf).findUsuariosEntities()) {
            Object[] obj = new Object[3];
            obj[0] = us.getId();
            obj[1] = us.getUsuario();
            obj[2] = us.getApaterno()+" "+us.getNombres().split(" ")[0];
            modelo.addRow(obj);
        }
        tablaResultados.setModel(modelo);
    }
    
    public void rellenarTipoUsuario() {
        cmbTipoUsuario.removeAllItems();
        if(us != null)
        {
            cmbTipoUsuario.addItem(us.getPerfil().getId()+": "+us.getPerfil().getPerfil());
            for(Perfiles p : new PerfilesJpaController(emf).findPerfilesEntities() )
            {
                if(p.getPerfil().compareToIgnoreCase(us.getPerfil().getPerfil()) != 0) {
                    cmbTipoUsuario.addItem(p.getId()+": "+p.getPerfil());
                }
            }
        }else {
            for(Perfiles p : new PerfilesJpaController(emf).findPerfilesEntities() )
            {
                cmbTipoUsuario.addItem(p.getId()+": "+p.getPerfil());
            }
        }
    }
    
    public void limpiarCampos() {
        //vaciando campos
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtNombres.setText("");
        txtApaterno.setText("");
        txtAmaterno.setText("");
        txtCorreo.setText("");
        txtRun.setText("");
        txtEspecialidad.setText("");
        
        //limpiando comboboxs
        cmbTipoUsuario.removeAllItems();
    }
    
    public void rellenarEstados() {
        if(cmbEstado.getItemCount() == 0) {    
            cmbEstado.addItem("Habilitado");
            cmbEstado.addItem("Deshabilitado");
        }else{
            if(cmbEstado.getItemAt(0).toString().compareToIgnoreCase("Habilitado") == 0) {
                cmbEstado.addItem("Deshabilitado");
            }else{
                cmbEstado.addItem("Habilitado");
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblClinica = new javax.swing.JLabel();
        lblNombreClinica = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        panelInfoCuenta = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        lblNombres = new javax.swing.JLabel();
        lblApaterno = new javax.swing.JLabel();
        lblAmaterno = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApaterno = new javax.swing.JTextField();
        txtAmaterno = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        cmbEstado = new javax.swing.JComboBox<String>();
        btnAccion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTipoUsuario = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox();
        lblEspecialidad = new javax.swing.JLabel();
        txtEspecialidad = new javax.swing.JTextField();
        lblRun = new javax.swing.JLabel();
        txtRun = new javax.swing.JTextField();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SyncPet :: Administrador de Trabajadores");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuentas de Usuario de clínica"));

        lblClinica.setText("Clínica");

        lblNombreClinica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNombreClinica.setText("Nombre de la clínica");

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaResultados.getColumnModel().getColumn(1).setResizable(false);
            tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(25);
            tablaResultados.getColumnModel().getColumn(2).setResizable(false);
        }

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/add.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/starlabs/imagenes/iconos/delete.png"))); // NOI18N
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClinica)
                    .addComponent(lblNombreClinica))
                .addContainerGap(103, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblClinica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreClinica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        panelInfoCuenta.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de la Cuenta"));

        lblUsuario.setText("Usuario");

        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        lblPassword.setText("Contraseña");

        txtContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContrasenaFocusLost(evt);
            }
        });
        txtContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContrasenaKeyTyped(evt);
            }
        });

        lblNombres.setText("Nombres");

        lblApaterno.setText("Apellido Paterno");

        lblAmaterno.setText("Apellido Materno");

        lblCorreo.setText("Correo Electrónico");

        lblEstado.setText("Estado de Cuenta");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtApaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApaternoKeyTyped(evt);
            }
        });

        txtAmaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmaternoKeyTyped(evt);
            }
        });

        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Habilitado", "Deshabilitado" }));
        cmbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadoItemStateChanged(evt);
            }
        });
        cmbEstado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbEstadoFocusLost(evt);
            }
        });
        cmbEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbEstadoMouseClicked(evt);
            }
        });

        btnAccion.setText("Guardar");
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

        lblTipoUsuario.setText("Tipo Usuario");

        cmbTipoUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoUsuarioItemStateChanged(evt);
            }
        });

        lblEspecialidad.setText("Especialidad");

        txtEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspecialidadKeyTyped(evt);
            }
        });

        lblRun.setText("RUN");

        txtRun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRunFocusLost(evt);
            }
        });
        txtRun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRunKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelInfoCuentaLayout = new javax.swing.GroupLayout(panelInfoCuenta);
        panelInfoCuenta.setLayout(panelInfoCuentaLayout);
        panelInfoCuentaLayout.setHorizontalGroup(
            panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoCuentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario)
                    .addComponent(lblPassword)
                    .addComponent(lblNombres)
                    .addComponent(lblApaterno)
                    .addComponent(lblAmaterno)
                    .addComponent(lblCorreo)
                    .addComponent(lblEstado)
                    .addComponent(lblTipoUsuario)
                    .addComponent(lblEspecialidad)
                    .addComponent(lblRun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEspecialidad)
                    .addComponent(txtApaterno, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAmaterno)
                    .addComponent(txtCorreo)
                    .addComponent(cmbEstado, 0, 178, Short.MAX_VALUE)
                    .addComponent(cmbTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRun))
                .addContainerGap())
            .addGroup(panelInfoCuentaLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        panelInfoCuentaLayout.setVerticalGroup(
            panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoCuentaLayout.createSequentialGroup()
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombres)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApaterno)
                    .addComponent(txtApaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmaterno)
                    .addComponent(txtAmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoUsuario))
                .addGap(5, 5, 5)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRun)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEspecialidad))
                .addGap(27, 27, 27)
                .addGroup(panelInfoCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccion)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInfoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        limpiarCampos();
        
        //deshabilitando campos innecesarios y habilitando campos necesarios
        lblUsuario.setEnabled(true);
        lblPassword.setEnabled(true);
        lblNombres.setEnabled(true);
        lblApaterno.setEnabled(true);
        lblAmaterno.setEnabled(true);
        lblCorreo.setEnabled(true);
        lblEstado.setEnabled(true);
        lblTipoUsuario.setEnabled(true);
        lblEspecialidad.setEnabled(false);
        lblRun.setEnabled(false);
        txtUsuario.setEnabled(true);
        txtContrasena.setEnabled(true);
        txtNombres.setEnabled(true);
        txtApaterno.setEnabled(true);
        txtAmaterno.setEnabled(true);
        txtCorreo.setEnabled(true);
        txtRun.setEnabled(false);
        cmbTipoUsuario.setEnabled(true);
        txtEspecialidad.setEnabled(false);
        cmbEstado.setEnabled(true);
        btnAccion.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAccion.setText("Guardar");
        
        tablaResultados.setEnabled(false);
        btnRemover.setEnabled(false);
        
        //rellenando campos necesarios
        rellenarTipoUsuario();
        
        txtUsuario.requestFocus();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaResultadosMouseClicked
        if(tablaResultados.getSelectedColumn() >= 0) {
            //recuperando valores desde la tabla
            DefaultTableModel modelo = (DefaultTableModel)tablaResultados.getModel();
            //consultando por el valor a cargar, es recuperado desde el valor seleccionado recuperando el ROW ID
            us = new UsuariosJpaController(emf).findUsuarios(Integer.parseInt(String.valueOf(modelo.getValueAt(tablaResultados.getSelectedRow(), 0))));
            //si el pais no fue encontrado
            if(us == null) {
                //se informa que el pais no fue encontrado
                JOptionPane.showMessageDialog(null, "Error: El usuario no pudo ser encontrado por el sistema");
                us = null;
            }else{
                //si el pais es encontrado, se definen valores en campos por defecto
                btnAgregarActionPerformed(new java.awt.event.ActionEvent(us, WIDTH, null));
                limpiarCampos();
                cmbEstado.removeAllItems();
                //seteando valores
                txtUsuario.setText(us.getUsuario());
                txtContrasena.setText(us.getContrasena());
                txtNombres.setText(us.getNombres());
                txtApaterno.setText(us.getApaterno());
                txtAmaterno.setText(us.getAmaterno());
                txtCorreo.setText(us.getCorreo());
                if(us.getBloqueado() == '0') {
                    cmbEstado.addItem("Habilitado");
                }else{
                    cmbEstado.addItem("Deshabilitado");
                }
                rellenarEstados();
                rellenarTipoUsuario();
                
                txtRun.setText("");
                txtRun.setEnabled(false);
                lblRun.setEnabled(false);
                txtEspecialidad.setEnabled(false);
                txtEspecialidad.setText("");
                lblEspecialidad.setEnabled(false);
                
                btnAccion.setEnabled(true);
                btnAccion.setText("Actualizar");
                btnCancelar.setEnabled(true);
                //--------------------------
                tablaResultados.setEnabled(false);
                btnAgregar.setEnabled(false);
                btnRemover.setEnabled(true);
                
                update = true;
                
            }
        }
    }//GEN-LAST:event_tablaResultadosMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de eliminar el usuario?");
        if(opcion == 0)
        {
            try {
                try {
                    for(DetalleUsuarios dt : new DetalleUsuariosJpaController(emf).buscarPorUsuario(us)) {
                        new DetalleUsuariosJpaController(emf).destroy(dt.getId());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error eliminando el detalle del usuario "+e.getMessage());
                }
                new UsuariosJpaController(emf).destroy(us.getId());
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                btnCancelarActionPerformed(evt); 
            }catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error generalizado al intentar eliminar el usuario: "+ex.getMessage());
            }
        }else{
            btnCancelarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void cmbTipoUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoUsuarioItemStateChanged
        if(cmbTipoUsuario.getItemCount() != 0) {
            if(cmbTipoUsuario.getSelectedItem().toString().split(":")[0].compareToIgnoreCase("2") == 0) {
                txtEspecialidad.setEnabled(true);
                lblEspecialidad.setEnabled(true);
                txtRun.requestFocus();
                txtRun.setEnabled(true);
                lblRun.setEnabled(true);

            }else{
                txtEspecialidad.setEnabled(false);
                lblEspecialidad.setEnabled(false);
                txtRun.setEnabled(false);
                lblRun.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbTipoUsuarioItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //volviendo la ventana a su estado original
        lblUsuario.setEnabled(false);
        lblPassword.setEnabled(false);
        lblNombres.setEnabled(false);
        lblApaterno.setEnabled(false);
        lblAmaterno.setEnabled(false);
        lblCorreo.setEnabled(false);
        lblEstado.setEnabled(false);
        lblTipoUsuario.setEnabled(false);
        lblEspecialidad.setEnabled(false);
        lblRun.setEnabled(false);
        txtUsuario.setEnabled(false);
        txtContrasena.setEnabled(false);
        txtNombres.setEnabled(false);
        txtApaterno.setEnabled(false);
        txtAmaterno.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtRun.setEnabled(false);
        cmbTipoUsuario.setEnabled(false);
        txtEspecialidad.setEnabled(false);
        cmbEstado.setEnabled(false);
        btnAccion.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnRemover.setEnabled(true);
        btnAccion.setText("Guardar");
        txtUsuario.setBackground(Color.white);

        tablaResultados.setEnabled(true);
        btnRemover.setEnabled(true);
        cmbEstado.removeAllItems();

        //vaciando campos
        limpiarCampos();

        tablaResultados.requestFocus();

        us = null;

        rellenarTabla();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        if(btnAccion.getText().compareToIgnoreCase("Guardar") == 0) {
            //crear nuevo usuario
            //comprobando campos
            if(!txtUsuario.getText().isEmpty()) {
                if(!txtContrasena.getText().isEmpty()) {
                    if(!txtNombres.getText().isEmpty()) {
                        if(!txtApaterno.getText().isEmpty()) {
                            if(!txtAmaterno.getText().isEmpty()) {
                                if(!txtCorreo.getText().isEmpty()) {
                                    if(cmbTipoUsuario.getSelectedItem().toString().split(":")[0].compareToIgnoreCase("2") == 0) {
                                        //comprobacion para registro de veterinario
                                        if(!txtRun.getText().isEmpty()) {
                                            if(!txtEspecialidad.getText().isEmpty()) {
                                                try {
                                                    //creando veterinario
                                                    boolean existe = false;
                                                    Veterinario vet = new VeterinarioJpaController(emf).buscarVeterinarioPorRut(txtRun.getText().split("-")[0]);
                                                    if(vet == null)
                                                    {
                                                        vet = new Veterinario(new VeterinarioJpaController(emf).ultimo(), txtRun.getText().replace(".", "").split("-")[0], txtRun.getText().split("-")[1].charAt(0), txtNombres.getText(), txtApaterno.getText(), txtAmaterno.getText(), txtEspecialidad.getText());
                                                    }else{
                                                        JOptionPane.showMessageDialog(null, "El veterinario ya se encuentra registrado y será vinculado al usuario");
                                                        existe = true;
                                                    }
                                                    //crear el usuario
                                                    //rescatando el perfil
                                                    Perfiles per = new PerfilesJpaController(emf).findPerfiles(Integer.parseInt(cmbTipoUsuario.getSelectedItem().toString().split(":")[0]));
                                                    if(per == null) {
                                                        JOptionPane.showMessageDialog(null, "ERROR: El perfil de usuario no pudo ser encontrado");
                                                    }else{
                                                        //continua operaciones
                                                        //creando usuario
                                                        char bloqueo;
                                                        if(cmbEstado.getSelectedItem().toString().compareToIgnoreCase("Habilitado") == 0) {
                                                            bloqueo = '0';
                                                        }else{
                                                            bloqueo = '1';
                                                        }
                                                        us = new Usuarios(new UsuariosJpaController(emf).ultimo(), txtUsuario.getText(), txtContrasena.getText(), txtNombres.getText(), txtApaterno.getText(), txtAmaterno.getText(), txtCorreo.getText(), bloqueo, per);
                                                        //creando registros!!!
                                                        //1... guardando usuario
                                                        new UsuariosJpaController(emf).create(us);
                                                        //2... creando veterinario
                                                        if(!existe) {
                                                            new VeterinarioJpaController(emf).create(vet);
                                                        }
                                                        //3... creando detalle de usuario
                                                        new DetalleUsuariosJpaController(emf).create(new DetalleUsuarios(new DetalleUsuariosJpaController(emf).ultimo(), us, vet, suc));
                                                        JOptionPane.showMessageDialog(null, "Usuario registrado");
                                                        btnCancelarActionPerformed(evt);
                                                    }
                                                }catch(Exception e)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Error: Al intentar registrar el usuario ocurrió lo siguiente... "+e.getMessage());
                                                }
                                            }else{
                                                JOptionPane.showMessageDialog(null, "Debe especificar la especialidad del médico veterinario");
                                                txtEspecialidad.requestFocus();
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null, "Debe especificar el RUN del médico veterinario");
                                            txtRun.requestFocus();
                                        }
                                    }else{
                                        //registro comun
                                        try {
                                            Perfiles per = new PerfilesJpaController(emf).findPerfiles(Integer.parseInt(cmbTipoUsuario.getSelectedItem().toString().split(":")[0]));
                                            if(per == null) {
                                                JOptionPane.showMessageDialog(null, "ERROR: El perfil de usuario no pudo ser encontrado");
                                            }else{
                                                //continua operaciones
                                                //creando usuario
                                                char bloqueo;
                                                if(cmbEstado.getSelectedItem().toString().compareToIgnoreCase("Habilitado") == 0) {
                                                    bloqueo = '0';
                                                }else{
                                                    bloqueo = '1';
                                                }
                                                us = new Usuarios(new UsuariosJpaController(emf).ultimo(), txtUsuario.getText(), txtContrasena.getText(), txtNombres.getText(), txtApaterno.getText(), txtAmaterno.getText(), txtCorreo.getText(), bloqueo, per);
                                                //creando registros!!!
                                                //1... guardando usuario
                                                new UsuariosJpaController(emf).create(us);
                                                //2... creando detalle de usuario
                                                new DetalleUsuariosJpaController(emf).create(new DetalleUsuarios(new DetalleUsuariosJpaController(emf).ultimo(), us, suc));
                                                JOptionPane.showMessageDialog(null, "Usuario registrado");
                                                btnCancelarActionPerformed(evt);
                                            }
                                        }catch(Exception e)
                                        {
                                            JOptionPane.showMessageDialog(null, "Error: Al intentar registrar el usuario ocurrió lo siguiente... "+e.getMessage());
                                        }
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Debe especificar el correo electrónico del usuario");
                                    txtCorreo.requestFocus();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Debe especificar el apellido materno del usuario");
                                txtAmaterno.requestFocus();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Debe especificar el apellido paterno del usuario");
                            txtApaterno.requestFocus();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Debe especificar los nombres del usuario");
                        txtNombres.requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe especificar una contraseña para el usuario");
                    txtContrasena.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe especificar el nickname del usuario");
                txtUsuario.requestFocus();
            }
        }else{
            //actualizar usuario
            //comprobando campos
            if(!txtUsuario.getText().isEmpty()) {
                if(!txtContrasena.getText().isEmpty()) {
                    if(!txtNombres.getText().isEmpty()) {
                        if(!txtApaterno.getText().isEmpty()) {
                            if(!txtAmaterno.getText().isEmpty()) {
                                if(!txtCorreo.getText().isEmpty()) {
                                    //actualizacion comun
                                    try {
                                        Perfiles per = new PerfilesJpaController(emf).findPerfiles(Integer.parseInt(cmbTipoUsuario.getSelectedItem().toString().split(":")[0]));
                                        if(per == null) {
                                            JOptionPane.showMessageDialog(null, "ERROR: El perfil de usuario no pudo ser encontrado");
                                        }else{
                                            //continua operaciones
                                            //editando usuario usuario
                                            char bloqueo;
                                            if(cmbEstado.getSelectedItem().toString().compareToIgnoreCase("Habilitado") == 0) {
                                                bloqueo = '0';
                                            }else{
                                                bloqueo = '1';
                                            }
                                            Usuarios user = new Usuarios(us.getId(), txtUsuario.getText(), txtContrasena.getText(), txtNombres.getText(), txtApaterno.getText(), txtAmaterno.getText(), txtCorreo.getText(), bloqueo, per);
                                            //creando registros!!!
                                            //1... guardando usuario
                                            new UsuariosJpaController(emf).actualizar(user);

                                            JOptionPane.showMessageDialog(null, "Usuario Actualizado");
                                            btnCancelarActionPerformed(evt);
                                        }
                                    }catch(Exception e)
                                    {
                                        JOptionPane.showMessageDialog(null, "Error: Al intentar registrar el usuario ocurrió lo siguiente... "+e.getMessage());
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Debe especificar el correo electrónico del usuario");
                                    txtCorreo.requestFocus();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Debe especificar el apellido materno del usuario");
                                txtAmaterno.requestFocus();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Debe especificar el apellido paterno del usuario");
                            txtApaterno.requestFocus();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Debe especificar los nombres del usuario");
                        txtNombres.requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe especificar una contraseña para el usuario");
                    txtContrasena.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe especificar el nickname del usuario");
                txtUsuario.requestFocus();
            }
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void cmbEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbEstadoMouseClicked

    }//GEN-LAST:event_cmbEstadoMouseClicked

    private void cmbEstadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEstadoFocusLost
        if(cmbEstado.getSelectedItem().toString().compareToIgnoreCase("deshabilitado") == 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "Si deshabilita al usuario "+txtUsuario.getText()+" este no podrá iniciar sesión hasta que sea habilitado su ingreso. ¿Esta seguro de continuar?");
            if(opcion != 0){
                for(int i = 0; i < cmbEstado.getItemCount(); i++)
                {
                    if(cmbEstado.getItemAt(i).toString().compareToIgnoreCase("Habilitado") == 0) {
                        cmbEstado.setSelectedIndex(i);
                    }
                }
            }
        }
    }//GEN-LAST:event_cmbEstadoFocusLost

    private void cmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadoItemStateChanged

    }//GEN-LAST:event_cmbEstadoItemStateChanged

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if(!txtCorreo.getText().isEmpty()) {
            if(!cl.starlabs.herramientas.HerramientasCorreo.validarEmail(txtCorreo.getText())) {
                lblCorreo.setForeground(Color.red);
                txtCorreo.setForeground(Color.white);
                txtCorreo.setBackground(Color.red);
                txtCorreo.requestFocus();
            }else{
                lblCorreo.setForeground(Color.black);
                txtCorreo.setForeground(Color.black);
                txtCorreo.setBackground(Color.white);
            }
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtAmaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmaternoKeyTyped
        if(txtAmaterno.getText().length() > 73) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAmaternoKeyTyped

    private void txtApaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApaternoKeyTyped
        if(txtApaterno.getText().length() > 73) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApaternoKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        if(txtNombres.getText().length() > 73) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtContrasenaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaKeyTyped
        if(txtContrasena.getText().length() > 248) {
            evt.consume();
        }
    }//GEN-LAST:event_txtContrasenaKeyTyped

    private void txtContrasenaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaFocusLost
        if(!txtContrasena.getText().isEmpty()) {
            if(txtContrasena.getText().length() <= 6) {
                txtContrasena.setForeground(Color.white);
                txtContrasena.setBackground(Color.red);
                lblPassword.setForeground(Color.red);
                txtContrasena.requestFocus();
                JOptionPane.showMessageDialog(null, "La contraseña para el usuario "+txtUsuario.getText()+ " debe tener al menos 7 carácteres!");
            }else{
                txtContrasena.setForeground(Color.black);
                txtContrasena.setBackground(Color.white);
                lblPassword.setForeground(Color.black);
            }
        }
    }//GEN-LAST:event_txtContrasenaFocusLost

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        update = false;
        if(txtUsuario.getText().length() > 98) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if(!update) {
            if(!txtUsuario.getText().isEmpty()) {
                if(txtUsuario.getText().contains("@")) {
                    if(txtUsuario.getText().split("@")[1].compareToIgnoreCase(suc.getClinica().getNombreFantasia()) != 0) {
                        txtUsuario.setText(txtUsuario.getText()+"@"+suc.getClinica().getNombreFantasia());
                    }
                }else{
                    txtUsuario.setText(txtUsuario.getText()+"@"+suc.getClinica().getNombreFantasia());
                }
                if(new UsuariosJpaController(emf).existeUsuario(txtUsuario.getText())) {
                    txtUsuario.setForeground(Color.white);
                    txtUsuario.setBackground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    txtUsuario.requestFocus();
                    JOptionPane.showMessageDialog(null, "El usuario "+txtUsuario.getText()+ " ya se encuentra registrado!");
                    txtUsuario.setText("");
                }else{
                    txtUsuario.setForeground(Color.black);
                    txtUsuario.setBackground(Color.green);
                    lblUsuario.setForeground(Color.black);
                }
            }
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtEspecialidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecialidadKeyTyped
        if(txtEspecialidad.getText().length() > 109) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEspecialidadKeyTyped

    private void txtRunKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRunKeyTyped
        if(txtRun.getText().length() > 8) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if((!Character.isDigit(c)) && (!((c == 'K') || (c == 'k') || (c == '-') || (c == '.'))))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRunKeyTyped

    private void txtRunFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRunFocusLost
        if(!txtRun.getText().isEmpty()) {
            txtRun.setText(cl.starlabs.herramientas.HerramientasRut.formatear(txtRun.getText()));
            if(!cl.starlabs.herramientas.HerramientasRut.validar(txtRun.getText())) {
                lblRun.setForeground(Color.red);
                txtRun.setForeground(Color.white);
                txtRun.setBackground(Color.red);
                txtRun.requestFocus();
            }else{
                lblRun.setForeground(Color.black);
                txtRun.setForeground(Color.black);
                txtRun.setBackground(Color.white);
            }
        }
    }//GEN-LAST:event_txtRunFocusLost


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
            java.util.logging.Logger.getLogger(AdministradorTrabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradorTrabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradorTrabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradorTrabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorTrabajadores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox cmbTipoUsuario;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAmaterno;
    private javax.swing.JLabel lblApaterno;
    private javax.swing.JLabel lblClinica;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombreClinica;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRun;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelInfoCuenta;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtAmaterno;
    private javax.swing.JTextField txtApaterno;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtRun;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
