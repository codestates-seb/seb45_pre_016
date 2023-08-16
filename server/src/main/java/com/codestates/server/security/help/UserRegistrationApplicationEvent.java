package com.codestates.server.security.help;

import com.codestates.server.user.entity.User;
import com.codestates.server.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
@AllArgsConstructor
public class UserRegistrationApplicationEvent {

    private User user;

}
