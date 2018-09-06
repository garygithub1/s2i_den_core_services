package ca.on.gov.s2i.denservice.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    protected static MockMvc mockMvc;

    protected static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    
    @Autowired
    protected WebApplicationContext context;
    
    protected static String staticToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJpc3MiOiJodHRwczovL29udGFyaW8uY2EiLCJ0eElkIjoiMjAxNjYxNDgwMDI0IiwiZXhwIjoxNTMzNjY4OTQ1fQ.shCp9NEMV_mgBU59w5Orn4-EKt4foKu_hWpKz5RMFtVe5ITMXQodkTuqXAjRQSsnAFo0c0F1kNClviG3I5beLw";

    /**
     * setup.
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        
        //TODO update token
    }
    
    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

	//@Test
	public void testExample() throws Exception {
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("postCode", "M2J2V5");

        //.andExpect(status().isOk())
        //.andExpect(status().is(500))

        MvcResult result = mockMvc.perform(get("/v1/subscription/dl/dl01?postCode=M2J2V5")
                .contentType(APPLICATION_JSON_UTF8)
                .header("Authorization", staticToken)
                .content(convertObjectToJsonBytes(reqMap)))
                .andExpect(status().is(500))
                .andDo(print())
                .andReturn();
        
        String content = result.getResponse().getContentAsString();
        
        assertTrue("This will succeed.", content.indexOf("code-101")>0);
	}

    @Test
    public void testExample1() throws Exception {
        int i=0;
        i++;
        
        assertTrue("This will succeed.", i==1);
    }
}
