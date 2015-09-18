/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author ko-aoki
 */
@Entity
@Table(name = "trn_design")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrnDesign.findAll", query = "SELECT t FROM TrnDesign t"),
    @NamedQuery(name = "TrnDesign.findByTrnDesignId", query = "SELECT t FROM TrnDesign t WHERE t.trnDesignId = :trnDesignId"),
    @NamedQuery(name = "TrnDesign.findByName", query = "SELECT t FROM TrnDesign t WHERE t.name = :name"),
    @NamedQuery(name = "TrnDesign.findByDesign", query = "SELECT t FROM TrnDesign t WHERE t.design = :design"),
    @NamedQuery(name = "TrnDesign.findByVersionNo", query = "SELECT t FROM TrnDesign t WHERE t.versionNo = :versionNo"),
    @NamedQuery(name = "TrnDesign.findByInsertDate", query = "SELECT t FROM TrnDesign t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "TrnDesign.findByUpdateDate", query = "SELECT t FROM TrnDesign t WHERE t.updateDate = :updateDate")})
public class TrnDesign implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trn_design_id")
    private Integer trnDesignId;
    @Column(name = "name")
    private String name;
    @Column(name = "design", columnDefinition="TEXT")
    private String design;
    @Column(name = "version_no")
    private Integer versionNo;
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @JoinColumn(name = "mst_palette_id", referencedColumnName = "mst_palette_id")
    @ManyToOne
    @JsonIgnore
    private MstPalette mstPaletteId;
    @JoinColumn(name = "mst_user_id", referencedColumnName = "mst_user_id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private MstUser mstUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trnDesignId")
    @JsonIgnore
    private Collection<TrnTag> trnTagCollection;

    public TrnDesign() {
    }

    public TrnDesign(Integer trnDesignId) {
        this.trnDesignId = trnDesignId;
    }

    public Integer getTrnDesignId() {
        return trnDesignId;
    }

    public void setTrnDesignId(Integer trnDesignId) {
        this.trnDesignId = trnDesignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public MstPalette getMstPaletteId() {
        return mstPaletteId;
    }

    public void setMstPaletteId(MstPalette mstPaletteId) {
        this.mstPaletteId = mstPaletteId;
    }

    public MstUser getMstUserId() {
        return mstUserId;
    }

    public void setMstUserId(MstUser mstUserId) {
        this.mstUserId = mstUserId;
    }

    @XmlTransient
    public Collection<TrnTag> getTrnTagCollection() {
        return trnTagCollection;
    }

    public void setTrnTagCollection(Collection<TrnTag> trnTagCollection) {
        this.trnTagCollection = trnTagCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trnDesignId != null ? trnDesignId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrnDesign)) {
            return false;
        }
        TrnDesign other = (TrnDesign) object;
        if ((this.trnDesignId == null && other.trnDesignId != null) || (this.trnDesignId != null && !this.trnDesignId.equals(other.trnDesignId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beadsan.entity.TrnDesign[ trnDesignId=" + trnDesignId + " ]";
    }

}
