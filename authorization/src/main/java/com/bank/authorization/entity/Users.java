package com.bank.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Byte id;

   @Column(name = "role")
   private String role;

   @Column(name = "profile_id")
   private Byte profileId;

   @Column(name = "password")
   private String password;

}
