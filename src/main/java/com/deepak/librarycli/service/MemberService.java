package com.deepak.librarycli.service;

import com.deepak.librarycli.model.Member;
import com.deepak.librarycli.model.MemberStatus;
import com.deepak.librarycli.repository.MemberRepository;

import java.util.List;

/**
 * Service for member business logic.
 */
public class MemberService {
    private final MemberRepository memberRepo;
    private int memberCounter = 1; // for generating IDs like M0001

    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    public Member registerMember(String name, String emailValue) {
        String id = String.format("M%04d", memberCounter++);
        Member member = new Member(id, name, new com.deepak.librarycli.model.Email(emailValue));
        member.setStatus(MemberStatus.ACTIVE);
        memberRepo.save(member);
        return member;
    }

    // Find the member
    public List<Member> listMembers() {
        return memberRepo.findAll();
    }

    // Deactivate the member
    public void deactivateMember(String id) {
        Member member = memberRepo.findById(id);
        if (member != null) {
            member.setStatus(MemberStatus.INACTIVE);
            memberRepo.save(member);
        }
    }

    // Activate the member
    public void activateMember(String id) {
        Member member = memberRepo.findById(id);
        if (member != null) {
            member.setStatus(MemberStatus.ACTIVE);
        }
    }
}
