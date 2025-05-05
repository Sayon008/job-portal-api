package org.vibe.jobportal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vibe.jobportal.model.Role;
import org.vibe.jobportal.repository.RoleRepository;

@Component
public class RoleDataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if(roleRepository.count() == 0){
            Role userRole = new Role();
            userRole.setRoleValue("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setRoleValue("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
    }
}
