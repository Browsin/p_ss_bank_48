package com.bank.authorization.service;

import com.bank.authorization.dto.UsersDTO;
import com.bank.authorization.entity.Users;
import com.bank.authorization.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
   public Users findByProfileId(Byte profileId) {
      return userRepository.findByProfileId(profileId);
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
      Users user = userRepository.findByProfileId(Byte.valueOf(username));

      if(user == null) {
         throw new UsernameNotFoundException(String.format("user '%s' not found", username));
      }

      UsersDTO userDTO = new UsersDTO();
      userDTO.setUsername(username);
      userDTO.setPassword(user.getPassword());
      userDTO.setRole(user.getRole());
      userDTO.setProfileId(user.getProfileId());
      userDTO.setAuthority(new SimpleGrantedAuthority(userDTO.getRole()));
      userDTO.setRoles(new ArrayList<>(Collections.singleton(userDTO.getAuthority())));

      return new org.springframework.security.core.userdetails.User(
              userDTO.getUsername(),
              userDTO.getPassword(),
              userDTO.getAuthorities());
   }

}
