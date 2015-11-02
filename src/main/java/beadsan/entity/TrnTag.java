/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ko-aoki
 */
@Entity
@Table(name = "trn_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrnTag.findAll", query = "SELECT t FROM TrnTag t"),
    @NamedQuery(name = "TrnTag.findByTrnTagId", query = "SELECT t FROM TrnTag t WHERE t.trnTagId = :trnTagId"),
    @NamedQuery(name = "TrnTag.findByVersionNo", query = "SELECT t FROM TrnTag t WHERE t.versionNo = :versionNo"),
    @NamedQuery(name = "TrnTag.findByInsertDate", query = "SELECT t FROM TrnTag t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "TrnTag.findByUpdateDate", query = "SELECT t FROM TrnTag t WHERE t.updateDate = :updateDate")})
public class TrnTag extends AuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trn_tag_id")
    private Integer trnTagId;
    @Basic(optional = false)
    @JoinColumn(name = "mst_tag_id", referencedColumnName = "mst_tag_id")
    @ManyToOne(optional = false)
    private MstTag mstTagId;
    @JoinColumn(name = "trn_design_id", referencedColumnName = "trn_design_id")
    @ManyToOne(optional = false)
    private TrnDesign trnDesignId;

    public TrnTag() {
    }

    public TrnTag(Integer trnTagId) {
        this.trnTagId = trnTagId;
    }

    public TrnTag(Integer trnTagId, int versionNo, Date insertDate, Date updateDate) {
        this.trnTagId = trnTagId;
    }

    public Integer getTrnTagId() {
        return trnTagId;
    }

    public void setTrnTagId(Integer trnTagId) {
        this.trnTagId = trnTagId;
    }

    public MstTag getMstTagId() {
        return mstTagId;
    }

    public void setMstTagId(MstTag mstTagId) {
        this.mstTagId = mstTagId;
    }

    public TrnDesign getTrnDesignId() {
        return trnDesignId;
    }

    public void setTrnDesignId(TrnDesign trnDesignId) {
        this.trnDesignId = trnDesignId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trnTagId != null ? trnTagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrnTag)) {
            return false;
        }
        TrnTag other = (TrnTag) object;
        if ((this.trnTagId == null && other.trnTagId != null) || (this.trnTagId != null && !this.trnTagId.equals(other.trnTagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beadsan.entity.TrnTag[ trnTagId=" + trnTagId + " ]";
    }
    
}
