package com.github.oaiqh.basicrbac.domain;

import javax.persistence.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class SysUser extends AbstractAuditingEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean activated;

    @Column(length = 200)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private SysDept department;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SysUserRole> roles = Sets.newHashSet();

    public void addRole(SysRole role) {
        SysUserRole userRole = new SysUserRole(this, role);
        roles.add(userRole);
        role.getUsers().add(userRole);
    }

    public void removeRole(SysRole role) {
        Iterator<SysUserRole> iterator = roles.iterator();
        while (iterator.hasNext()) {
            SysUserRole userRole = iterator.next();
            if (userRole.getUser().equals(this) && userRole.getRole().equals(role)) {
                iterator.remove();
                userRole.getUser().getRoles().remove(userRole);
                userRole.setUser(null);
                userRole.setRole(null);
            }
        }
    }

    public Set<SysUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysUserRole> roles) {
        this.roles = roles;
    }

    public SysUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public SysDept getDepartment() {
        return department;
    }

    public void setDepartment(SysDept department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(email, sysUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}