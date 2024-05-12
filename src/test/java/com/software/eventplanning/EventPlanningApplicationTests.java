package com.software.eventplanning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class EventPlanningApplicationTests {

    @Resource
    DataSource dataSource;
    @Test
    public void connetionTest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
