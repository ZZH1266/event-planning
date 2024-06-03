package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.NoticeReceptions;
import com.software.eventplanning.entity.Notices;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticesMapper extends BaseMapper<Notices> {

    @Select("select * from notification_logs where sent_to=#{userId}")
    List<Notices> selectAllbyUserId(@Param("userId") int userId);

    @Insert("insert into notification_receptions (reception_id,log_id,user_id) values (#{receptionId},#{logId},#{userId})")
    int addReception(NoticeReceptions noticeReceptions);
}
