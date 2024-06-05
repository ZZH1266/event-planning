package com.software.eventplanning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.service.IActivitiesService;
import com.software.eventplanning.utils.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;

@RestController
public class ActivityShareController {


    @Autowired
    private IActivitiesService activitiesService;
    //二维码生成接口
    @GetMapping("/generateQRForSharing")
    public ResponseEntity<String> generateQRForSharing(@RequestParam Integer activityId) {
        try {
            Activities one = activitiesService.getactivitiesbyid(activityId);

            //将活动信息JSON序列化
            ObjectMapper mapper = new ObjectMapper();
            String shareContent;
            try {
                shareContent = new String(mapper.writeValueAsString(one));
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "JsonProcessingException");
            }
            // 生成二维码图片字节数组
            byte[] qrCodeBytes = QRCodeGenerator.generateAsByteArray(shareContent, 300, 300);
            // 将字节数组转换为Base64字符串以便于前端显示
            String base64QRCode = Base64.getEncoder().encodeToString(qrCodeBytes);
            return ResponseEntity.ok(base64QRCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate QR Code.");
        }
    }
}
