package com.violetbeach.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {

    MemberMoneyJpaEntity getByMembershipId(Long MembershipId);
}