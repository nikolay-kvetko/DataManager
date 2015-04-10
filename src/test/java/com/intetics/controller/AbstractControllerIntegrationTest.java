package com.intetics.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration("file:src/test/resources/spring/test-root-context.xml"),
        @ContextConfiguration("file:src/main/resources/spring/servlet-context.xml")
})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractControllerIntegrationTest {

    @Autowired
    protected WebApplicationContext wac;
}