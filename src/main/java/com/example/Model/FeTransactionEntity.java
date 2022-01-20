package com.example.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "fe_transaction", schema = "oopl20211", catalog = "")
public class FeTransactionEntity {
    private Integer id;
    private Date transDate;
    private Long nominal;
    private FeMemberEntity feMemberByMemberCitizenId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "trans_date", nullable = false)
    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    @Basic
    @Column(name = "nominal", nullable = false)
    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeTransactionEntity that = (FeTransactionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(transDate, that.transDate) && Objects.equals(nominal, that.nominal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transDate, nominal);
    }

    @ManyToOne
    @JoinColumn(name = "member_citizenId", referencedColumnName = "citizenId", nullable = false)
    public FeMemberEntity getFeMemberByMemberCitizenId() {
        return feMemberByMemberCitizenId;
    }

    public void setFeMemberByMemberCitizenId(FeMemberEntity feMemberByMemberCitizenId) {
        this.feMemberByMemberCitizenId = feMemberByMemberCitizenId;
    }
}
