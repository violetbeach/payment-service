package com.violetbeach.membership.adapter.in.web;

public record ModifyMembershipRequest(
    String membershipId,
    String name,
    String address,
    String email,
    boolean isCorp
) {
}
