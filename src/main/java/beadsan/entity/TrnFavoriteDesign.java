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
@Table(name = "trn_favorite_design")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TrnFavoriteDesign.findAll", query = "SELECT t FROM TrnFavoriteDesign t"),
        @NamedQuery(name = "TrnFavoriteDesign.findByTrnFavoriteDesignId", query = "SELECT t FROM TrnFavoriteDesign t WHERE t.trnFavoriteDesignId = :trnFavoriteDesignId"),
        @NamedQuery(name = "TrnFavoriteDesign.findByVersionNo", query = "SELECT t FROM TrnFavoriteDesign t WHERE t.versionNo = :versionNo"),
        @NamedQuery(name = "TrnFavoriteDesign.findByInsertDate", query = "SELECT t FROM TrnFavoriteDesign t WHERE t.insertDate = :insertDate"),
        @NamedQuery(name = "TrnFavoriteDesign.findByUpdateDate", query = "SELECT t FROM TrnFavoriteDesign t WHERE t.updateDate = :updateDate")
})
public class TrnFavoriteDesign extends AuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trn_favorite_design_id")
    private Integer trnFavoriteDesignId;
    @JoinColumn(name = "mst_user_id", referencedColumnName = "mst_user_id")
    @ManyToOne(optional = false)
    private MstUser mstUserId;
    @JoinColumn(name = "trn_design_id", referencedColumnName = "trn_design_id")
    @ManyToOne(optional = false)
    private TrnDesign trnDesignId;

    public TrnFavoriteDesign() {
    }

    public TrnFavoriteDesign(Integer trnFavoriteDesignId) {
        this.trnFavoriteDesignId = trnFavoriteDesignId;
    }

    public Integer getTrnFavoriteDesignId() {
        return trnFavoriteDesignId;
    }

    public void setTrnFavoriteDesignId(Integer trnFavoriteDesignId) {
        this.trnFavoriteDesignId = trnFavoriteDesignId;
    }

    public MstUser getMstUserId() {
        return mstUserId;
    }

    public void setMstUserId(MstUser mstUserId) {
        this.mstUserId = mstUserId;
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
        hash += (trnFavoriteDesignId != null ? trnFavoriteDesignId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrnFavoriteDesign)) {
            return false;
        }
        TrnFavoriteDesign other = (TrnFavoriteDesign) object;
        if ((this.trnFavoriteDesignId == null && other.trnFavoriteDesignId != null) || (this.trnFavoriteDesignId != null && !this.trnFavoriteDesignId.equals(other.trnFavoriteDesignId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beadsan.entity.TrnFavoriteDesign[ trnFavoriteDesignId=" + trnFavoriteDesignId + " ]";
    }
    
}
