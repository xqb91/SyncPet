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
@Table(name = "Tipo_Patolog\u00eda", catalog = "syncpet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPatolog\u00eda.findAll", query = "SELECT t FROM TipoPatolog\u00eda t"),
    @NamedQuery(name = "TipoPatolog\u00eda.findByIdTipoPatologia", query = "SELECT t FROM TipoPatolog\u00eda t WHERE t.idTipoPatologia = :idTipoPatologia"),
    @NamedQuery(name = "TipoPatolog\u00eda.findByNombrePatolog\u00eda", query = "SELECT t FROM TipoPatolog\u00eda t WHERE t.nombrePatolog\u00eda = :nombrePatolog\u00eda")})
public class TipoPatología implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_patologia", nullable = false)
    private Integer idTipoPatologia;
    @Basic(optional = false)
    @Column(name = "nombre_patolog\u00eda", nullable = false, length = 250)
    private String nombrePatología;
    @Basic(optional = false)
    @Lob
    @Column(name = "etiologia", nullable = false, length = 2147483647)
    private String etiologia;
    @Basic(optional = false)
    @Lob
    @Column(name = "patogenia", nullable = false, length = 2147483647)
    private String patogenia;
    @Basic(optional = false)
    @Lob
    @Column(name = "morfologia", nullable = false, length = 2147483647)
    private String morfologia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPatologia")
    private Collection<Patologias> patologiasCollection;

    public TipoPatología() {
    }

    public TipoPatología(Integer idTipoPatologia) {
        this.idTipoPatologia = idTipoPatologia;
    }

    public TipoPatología(Integer idTipoPatologia, String nombrePatología, String etiologia, String patogenia, String morfologia) {
        this.idTipoPatologia = idTipoPatologia;
        this.nombrePatología = nombrePatología;
        this.etiologia = etiologia;
        this.patogenia = patogenia;
        this.morfologia = morfologia;
    }

    public Integer getIdTipoPatologia() {
        return idTipoPatologia;
    }

    public void setIdTipoPatologia(Integer idTipoPatologia) {
        this.idTipoPatologia = idTipoPatologia;
    }

    public String getNombrePatología() {
        return nombrePatología;
    }

    public void setNombrePatología(String nombrePatología) {
        this.nombrePatología = nombrePatología;
    }

    public String getEtiologia() {
        return etiologia;
    }

    public void setEtiologia(String etiologia) {
        this.etiologia = etiologia;
    }

    public String getPatogenia() {
        return patogenia;
    }

    public void setPatogenia(String patogenia) {
        this.patogenia = patogenia;
    }

    public String getMorfologia() {
        return morfologia;
    }

    public void setMorfologia(String morfologia) {
        this.morfologia = morfologia;
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
        hash += (idTipoPatologia != null ? idTipoPatologia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPatología)) {
            return false;
        }
        TipoPatología other = (TipoPatología) object;
        if ((this.idTipoPatologia == null && other.idTipoPatologia != null) || (this.idTipoPatologia != null && !this.idTipoPatologia.equals(other.idTipoPatologia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.starlabs.modelo.TipoPatolog\u00eda[ idTipoPatologia=" + idTipoPatologia + " ]";
    }
    
}
