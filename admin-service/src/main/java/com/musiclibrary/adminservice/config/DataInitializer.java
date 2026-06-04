package com.musiclibrary.adminservice.config;

import com.musiclibrary.adminservice.entity.Admin;
import com.musiclibrary.adminservice.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!adminRepository.existsByEmail("admin@musiclibrary.com")) {
            adminRepository.save(Admin.builder()
                    .name("Super Admin")
                    .email("admin@musiclibrary.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Admin.Role.ROLE_ADMIN)
                    .build());
            System.out.println("Admin created: admin@musiclibrary.com / admin123");
        }

        if (!adminRepository.existsByEmail("aayush@musiclibrary.com")) {
            adminRepository.save(Admin.builder()
                    .name("Aayush Pandey")
                    .email("aayush@musiclibrary.com")
                    .password(passwordEncoder.encode("aayush123"))
                    .role(Admin.Role.ROLE_ADMIN)
                    .build());
            System.out.println("Admin created: aayush@musiclibrary.com / aayush123");
        }

        if (!adminRepository.existsByEmail("manager@musiclibrary.com")) {
            adminRepository.save(Admin.builder()
                    .name("Music Manager")
                    .email("manager@musiclibrary.com")
                    .password(passwordEncoder.encode("manager123"))
                    .role(Admin.Role.ROLE_ADMIN)
                    .build());
            System.out.println("Admin created: manager@musiclibrary.com / manager123");
        }
    }
}
