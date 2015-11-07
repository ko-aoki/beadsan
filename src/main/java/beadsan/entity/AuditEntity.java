package beadsan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ko-aoki on 2015/10/31.
 */
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public class AuditEntity {

    @Column(name = "version_no")
    @Version
    private Integer versionNo;

    @Column(name = "insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @JoinColumn(name = "insert_user", referencedColumnName = "mst_user_id")
    @ManyToOne
    @JsonIgnore
    private MstUser insertUser;

    @JoinColumn(name = "update_user", referencedColumnName = "mst_user_id")
    @ManyToOne
    @JsonIgnore
    private MstUser updatetUser;

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

    public MstUser getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(MstUser insertUser) {
        this.insertUser = insertUser;
    }

    public MstUser getUpdatetUser() {
        return updatetUser;
    }

    public void setUpdatetUser(MstUser updatetUser) {
        this.updatetUser = updatetUser;
    }

}
