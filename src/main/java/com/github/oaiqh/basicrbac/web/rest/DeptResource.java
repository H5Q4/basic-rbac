package com.github.oaiqh.basicrbac.web.rest;

import com.github.oaiqh.basicrbac.domain.SysDept;
import com.github.oaiqh.basicrbac.service.SysDeptService;
import com.github.oaiqh.basicrbac.service.dto.DeptDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class DeptResource {
    private SysDeptService deptService;

    public DeptResource(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @PostMapping("/depts")
    public ResponseEntity<SysDept> createDept(@Valid @RequestBody DeptDTO deptDTO) throws URISyntaxException {
        log.debug("REST request to save dept : {}", deptDTO);

        SysDept dept = deptService.save(deptDTO);
        return ResponseEntity.created(new URI("/api/depts/" + deptDTO.getId())).body(dept);
    }

    @GetMapping("/depts/tree")
    public ResponseEntity<List<DeptDTO>> getDeptTree() {
        List<DeptDTO> deptTree = deptService.getDeptTree();
        return ResponseEntity.ok(deptTree);
    }
}
