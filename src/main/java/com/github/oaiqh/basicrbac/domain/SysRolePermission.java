package com.github.oaiqh.basicrbac.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission extends AbstractAuditingEntity {
    @EmbeddedId
    private RolePermissionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private SysRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("permissionId")
    private SysPermission permission;

    public SysRolePermission() {
    }

    public SysRolePermission(SysRole role, SysPermission permission) {
        this.role = role;
        this.permission = permission;
        this.id = new RolePermissionId(role.getId(), permission.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRolePermission that = (SysRolePermission) o;
        return Objects.equals(role, that.role) &&
                Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, permission);
    }

    public RolePermissionId getId() {
        return id;
    }

    public void setId(RolePermissionId id) {
        this.id = id;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysPermission getPermission() {
        return permission;
    }

    public void setPermission(SysPermission permission) {
        this.permission = permission;
    }
}
