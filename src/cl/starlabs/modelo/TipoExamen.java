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
import javax.persistence.Lob;
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
@Table(name = "Tipo_Examen", catalog = "syncpet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoExamen.findAll", query = "SELECT t FROM TipoExamen t"),
    @NamedQuery(name = "TipoExamen.findByIdTipoExamen", query = "SELECT t FROM TipoExamen t WHERE t.idTipoExamen = :idTipoExamen"),
    @NamedQuery(name = "TipoExamen.findByNombreExamen", query = "SELECT t FROM TipoExamen t WHERE t.nombreExamen = :nombreExamen"),
    @NamedQuery(name = "TipoExamen.findByCosto", query = "SELECT t FROM TipoExamen t WHERE t.costo = :costo")})
public class TipoExamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_examen", nullable = false)
    private Integer idTipoExamen;
    @Basic(optional = false)
    @Column(name = "nombre_examen", nullable = false, length = 250)
    private String nombreExamen;
    @Lob
    @Column(name = "descripci\u00f3m", length = 2147483647)
    private String descripcióm;
    @Basic(optional = false)
    @Column(name = "costo", nullable = false)
    private int costo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoExamen")
    private Collection<Examenes> examenesCollection;

    public TipoExamen() {
    }

    public TipoExamen(Integer idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    public TipoExamen(Integer idTipoExamen, String nombreExamen, int costo) {
        this.idTipoExamen = idTipoExamen;
        this.nombreExamen = nombreExamen;
        this.costo = costo;
    }

    public Integer getIdTipoExamen() {
        return idTipoExamen;
    }

    public void setIdTipoExamen(Integer idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public String getDescripcióm() {
        return descripcióm;
    }

    public void setDescripcióm(String descripcióm) {
        this.descripcióm = descripcióm;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @XmlTransient
    public Collection<Examenes> getExamenesCollection() {
        return examenesCollection;
    }

    public void setExamenesCollection(Collection<Examenes> examenesCollection) {
        this.examenesCollection = examenesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoExamen != null ? idTipoExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoExamen)) {
            return false;
        }
        TipoExamen other = (TipoExamen) object;
        if ((this.idTipoExamen == null && other.idTipoExamen != null) || (this.idTipoExamen != null && !this.idTipoExamen.equals(other.idTipoExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.starlabs.modelo.TipoExamen[ idTipoExamen=" + idTipoExamen + " ]";
    }
    
}
