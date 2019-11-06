package com.github.oaiqh.basicrbac.service.dto;

import com.github.oaiqh.basicrbac.domain.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class DeptDTO {
    private Long id;

    private Long parentId;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    private String level;

    @NotNull
    private Integer seq;

    @Size(max = 200)
    private String remark;

    private List<DeptDTO> children;

    public static DeptDTO adapt(SysDept dept) {
        DeptDTO deptDTO = new DeptDTO();
        BeanUtils.copyProperties(dept, deptDTO);

        return deptDTO;
    }
}
