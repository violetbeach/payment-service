package com.violetbeach.membership.adapter.in.web;

import com.violetbeach.membership.application.port.in.RegisterMembershipCommand;
import com.violetbeach.membership.application.port.in.RegisterMembershipUseCase;
import com.violetbeach.membership.domain.Membership;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/membership")
@RequiredArgsConstructor
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping
    Membership registerMembership(@RequestBody RegisterMembershipRequest request) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
            .name(request.name())
            .address(request.address())
            .email(request.email())
            .isValid(true)
            .isCorp(request.isCorp())
            .build();

        return registerMembershipUseCase.registerMembership(command);
    }

}
