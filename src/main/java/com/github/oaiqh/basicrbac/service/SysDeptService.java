package com.github.oaiqh.basicrbac.service;

import apple.laf.JRSUIUtils;
import com.github.oaiqh.basicrbac.domain.SysDept;
import com.github.oaiqh.basicrbac.repository.DeptRepository;
import com.github.oaiqh.basicrbac.service.dto.DeptDTO;
import com.github.oaiqh.basicrbac.service.util.TreeUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SysDeptService {
    private DeptRepository deptRepository;

    public SysDeptService(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    public List<DeptDTO> getDeptTree() {
        List<SysDept> depts = deptRepository.findAll();
        List<DeptDTO> deptDTOS = Lists.newArrayList();
        depts.forEach(dept -> deptDTOS.add(DeptDTO.adapt(dept)));
        return buildDeptTree(deptDTOS);
    }

    private Comparator<DeptDTO> deptComparator = Comparator.comparingInt(DeptDTO::getSeq);

    private List<DeptDTO> buildDeptTree(List<DeptDTO> deptDTOS) {
        if (CollectionUtils.isEmpty(deptDTOS)) {
            return Lists.newArrayList();
        }
        // level: [dept1, dept2...]
        Multimap<String, DeptDTO> deptDTOMultimap = ArrayListMultimap.create();
        List<DeptDTO> root = Lists.newArrayList();
        deptDTOS.forEach(deptDTO -> {
            deptDTOMultimap.put(deptDTO.getLevel(), deptDTO);
            if (deptDTO.getLevel().equals(TreeUtil.ROOT)) {
                root.add(deptDTO);
            }
        });
        root.sort(deptComparator);
        transformDeptList(deptDTOS, TreeUtil.ROOT, deptDTOMultimap);
        return root;
    }

    private void transformDeptList(List<DeptDTO> deptDTOS, String level, Multimap<String, DeptDTO> deptDTOMultimap) {
        deptDTOS.forEach(deptDTO -> {
            String nextLevel = TreeUtil.calculateLevel(level, deptDTO.getId());
            List<DeptDTO> nextDeptDTOs = (List<DeptDTO>) deptDTOMultimap.get(nextLevel);
            if (!CollectionUtils.isEmpty(nextDeptDTOs)) {
                nextDeptDTOs.sort(deptComparator);
                deptDTO.setChildren(nextDeptDTOs);
                transformDeptList(nextDeptDTOs, nextLevel, deptDTOMultimap);
            }
        });
    }

    public SysDept save(DeptDTO deptDTO) {
        if (checkExist(deptDTO.getName(), deptDTO.getParentId())) {
            throw new RuntimeException("Already exists a department with same name");
        }

        SysDept dept = SysDept.builder().id(deptDTO.getId()).name(deptDTO.getName()).parentId(deptDTO.getParentId())
                .seq(deptDTO.getSeq()).remark(deptDTO.getRemark())
                .level(TreeUtil.calculateLevel(getDeptLevel(deptDTO.getParentId()), deptDTO.getParentId()))
                .build();
        return deptRepository.save(dept);
    }

    private boolean checkExist(String name, Long parentId) {
        return false;
    }

    private String getDeptLevel(Long id) {
        SysDept dept = deptRepository.findById(id).orElse(SysDept.builder().build());
        return dept.getLevel();
    }
}
