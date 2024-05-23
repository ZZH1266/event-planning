package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ParticipantsDTO;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.service.IParticipantService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Resource
    private IParticipantService participantService;

    /**
     * 分页查询
     *
     * @param pageNum    查询的开始页
     * @param pageSize   查询的页数
     * @param activityId 组织者的活动id
     * @param role       根据角色查询
     * @return           返回参与者的信息、total：总共的页数、current：当前页数等数据
     */
    @GetMapping("/page")
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


}
