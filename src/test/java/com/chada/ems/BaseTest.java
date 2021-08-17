package com.chada.ems;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 16:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmsApplication.class)
public abstract class BaseTest {

    @Before
    public void beforeTest() {

    }

    @After
    public void afterTest() {

    }
}
