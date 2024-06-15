package com.maruf.showcase.boot.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maruf.showcase.boot.builders.ServiceTestDataBuilder;
import com.maruf.showcase.businesslogic.exceptions.ResourceNotFoundException;
import com.maruf.showcase.businesslogic.services.ProductService;
import com.maruf.showcase.dto.ProductSummaryDto;
import com.maruf.showcase.web.controllers.ProductController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ProductController.class)
@Import(ProductController.class)
@TestInstance(Lifecycle.PER_METHOD)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ProductService productService;
  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void givenExistingProductId_whenGetProductSummary_thenReturnProductSummaryAndOk()
      throws Exception {
    ProductSummaryDto expectedDto = ServiceTestDataBuilder.randomProductSummaryDto();
    String expected = objectMapper.writeValueAsString(expectedDto);
    Integer id = 5;
    doReturn(expectedDto).when(productService).getProductById(id);

    String actual = mockMvc.perform(
            get("/v1/api/product/%d".formatted(id)).accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  void givenNonExistingProductId_whenGetProductSummary_thenReturnNotFound() throws Exception {
    Integer id = 5;
    doThrow(new ResourceNotFoundException("Product does not exist")).when(productService).getProductById(id);

    mockMvc.perform(
            get("/v1/api/product/%d".formatted(id)).accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}
