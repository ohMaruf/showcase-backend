package com.maruf.showcase.web;

import com.maruf.showcase.businesslogic.exceptions.ResourceNotFoundException;
import com.maruf.showcase.businesslogic.services.ProductService;
import com.maruf.showcase.dto.ProductSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

  private final ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping("/{productId}")
  public ProductSummaryDto getAverageRating(@PathVariable Integer productId) throws ResourceNotFoundException {
    return service.getProductById(productId);
  }
}
