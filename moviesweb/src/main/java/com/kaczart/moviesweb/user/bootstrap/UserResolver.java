package com.kaczart.moviesweb.user.bootstrap;

import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.service.UserRoleService;
import com.kaczart.moviesweb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserResolver implements CommandLineRunner {

    private final UserRoleService userRoleService;
    private final UserService userService;


    @Override
    public void run(String... args) throws Exception {

        userRoleService.buildUserRole();
        userRoleService.buildAdminRole();

        RequestUser adminUser = RequestUser.builder().username("admin").password("admin123").build();
        userService.createAdmin(adminUser);
    }
}
