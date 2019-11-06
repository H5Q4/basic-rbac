package com.github.oaiqh.basicrbac.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sys_dept")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class SysDept extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String level;

    @Column(nullable = false)
    private Integer seq;

    @Column(length = 200)
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDept sysDept = (SysDept) o;
        return Objects.equals(id, sysDept.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}