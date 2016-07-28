package io.crowdcode.speedbay.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.crowdcode.speedbay.product.ProductServiceApplication;
import io.crowdcode.speedbay.product.model.Product;
import io.crowdcode.speedbay.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductServiceApplication.class)
@WebAppConfiguration
//@Configuration
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

//    @Bean
//    public ProductService productService() {
//        return Mockito.mock(ProductService.class);
//    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateProducts() throws Exception {

        when(productService.createProduct(any(),any(),any())).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .content("{\"uuid\":\"1\",\"title\":\"x\"}"))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(productService.createProduct(any(),any(),any()),atLeastOnce());
    }

    @Test
    public void testCreateInvalidProducts() throws Exception {

        when(productService.createProduct(any(),any(),any())).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"title\":\"x\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(productService, never()).createProduct(any(),any(),any());
    }


    @Test
    public void testController() throws Exception {
        ProductController productController = new ProductController();

        ProductService productService = Mockito.mock(ProductService.class);
        productController.setProductService(productService);

        when(productService.findAllProducts()).thenReturn(Collections.emptyList());

        List<Product> products = productController.list();

        assertNotNull(products);
    }


    private String asJsonString(Object value) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(value);
        System.out.println(json);
        return json;
    }
}