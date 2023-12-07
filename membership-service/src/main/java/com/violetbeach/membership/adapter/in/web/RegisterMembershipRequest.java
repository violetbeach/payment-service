package com.violetbeach.membership.adapter.in.web;

public record RegisterMembershipRequest(
    String name,
    String address,
    String email,
    boolean isCorp
) {
}
