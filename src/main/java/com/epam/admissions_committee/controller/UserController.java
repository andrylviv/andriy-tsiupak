package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Log4j2
public class UserController {
    private final UserService userService;

    @ApiOperation("Get users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers");
        return userService.listUsers();
    }

    @ApiOperation("Get user by email")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    public UserDto getUser(@PathVariable String email) {
        log.info("getUser by email {}", email);
        return userService.getUser(email);
    }

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{email}")
    public UserDto updateUser(@PathVariable("email") String email, @RequestBody  @Valid UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
