package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.LoginDTO;
import com.software.eventplanning.controller.dto.RegisterDTO;
import com.software.eventplanning.entity.User;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.UserMapper;
import com.software.eventplanning.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class EmailServiceImpl extends ServiceImpl<UserMapper, User> implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;

    @Value("${spring.mail.username}")
    private String from;

    private static final Log LOG = Log.get();

    @Override
    public boolean sendEmail(String email, HttpSession session) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码邮件");//邮件标题
            //生成随机验证码
            String code = randomCode();

            //将验证码存入session
            session.setAttribute("code", code);
            session.setAttribute("email", email);

            mailMessage.setText("您的验证码是：" + code); //邮件内容
            mailMessage.setTo(email); //收件人
            mailMessage.setFrom(from); //发件人
            mailSender.send(mailMessage); //发送邮件
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 生成随机验证码
     * @return String code
     */
    private String randomCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 验证验证码是否一致
     */
    @Override
    public User registered(RegisterDTO registerDTO, HttpSession session) {
        //获取session中的验证码
        String email = (String) session.getAttribute("email");
        String sessionCode = (String) session.getAttribute("code");

        //获取表单中的提交的验证信息
        String voCode = registerDTO.getCode();

        //判断验证码是否一致
        if (email == null || email.isEmpty()) {
            throw new ServiceException(Constants.CODE_601, "未输入邮箱");
        } else if (!sessionCode.equals(voCode)) {
            throw new ServiceException(Constants.CODE_602, "验证码错误");
        }

        //将用户信息存入数据库
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setRole("user");
        user.setEmail(email);
        userMapper.insert(user);
        return user;
    }
}
