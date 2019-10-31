package com.github.oaiqh.basicrbac.domain;

import com.google.common.collect.Sets;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_role")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class SysRole extends AbstractAuditingEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 20)
    private String name;

    @Column(nullable = false)
    private Byte type = 1;

    @Column(nullable = false)
    private Boolean activated;

    @Column(length = 200)
    private String remark;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SysUserRole> users = Sets.newHashSet();

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SysRolePermission> permissions = Sets.newHashSet();

    public void addPermission(SysPermission permission) {
        SysRolePermission rolePermission = new SysRolePermission(this, permission);
        permissions.add(rolePermission);
        permission.getRoles().add(rolePermission);
    }

    public void removePermission(SysPermission permission) {
        for (Iterator<SysRolePermission> iterator = permissions.iterator(); iterator.hasNext(); ) {
            SysRolePermission rolePermission = iterator.next();
            if (rolePermission.getRole().equals(this) && rolePermission.getPermission().equals(permission)) {
                iterator.remove();
                rolePermission.getRole().getPermissions().remove(rolePermission);
                rolePermission.setRole(null);
                rolePermission.setPermission(null);
            }
        }
    }

    public SysRole() {
    }

    public Set<SysUserRole> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUserRole> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(name, sysRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Set<SysRolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SysRolePermission> permissions) {
        this.permissions = permissions;
    }
}