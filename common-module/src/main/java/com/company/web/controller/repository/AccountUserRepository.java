package com.company.web.controller.repository;

import com.company.web.controller.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountUserRepository extends JpaRepository<AccountUser, Long>, JpaSpecificationExecutor<AccountUser> {

	public AccountUser findOneByMobile(String mobile);
}
