package com.violetbeach.money.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    Optional<MemberMoneyJpaEntity> findByMembershipId(Long MembershipId);
}