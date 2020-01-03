package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.CreateUserCommand;
import com.progmasters.moovsmart.service.MailSenderService;
import com.progmasters.moovsmart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private MailSenderService mailSenderService;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(MailSenderService mailSenderService, UserService userService) {
        this.mailSenderService = mailSenderService;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity createUser(@RequestBody CreateUserCommand command) {
        Long id = this.userService.makeUser(command);
        if (id != null) {
            this.mailSenderService.sendMailByRegistration(command.getUserName(), command.getMail(), id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn("Registration was not possible");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/validuser/{id}")
    public ResponseEntity validateUser(@PathVariable("id") Long id) {
        ResponseEntity responseEntity = this.userService.validateUser(id);
        return responseEntity;
    }



}
