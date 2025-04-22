package com.example.spring_article_java_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_article_java_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //基本的な動作はjparepositoryから継承    
}
