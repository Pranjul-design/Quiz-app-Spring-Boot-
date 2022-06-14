package com.usersData;


import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<UserBean, Integer> {

}
