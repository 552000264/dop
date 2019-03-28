package com.clsaa.dop.server.image.controller;

import com.clsaa.dop.server.image.model.vo.AccessLogVO;
import com.clsaa.dop.server.image.service.ProjectLogsService;
import com.clsaa.dop.server.image.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  项目日志的控制器类
 * </p>
 * @author xzt
 * @since 2019-3-27
 */
@RestController
@Api(value = "ProjectLogsController|一个项目日志的控制器类")
public class ProjectLogsController {
    @Autowired
    private ProjectLogsService projectLogsService;

    @ApiOperation(value = "根据项目id获取项目日志")
    @GetMapping(value = "/v1/projects/{project_id}/logs")
    public List<AccessLogVO> getProjectLogs(@ApiParam(value = "项目的id",required = true)@PathVariable(value = "project_id")Long projectId,
                                            @ApiParam(value = "用户名称") @RequestParam(value = "username", required = false) String username,
                                            @ApiParam(value = "仓库名称") @RequestParam(value = "repository", required = false) String repository,
                                            @ApiParam(value = "标签号") @RequestParam(value = "tag", required = false) String tag,
                                            @ApiParam(value = "操作类型") @RequestParam(value = "operation", required = false) String operation,
                                            @ApiParam(value = "开始时间") @RequestParam(value = "begin_timestamp", required = false) String beginTimestamp,
                                            @ApiParam(value = "结束时间") @RequestParam(value = "end_timestamp", required = false) String endTimestamp,
                                            @ApiParam(value = "页号") @RequestParam(value = "page", required = false) Integer page,
                                            @ApiParam(value = "页大小") @RequestParam(value = "page_size", required = false) Integer pageSize){
        return BeanUtils.convertList(projectLogsService.getProjectLogs(projectId,username,repository,tag,operation,beginTimestamp,endTimestamp,page,pageSize),AccessLogVO.class);
    }
}