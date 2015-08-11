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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ko-aoki
 */
@Entity
@Table(name = "mst_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MstUser.findAll", query = "SELECT m FROM MstUser m"),
    @NamedQuery(name = "MstUser.findByMstUserId", query = "SELECT m FROM MstUser m WHERE m.mstUserId = :mstUserId"),
    @NamedQuery(name = "MstUser.findByFirstName", query = "SELECT m FROM MstUser m WHERE m.firstName = :firstName"),
    @NamedQuery(name = "MstUser.findByLastName", query = "SELECT m FROM MstUser m WHERE m.lastName = :lastName"),
    @NamedQuery(name = "MstUser.findByMailAddress", query = "SELECT m FROM MstUser m WHERE m.mailAddress = :mailAddress"),
    @NamedQuery(name = "MstUser.findByNickName", query = "SELECT m FROM MstUser m WHERE m.nickName = :nickName"),
    @NamedQuery(name = "MstUser.findByVersionNo", query = "SELECT m FROM MstUser m WHERE m.versionNo = :versionNo"),
    @NamedQuery(name = "MstUser.findByInsertDate", query = "SELECT m FROM MstUser m WHERE m.insertDate = :insertDate"),
    @NamedQuery(name = "MstUser.findByUpdateDate", query = "SELECT m FROM MstUser m WHERE m.updateDate = :updateDate")})
public class MstUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mst_user_id")
    private Integer mstUserId;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "mail_address")
    private String mailAddress;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "version_no")
    @Version
    private Integer versionNo;
    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mstUserId")
    private Collection<TrnDesign> trnDesignCollection;

    public MstUser() {
    }

    public MstUser(Integer mstUserId) {
        this.mstUserId = mstUserId;
    }

    public Integer getMstUserId() {
        return mstUserId;
    }

    public void setMstUserId(Integer mstUserId) {
        this.mstUserId = mstUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
        hash += (mstUserId != null ? mstUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MstUser)) {
            return false;
        }
        MstUser other = (MstUser) object;
        if ((this.mstUserId == null && other.mstUserId != null) || (this.mstUserId != null && !this.mstUserId.equals(other.mstUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beadsan.entity.MstUser[ mstUserId=" + mstUserId + " ]";
    }

}
