//package com.projectA1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.projectA1.repository.OwnerRepository;
//import com.projectA1.repository.UserRepository;
//
//import jakarta.transaction.Transactional;
//
//
//@Component
//public class PasswordEncryptionInitializer implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository; // UserRepository 주입
//
//    @Autowired
//    private OwnerRepository ownerRepository; // OwnerRepository 주입
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder; // BCryptPasswordEncoder 주입
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        // 사용자의 비밀번호를 암호화하여 업데이트
//        userRepository.findAll().forEach(user -> {
//            String encryptedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encryptedPassword);
//            userRepository.save(user);
//        });
//
//        // 소유자의 비밀번호를 암호화하여 업데이트
//        ownerRepository.findAll().forEach(owner -> {
//            String encryptedPassword = passwordEncoder.encode(owner.getPassword());
//            owner.setPassword(encryptedPassword);
//            ownerRepository.save(owner);
//        });
//    }
//}
