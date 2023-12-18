package com.violetbeach.money.adapter.out.web;

record MembershipResponse(
    String membershipId,
    String name,
    String email,
    String address,
    boolean isValid,
    boolean isCorp
) {
}
