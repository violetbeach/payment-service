package com.violetbeach.membership.adapter.in.web;

import com.violetbeach.membership.application.port.in.FindMembershipCommand;
import com.violetbeach.membership.application.port.in.FindMembershipUseCase;
import com.violetbeach.membership.domain.Membership;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@WebAdapter(path = "/membership")
@RequiredArgsConstructor
public class FindMembershipController {
    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable String membershipId){

        FindMembershipCommand command = FindMembershipCommand.builder()
            .membershipId(membershipId)
            .build();
        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }

}
