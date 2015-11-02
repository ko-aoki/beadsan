/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author ko-aoki
 */
@Entity
@Table(name = "mst_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MstTag.findAll", query = "SELECT m FROM MstTag m"),
    @NamedQuery(name = "MstTag.findByMstTagId", query = "SELECT m FROM MstTag m WHERE m.mstTagId = :mstTagId"),
    @NamedQuery(name = "MstTag.findByName", query = "SELECT m FROM MstTag m WHERE m.name = :name"),
    @NamedQuery(name = "MstTag.findByVersionNo", query = "SELECT m FROM MstTag m WHERE m.versionNo = :versionNo"),
    @NamedQuery(name = "MstTag.findByInsertDate", query = "SELECT m FROM MstTag m WHERE m.insertDate = :insertDate"),
    @NamedQuery(name = "MstTag.findByUpdateDate", query = "SELECT m FROM MstTag m WHERE m.updateDate = :updateDate")})
public class MstTag extends AuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mst_tag_id")
    private Integer mstTagId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mstTagId")
    private Collection<TrnTag> trnTagCollection;

    public MstTag() {
    }

    public MstTag(Integer mstTagId) {
        this.mstTagId = mstTagId;
    }

    public MstTag(Integer mstTagId, String name, int versionNo) {
        this.mstTagId = mstTagId;
        this.name = name;
    }

    public Integer getMstTagId() {
        return mstTagId;
    }

    public void setMstTagId(Integer mstTagId) {
        this.mstTagId = mstTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (mstTagId != null ? mstTagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MstTag)) {
            return false;
        }
        MstTag other = (MstTag) object;
        if ((this.mstTagId == null && other.mstTagId != null) || (this.mstTagId != null && !this.mstTagId.equals(other.mstTagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beadsan.entity.MstTag[ mstTagId=" + mstTagId + " ]";
    }
    
}
