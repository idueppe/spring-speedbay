package io.crowdcode.speedbay.imagestore.controller;

import io.crowdcode.speedbay.imagestore.ImageStoreServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ImageStoreServiceApplication.class)
@WebAppConfiguration
public class ImageStoreControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ImageStoreControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        log.info("Setup tests ...");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testPostingAImage() throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/images/logo.png");
        assertNotNull(inputStream);
        String parameterName = "file";
        String originalFilename = "logo.png";
        String contentType = MediaType.MULTIPART_FORM_DATA_VALUE;

        MockMultipartFile multipartFile =
                new MockMultipartFile(parameterName, originalFilename, contentType, inputStream);

        String ownerUuid = UUID.randomUUID().toString();

        this.mockMvc.perform(
                MockMvcRequestBuilders.fileUpload("/api/{ownerUuid}", ownerUuid).file(multipartFile))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/baa1881ee2133f72ed8b9e87d012dc761ee0decc"));
    }
}