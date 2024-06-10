package com.maruf.showcase.web;

import com.maruf.showcase.businesslogic.services.UserService;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.dto.UserDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    List<Country> countries = userService.getOriginCountries();
    return ResponseEntity.ok(countries);
  }

  @PostMapping("/create")
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.createUser(userDto));
  }
}
