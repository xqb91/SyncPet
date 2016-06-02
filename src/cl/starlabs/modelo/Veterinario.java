/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cetecom
 */
@Entity
@Table(name = "Veterinario", catalog = "syncpet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veterinario.findAll", query = "SELECT v FROM Veterinario v"),
    @NamedQuery(name = "Veterinario.findByIdVeterinario", query = "SELECT v FROM Veterinario v WHERE v.idVeterinario = :idVeterinario"),
    @NamedQuery(name = "Veterinario.findByRut", query = "SELECT v FROM Veterinario v WHERE v.rut = :rut"),
    @NamedQuery(name = "Veterinario.findByDv", query = "SELECT v FROM Veterinario v WHERE v.dv = :dv"),
    @NamedQuery(name = "Veterinario.findByNombres", query = "SELECT v FROM Veterinario v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "Veterinario.findByApaterno", query = "SELECT v FROM Veterinario v WHERE v.apaterno = :apaterno"),
    @NamedQuery(name = "Veterinario.findByAmaterno", query = "SELECT v FROM Veterinario v WHERE v.amaterno = :amaterno"),
    @NamedQuery(name = "Veterinario.findByEspecialidad", query = "SELECT v FROM Veterinario v WHERE v.especialidad = :especialidad")})
public class Veterinario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_veterinario", nullable = false)
    private Integer idVeterinario;
    @Basic(optional = false)
    @Column(name = "rut", nullable = false, length = 8)
    private String rut;
    @Basic(optional = false)
    @Column(name = "dv", nullable = false)
    private Character dv;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 75)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apaterno", nullable = false, length = 75)
    private String apaterno;
    @Basic(optional = false)
    @Column(name = "amaterno", nullable = false, length = 75)
    private String amaterno;
    @Basic(optional = false)
    @Column(name = "especialidad", nullable = false, length = 110)
    private String especialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Examenes> examenesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Historialvacunas> historialvacunasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Contraindicaciones> contraindicacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialista")
    private Collection<Desparacitaciones> desparacitacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Farmacos> farmacosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Procedimientos> procedimientosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Hospitalizacion> hospitalizacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<AgendaDetalle> agendaDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Anamnesis> anamnesisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinario")
    private Collection<Patologias> patologiasCollection;
    @JoinColumn(name = "sucursal", referencedColumnName = "id_sucursal", nullable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;

    public Veterinario() {
    }

    public Veterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public Veterinario(Integer idVeterinario, String rut, Character dv, String nombres, String apaterno, String amaterno, String especialidad) {
        this.idVeterinario = idVeterinario;
        this.rut = rut;
        this.dv = dv;
        this.nombres = nombres;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.especialidad = especialidad;
    }

    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Character getDv() {
        return dv;
    }

    public void setDv(Character dv) {
        this.dv = dv;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @XmlTransient
    public Collection<Examenes> getExamenesCollection() {
        return examenesCollection;
    }

    public void setExamenesCollection(Collection<Examenes> examenesCollection) {
        this.examenesCollection = examenesCollection;
    }

    @XmlTransient
    public Collection<Historialvacunas> getHistorialvacunasCollection() {
        return historialvacunasCollection;
    }

    public void setHistorialvacunasCollection(Collection<Historialvacunas> historialvacunasCollection) {
        this.historialvacunasCollection = historialvacunasCollection;
    }

    @XmlTransient
    public Collection<Contraindicaciones> getContraindicacionesCollection() {
        return contraindicacionesCollection;
    }

    public void setContraindicacionesCollection(Collection<Contraindicaciones> contraindicacionesCollection) {
        this.contraindicacionesCollection = contraindicacionesCollection;
    }

    @XmlTransient
    public Collection<Desparacitaciones> getDesparacitacionesCollection() {
        return desparacitacionesCollection;
    }

    public void setDesparacitacionesCollection(Collection<Desparacitaciones> desparacitacionesCollection) {
        this.desparacitacionesCollection = desparacitacionesCollection;
    }

    @XmlTransient
    public Collection<Farmacos> getFarmacosCollection() {
        return farmacosCollection;
    }

    public void setFarmacosCollection(Collection<Farmacos> farmacosCollection) {
        this.farmacosCollection = farmacosCollection;
    }

    @XmlTransient
    public Collection<Procedimientos> getProcedimientosCollection() {
        return procedimientosCollection;
    }

    public void setProcedimientosCollection(Collection<Procedimientos> procedimientosCollection) {
        this.procedimientosCollection = procedimientosCollection;
    }

    @XmlTransient
    public Collection<Hospitalizacion> getHospitalizacionCollection() {
        return hospitalizacionCollection;
    }

    public void setHospitalizacionCollection(Collection<Hospitalizacion> hospitalizacionCollection) {
        this.hospitalizacionCollection = hospitalizacionCollection;
    }

    @XmlTransient
    public Collection<AgendaDetalle> getAgendaDetalleCollection() {
        return agendaDetalleCollection;
    }

    public void setAgendaDetalleCollection(Collection<AgendaDetalle> agendaDetalleCollection) {
        this.agendaDetalleCollection = agendaDetalleCollection;
    }

    @XmlTransient
    public Collection<Anamnesis> getAnamnesisCollection() {
        return anamnesisCollection;
    }

    public void setAnamnesisCollection(Collection<Anamnesis> anamnesisCollection) {
        this.anamnesisCollection = anamnesisCollection;
    }

    @XmlTransient
    public Collection<Patologias> getPatologiasCollection() {
        return patologiasCollection;
    }

    public void setPatologiasCollection(Collection<Patologias> patologiasCollection) {
        this.patologiasCollection = patologiasCollection;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVeterinario != null ? idVeterinario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veterinario)) {
            return false;
        }
        Veterinario other = (Veterinario) object;
        if ((this.idVeterinario == null && other.idVeterinario != null) || (this.idVeterinario != null && !this.idVeterinario.equals(other.idVeterinario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.starlabs.modelo.Veterinario[ idVeterinario=" + idVeterinario + " ]";
    }
    
}
