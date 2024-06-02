package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ParticipantsDTO;
import com.software.eventplanning.entity.ParticipantApplications;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.service.IActivitiesService;
import com.software.eventplanning.service.IParticipantApplicationService;
import com.software.eventplanning.service.IParticipantService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/activity/participant")
public class   ParticipantController {

    @Resource
    private IParticipantService participantService;

    @Autowired
    private IParticipantApplicationService participantAService;

    @Autowired
    private IActivitiesService  activitiesService;
    /**
     * 分页查询
     *
     * @param pageNum    查询的开始页
     * @param pageSize   查询的页数
     * @param activityId 组织者的活动id
     * @param role       根据角色查询
     * @return           返回参与者的信息、total：总共的页数、current：当前页数等数据
     */
    @GetMapping
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer activityId,
                           @RequestParam(defaultValue = "") String role) {
        IPage<Participants> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Participants> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id", activityId);
        wrapper.eq(Strings.isNotEmpty(role), "role", role);
        return Result.success(participantService.page(page, wrapper));
    }


    /**
     * 添加或修改参与者身份
     *
     * @param participantsDTO 参与者的信息
     * @return             返回添加的结果
     */
    @PostMapping
    public Result save(@RequestBody ParticipantsDTO participantsDTO) {
        boolean one = participantService.hasUser(participantsDTO);
        if (!one) {
            return Result.error(-1, "用户不存在,无法添加参与者");
        }
        Participants participants = participantService.convertParticipantsDTOToParticipants(participantsDTO);
        return Result.success(participantService.saveOrUpdate(participants));
    }

    /**
     * 删除参与者
     *
     * @param id 参与者的id
     * @return   返回删除的结果
     */
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(participantService.removeById(id));
    }

    /**
     * 批量删除参与者
     *
     * @param ids 参与者的id
     * @return    返回删除的结果
     */
    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(participantService.removeByIds(ids));
    }

    /**
     * 处理申请
     * 分页查询所有申请
     */
    @GetMapping("/handle")
    public Result showAllApplications(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam Integer activityId,
                                      @RequestParam(defaultValue = "") String username) {
        IPage<ParticipantApplications> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ParticipantApplications> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id", activityId);
        wrapper.eq(Strings.isNotEmpty(username), "username", username);
        wrapper.eq("state", "申请中");
        return Result.success(participantAService.page(page, wrapper));
    }

    /**
     * 处理申请
     * 通过申请
     */
    @PostMapping("/handle/apply")
    public Result applyApplication(@RequestBody ParticipantApplications participantApplication) {
        return Result.success(participantAService.applyApplication(participantApplication));
    }

    /**
     * 处理申请
     * 拒绝申请
     */
    @PostMapping("/handle/reject")
    public Result rejectApplication(@RequestBody ParticipantApplications participantApplication) {
        participantApplication.setState("已拒绝");
        participantAService.updateById(participantApplication);
        return Result.success(participantApplication);
    }
    /**
     * 活动参与者追踪活动进度
     */
    @GetMapping("/statustrack")
    @ResponseBody
    public Result r(@RequestParam Integer activityId) {
       String status=activitiesService.getactivitystatusbyid(activityId);
       return Result.success(status);
    }

}
