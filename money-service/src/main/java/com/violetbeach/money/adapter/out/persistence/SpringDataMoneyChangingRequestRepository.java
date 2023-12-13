package com.violetbeach.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataMoneyChangingRequestRepository extends JpaRepository<MoneyChangingRequestJpaEntity, Long> {
}