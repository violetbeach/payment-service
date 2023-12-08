package com.violetbeach.membership.adapter.in.web;

import com.violetbeach.membership.application.port.in.ModifyMembershipCommand;
import com.violetbeach.membership.application.port.in.ModifyMembershipUseCase;
import com.violetbeach.membership.domain.Membership;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/membership")
@RequiredArgsConstructor
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PutMapping(path = "/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(@RequestBody ModifyMembershipRequest request,
        @PathVariable(name = "membershipId") String membershipId){

        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
            .membershipId(membershipId)
            .name(request.name())
            .address(request.address())
            .email(request.email())
            .isValid(true)
            .isCorp(request.isCorp())
            .build();

        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }

}
