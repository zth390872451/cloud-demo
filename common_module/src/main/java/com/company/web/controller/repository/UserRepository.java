package com.company.web.controller.repository;

import com.company.web.controller.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<AccountUser, Long>, JpaSpecificationExecutor<AccountUser>{

    AccountUser findOneByLoginName(String loginName);

    AccountUser findOneByMobile(String mobile);

}
