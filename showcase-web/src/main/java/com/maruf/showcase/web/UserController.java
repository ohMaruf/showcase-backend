package com.maruf.showcase.web;

import com.maruf.showcase.businesslogic.UserService;
import com.maruf.showcase.domain.model.Country;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/origin-countries")
  public ResponseEntity<List<Country>> getAllCountries() {
    List<Country> countries = userService.getAllCountries();
    return ResponseEntity.ok(countries);
  }
}
