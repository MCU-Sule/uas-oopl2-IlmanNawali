package com.example.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fe_point", schema = "oopl20211", catalog = "")
public class FePointEntity {
    private Integer id;
    private Integer value;
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
    @Column(name = "value", nullable = false)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FePointEntity that = (FePointEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
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
