/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "mst_palette")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MstPalette.findAll", query = "SELECT m FROM MstPalette m"),
    @NamedQuery(name = "MstPalette.findByMstPaletteId", query = "SELECT m FROM MstPalette m WHERE m.mstPaletteId = :mstPaletteId"),
    @NamedQuery(name = "MstPalette.findByPaletteCd", query = "SELECT m FROM MstPalette m WHERE m.paletteCd = :paletteCd"),
    @NamedQuery(name = "MstPalette.findByPaletteName", query = "SELECT m FROM MstPalette m WHERE m.paletteName = :paletteName"),
    @NamedQuery(name = "MstPalette.findByVersionNo", query = "SELECT m FROM MstPalette m WHERE m.versionNo = :versionNo"),
    @NamedQuery(name = "MstPalette.findByInsertDate", query = "SELECT m FROM MstPalette m WHERE m.insertDate = :insertDate"),
    @NamedQuery(name = "MstPalette.findByUpdateDate", query = "SELECT m FROM MstPalette m WHERE m.updateDate = :updateDate")})
public class MstPalette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mst_palette_id")
    private Integer mstPaletteId;
    @Column(name = "palette_cd")
    private String paletteCd;
    @Column(name = "palette_name")
    private String paletteName;
    @Column(name = "version_no")
    private Integer versionNo;
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(mappedBy = "mstPaletteId")
    @JsonIgnore
    private Collection<TrnDesign> trnDesignCollection;

    public MstPalette() {
    }

    public MstPalette(Integer mstPaletteId) {
        this.mstPaletteId = mstPaletteId;
    }

    public Integer getMstPaletteId() {
        return mstPaletteId;
    }

    public void setMstPaletteId(Integer mstPaletteId) {
        this.mstPaletteId = mstPaletteId;
    }

    public String getPaletteCd() {
        return paletteCd;
    }

    public void setPaletteCd(String paletteCd) {
        this.paletteCd = paletteCd;
    }

    public String getPaletteName() {
        return paletteName;
    }

    public void setPaletteName(String paletteName) {
        this.paletteName = paletteName;
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

    @XmlTransient
    public Collection<TrnDesign> getTrnDesignCollection() {
        return trnDesignCollection;
    }

    public void setTrnDesignCollection(Collection<TrnDesign> trnDesignCollection) {
        this.trnDesignCollection = trnDesignCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mstPaletteId != null ? mstPaletteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MstPalette)) {
            return false;
        }
        MstPalette other = (MstPalette) object;
        if ((this.mstPaletteId == null && other.mstPaletteId != null) || (this.mstPaletteId != null && !this.mstPaletteId.equals(other.mstPaletteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beadsan.entity.MstPalette[ mstPaletteId=" + mstPaletteId + " ]";
    }
    
}
