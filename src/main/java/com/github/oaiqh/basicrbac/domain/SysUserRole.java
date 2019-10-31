package com.github.oaiqh.basicrbac.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_user_role")
public class SysUserRole extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private SysUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private SysRole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRole that = (SysUserRole) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }

    public SysUserRole() {
    }

    public SysUserRole(SysUser user, SysRole role) {
        this.user = user;
        this.role = role;
        this.id = new UserRoleId(user.getId(), role.getId());
    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}