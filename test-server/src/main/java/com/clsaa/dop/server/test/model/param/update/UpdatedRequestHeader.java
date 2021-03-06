package com.clsaa.dop.server.test.model.param.update;

import com.clsaa.dop.server.test.model.param.UpdateParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xihao
 * @version 1.0
 * @since 19/04/2019
 */
@Data
public class UpdatedRequestHeader implements UpdateParam<Long> {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String value;
}
