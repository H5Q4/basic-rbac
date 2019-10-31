package com.github.oaiqh.basicrbac.domain;

import javax.persistence.*;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_permission")
public class SysPermission extends AbstractAuditingEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(nullable = false)
    private String code;

    @Column(length = 20, nullable = false)
    private String name;

    private String url;

    @Column(nullable = false)
    private Byte type;

    @Column(nullable = false)
    private Integer seq;

    @Column(nullable = false)
    private Boolean activated;

    @Column(length = 200)
    private String remark;

    @OneToMany(
            mappedBy = "permission",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SysRolePermission> roles = Sets.newHashSet();

    public SysPermission() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermission that = (SysPermission) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<SysRolePermission> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRolePermission> roles) {
        this.roles = roles;
    }
}