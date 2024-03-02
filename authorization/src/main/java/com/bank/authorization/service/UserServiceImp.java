package com.bank.authorization.service;

import com.bank.authorization.dto.UsersDTO;
import com.bank.authorization.entity.Users;
import com.bank.authorization.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   @Transactional(readOnly = true)
   public Users findByRole(String role) {
      return userRepository.findByRole(role);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Users> getAllUsers() {
      return userRepository.findAll();
   }

   @Override
   @Transactional(readOnly = true)
   public Users findById(Byte id) {
      return userRepository.findById(id)
              .orElse(null);
   }

   @Override
   @Transactional
   public void createOrUpdateUser(Users user) {
      if ((user.getId() == null) ||
              (!user.getPassword().equals(userRepository.findById(user.getId()).get().getPassword()))) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      userRepository.save(user);
   }

   @Override
   @Transactional
   public void deleteUser(Byte id) {
      userRepository.deleteById(id);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UsersDTO userDTO = userRepository.findByUsername(username);
      if(userDTO == null) {
         throw new UsernameNotFoundException(String.format("user '%s' not found", username));
      }

      return new org.springframework.security.core.userdetails.User(
              userDTO.getUsername(),
              userDTO.getPassword(),
              userDTO.isAccountNonExpired(),
              userDTO.isCredentialsNonExpired(),
              userDTO.isEnabled(),
              userDTO.isAccountNonLocked(),
              userDTO.getRoles());

   }
}
