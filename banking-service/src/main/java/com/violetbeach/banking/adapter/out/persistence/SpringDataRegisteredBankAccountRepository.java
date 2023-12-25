package com.violetbeach.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataRegisteredBankAccountRepository extends
    JpaRepository<RegisteredBankAccountJpaEntity, Long> {

    RegisteredBankAccountJpaEntity getByMembershipId(String membershipId);
}