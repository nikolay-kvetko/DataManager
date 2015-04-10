package com.intetics.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class EntityInstanceControllerIntegrationTest extends AbstractControllerIntegrationTest {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void testAddInstance() throws Exception {
//        this.mockMvc.perform(get("/entity/edit/1"))
//                .andExpect(content().string("Obtained 'entitySchemaId' path variable value '1'"));
        this.mockMvc.perform(
                post("/entity/1/instance/new")
                        .param("1", "Anton")
                        .param("2", "Chervyakovsky")
                        .param("3", "java")
                        .param("3", "java script")
                     )
                .andDo(print());
//                .andExpect(status().isFound())
//                .andExpect(redirectedUrl("/entity/1/instance/list"));
               /* .andExpect(flash().attribute("message",
                        "Form submitted successfully.  Bound properties name='Joe', age=56, " +
                                "birthDate=Tue Dec 16 00:00:00 " + timezone + " 1941, phone='(347) 888-1234', " +
                                "currency=123.33, percent=0.89, inquiry=comment, inquiryDetails='what is?'," +
                                " subscribeNewsletter=false, additionalInfo={java=true, mvc=true}"));*/
    }
}