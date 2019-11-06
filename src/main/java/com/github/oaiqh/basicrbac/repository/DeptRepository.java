package com.github.oaiqh.basicrbac.repository;

import com.github.oaiqh.basicrbac.domain.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<SysDept, Long> {

}
