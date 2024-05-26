package com.software.eventplanning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: zzh
 * @Description: WebSocket配置类
 */
@Configuration
public class WebSocketConfig {
    /**
     * 给spring容器注入一个ServerEndpointExporter对象
     * 相当于xml
     * <beans>
     *     <bean id="serverEndpointExporter" class="org.springframework.web.socket.server.standard.ServerEndpointExporter"/>
     * </beans>
     * <p>
     *     检测到有@ServerEndpoint注解的类就会自动注册这个类为WebSocket的服务端
     * </p>
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
