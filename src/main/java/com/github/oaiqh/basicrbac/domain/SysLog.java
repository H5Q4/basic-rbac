package com.github.oaiqh.basicrbac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "sys_log")
public class SysLog extends AbstractAuditingEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 0: Unknown; 1: SysDept, 2: SysUser, 3: SysPermissionGroup, 4: SysPermission, 5: SysRole, 6:
     * SysRole <-> SysUser, 7: SysRole <-> SysPermission
     */
    @Column(nullable = false)
    private Byte type = 0;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Column(name = "old_value", length = 500)
    private String oldValue;

    @Column(name = "new_value", length = 500)
    private String newValue;
    @Column(nullable = false)
    private Boolean restored;

    public SysLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysLog sysLog = (SysLog) o;
        return Objects.equals(id, sysLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Boolean getRestored() {
        return restored;
    }

    public void setRestored(Boolean restored) {
        this.restored = restored;
    }
}