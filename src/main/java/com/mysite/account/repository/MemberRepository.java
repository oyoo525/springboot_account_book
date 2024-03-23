package com.mysite.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mysite.account.domain.Member;

import jakarta.transaction.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	

}
