package com.example.testdemo.repo;

import com.example.testdemo.domain.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<TokenInfo,Long> {


    List<TokenInfo> findTokenInfoByUserId(int userId);


    List<TokenInfo> findTokenInfoByToken(String token);

}
