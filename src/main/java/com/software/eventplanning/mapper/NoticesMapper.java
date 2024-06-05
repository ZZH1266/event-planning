package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.NoticeReceptions;
import com.software.eventplanning.entity.Notices;
import com.software.eventplanning.entity.NoticesInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticesMapper extends BaseMapper<Notices> {

    @Select("select a.*,t.* from notification_logs As a join notification_templates As t On a.template_id=t.template_id where a.sent_to=#{userId}")
    List<NoticesInfo> getnoticesInfoByUserId(@Param("userId") int userId);

    @Insert("insert into notification_receptions (reception_id,log_id,user_id) values (#{receptionId},#{logId},#{userId})")
    int addReception(NoticeReceptions noticeReceptions);

    @Select("select a.*,t.* from notification_logs As a join notification_templates As t On a.template_id=t.template_id where a.log_id=#{logId}")
    NoticesInfo getnoticesInfoByLogId(Integer logId);

    @Select("select * from notification_logs where sent_by=#{userId}")
    List<Notices> getnoticesByUserId(Integer userId);

    @Select("SELECT nr.*\n" +
            "FROM notification_receptions nr\n" +
            "JOIN notification_logs nl ON nr.log_id = nl.log_id\n" +
            "WHERE nl.sent_by =#{userId} ")
    List<NoticeReceptions> getreceptionsByUserId(Integer userId);
}
