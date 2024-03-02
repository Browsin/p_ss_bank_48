package com.bank.authorization.repositories;

import com.bank.authorization.dto.UsersDTO;
import com.bank.authorization.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<Users, Byte> {
//    Users findByRole(String role);
//
//    Users findByUsername(Byte username);
    Users findByProfileId(Byte profileId);
}
