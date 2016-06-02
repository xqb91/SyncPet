/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cetecom
 */
@Entity
@Table(name = "Hospitalizacion", catalog = "syncpet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospitalizacion.findAll", query = "SELECT h FROM Hospitalizacion h"),
    @NamedQuery(name = "Hospitalizacion.findByIdHospitalizacion", query = "SELECT h FROM Hospitalizacion h WHERE h.idHospitalizacion = :idHospitalizacion"),
    @NamedQuery(name = "Hospitalizacion.findByFechaIngreso", query = "SELECT h FROM Hospitalizacion h WHERE h.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Hospitalizacion.findByFechaAltaMedica", query = "SELECT h FROM Hospitalizacion h WHERE h.fechaAltaMedica = :fechaAltaMedica")})
public class Hospitalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_hospitalizacion", nullable = false)
    private Integer idHospitalizacion;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "fecha_alta_medica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAltaMedica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Examenes> examenesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Historialvacunas> historialvacunasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Contraindicaciones> contraindicacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Desparacitaciones> desparacitacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Farmacos> farmacosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Procedimientos> procedimientosCollection;
    @JoinColumn(name = "mascota", referencedColumnName = "id_mascota", nullable = false)
    @ManyToOne(optional = false)
    private Mascota mascota;
    @JoinColumn(name = "veterinario", referencedColumnName = "id_veterinario", nullable = false)
    @ManyToOne(optional = false)
    private Veterinario veterinario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Anamnesis> anamnesisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalizacion")
    private Collection<Patologias> patologiasCollection;

    public Hospitalizacion() {
    }

    public Hospitalizacion(Integer idHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
    }

    public Hospitalizacion(Integer idHospitalizacion, Date fechaIngreso) {
        this.idHospitalizacion = idHospitalizacion;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdHospitalizacion() {
        return idHospitalizacion;
    }

    public void setIdHospitalizacion(Integer idHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaAltaMedica() {
        return fechaAltaMedica;
    }

    public void setFechaAltaMedica(Date fechaAltaMedica) {
        this.fechaAltaMedica = fechaAltaMedica;
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

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
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
        hash += (idHospitalizacion != null ? idHospitalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospitalizacion)) {
            return false;
        }
        Hospitalizacion other = (Hospitalizacion) object;
        if ((this.idHospitalizacion == null && other.idHospitalizacion != null) || (this.idHospitalizacion != null && !this.idHospitalizacion.equals(other.idHospitalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.starlabs.modelo.Hospitalizacion[ idHospitalizacion=" + idHospitalizacion + " ]";
    }
    
}
