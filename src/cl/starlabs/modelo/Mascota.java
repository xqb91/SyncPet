/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cetecom
 */
@Entity
@Table(name = "Mascota", catalog = "syncpet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m"),
    @NamedQuery(name = "Mascota.findByIdMascota", query = "SELECT m FROM Mascota m WHERE m.idMascota = :idMascota"),
    @NamedQuery(name = "Mascota.findByNombre", query = "SELECT m FROM Mascota m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Mascota.findByFechaNacimiento", query = "SELECT m FROM Mascota m WHERE m.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Mascota.findByDefuncion", query = "SELECT m FROM Mascota m WHERE m.defuncion = :defuncion"),
    @NamedQuery(name = "Mascota.findByPeso", query = "SELECT m FROM Mascota m WHERE m.peso = :peso"),
    @NamedQuery(name = "Mascota.findBySexo", query = "SELECT m FROM Mascota m WHERE m.sexo = :sexo"),
    @NamedQuery(name = "Mascota.findByNumeroChip", query = "SELECT m FROM Mascota m WHERE m.numeroChip = :numeroChip"),
    @NamedQuery(name = "Mascota.findByGrupoSanguineo", query = "SELECT m FROM Mascota m WHERE m.grupoSanguineo = :grupoSanguineo"),
    @NamedQuery(name = "Mascota.findByCaracter", query = "SELECT m FROM Mascota m WHERE m.caracter = :caracter")})
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_mascota", nullable = false)
    private Integer idMascota;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 75)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "defuncion")
    @Temporal(TemporalType.DATE)
    private Date defuncion;
    @Column(name = "peso")
    private BigInteger peso;
    @Basic(optional = false)
    @Column(name = "sexo", nullable = false)
    private Character sexo;
    @Column(name = "numero_chip")
    private Integer numeroChip;
    @Basic(optional = false)
    @Column(name = "grupo_sanguineo", nullable = false, length = 10)
    private String grupoSanguineo;
    @Lob
    @Column(name = "foto", length = 2147483647)
    private String foto;
    @Basic(optional = false)
    @Column(name = "caracter", nullable = false)
    private int caracter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Examenes> examenesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Historialvacunas> historialvacunasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Contraindicaciones> contraindicacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Desparacitaciones> desparacitacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Alergias> alergiasCollection;
    @JoinColumn(name = "habitad", referencedColumnName = "id_habitad", nullable = false)
    @ManyToOne(optional = false)
    private Habitad habitad;
    @OneToMany(mappedBy = "madre")
    private Collection<Mascota> mascotaCollection;
    @JoinColumn(name = "madre", referencedColumnName = "id_mascota")
    @ManyToOne
    private Mascota madre;
    @OneToMany(mappedBy = "padre")
    private Collection<Mascota> mascotaCollection1;
    @JoinColumn(name = "padre", referencedColumnName = "id_mascota")
    @ManyToOne
    private Mascota padre;
    @JoinColumn(name = "propietario", referencedColumnName = "id_propietario", nullable = false)
    @ManyToOne(optional = false)
    private Propietario propietario;
    @JoinColumn(name = "raza", referencedColumnName = "id_raza", nullable = false)
    @ManyToOne(optional = false)
    private Raza raza;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Farmacos> farmacosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Hospitalizacion> hospitalizacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<AgendaDetalle> agendaDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Anamnesis> anamnesisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<Patologias> patologiasCollection;

    public Mascota() {
    }

    public Mascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Mascota(Integer idMascota, String nombre, Date fechaNacimiento, Character sexo, String grupoSanguineo, int caracter) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.grupoSanguineo = grupoSanguineo;
        this.caracter = caracter;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getDefuncion() {
        return defuncion;
    }

    public void setDefuncion(Date defuncion) {
        this.defuncion = defuncion;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Integer getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(Integer numeroChip) {
        this.numeroChip = numeroChip;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCaracter() {
        return caracter;
    }

    public void setCaracter(int caracter) {
        this.caracter = caracter;
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
    public Collection<Alergias> getAlergiasCollection() {
        return alergiasCollection;
    }

    public void setAlergiasCollection(Collection<Alergias> alergiasCollection) {
        this.alergiasCollection = alergiasCollection;
    }

    public Habitad getHabitad() {
        return habitad;
    }

    public void setHabitad(Habitad habitad) {
        this.habitad = habitad;
    }

    @XmlTransient
    public Collection<Mascota> getMascotaCollection() {
        return mascotaCollection;
    }

    public void setMascotaCollection(Collection<Mascota> mascotaCollection) {
        this.mascotaCollection = mascotaCollection;
    }

    public Mascota getMadre() {
        return madre;
    }

    public void setMadre(Mascota madre) {
        this.madre = madre;
    }

    @XmlTransient
    public Collection<Mascota> getMascotaCollection1() {
        return mascotaCollection1;
    }

    public void setMascotaCollection1(Collection<Mascota> mascotaCollection1) {
        this.mascotaCollection1 = mascotaCollection1;
    }

    public Mascota getPadre() {
        return padre;
    }

    public void setPadre(Mascota padre) {
        this.padre = padre;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    @XmlTransient
    public Collection<Farmacos> getFarmacosCollection() {
        return farmacosCollection;
    }

    public void setFarmacosCollection(Collection<Farmacos> farmacosCollection) {
        this.farmacosCollection = farmacosCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMascota != null ? idMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.idMascota == null && other.idMascota != null) || (this.idMascota != null && !this.idMascota.equals(other.idMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.starlabs.modelo.Mascota[ idMascota=" + idMascota + " ]";
    }
    
}
