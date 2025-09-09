package com.deepak.librarycli.service;

import com.deepak.librarycli.model.Email;
import com.deepak.librarycli.model.Member;
import com.deepak.librarycli.model.MemberStatus;
import com.deepak.librarycli.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for member business logic.
 */
public class MemberService {
    private final MemberRepository memberRepo;
    private int memberCounter = 1; // for generating IDs like M0001

    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    // Register a Member
    public Member registerMember(String name, String emailValue) {
        if(name == null || emailValue==null){
            throw new IllegalArgumentException("Name or Email cannot be null");
        }
        String id = String.format("M%04d", memberCounter++);
        Member member = new Member(id, name, new Email(emailValue));
        member.setStatus(MemberStatus.ACTIVE);
        memberRepo.save(member);
        return member;
    }

    // update the member info
    public boolean updateMember(String id, String name, String email) {
        Member existing = memberRepo.findById(id).orElse(null);
        if (existing == null) return false;

        if (name != null) existing.setName(name);
        if (email != null) existing.setEmail(new Email(email));

        memberRepo.update(id, existing);
        return true;
    }

    // delete the member
    public boolean deleteMember(String id){
        return memberRepo.delete(id);
    }

    // List all members
    public List<Member> listMembers() {
        return memberRepo.findAll();
    }

    // search member by the ids
    public Optional<Member> searchById(String id){
        return memberRepo.findById(id);

    }

    // search by Name
    public List<Member> searchByName(String name){
        return memberRepo.findAll().stream()
                .filter(m->m.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    // search by the email

    public List<Member> searchByEmail(String email){
        return memberRepo.findAll().stream()
                .filter(m->m.getEmail().getValue().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Deactivate the member
    public boolean deactivateMember(String id) {
        Optional<Member> memberOpt = memberRepo.findById(id);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (member.getStatus() != MemberStatus.INACTIVE) {
                member.setStatus(MemberStatus.INACTIVE);
                memberRepo.save(member);
                return true;
            }
        }
        return false;
    }

    // Activate the member
    public boolean activateMember(String id) {
        Optional<Member> memberOpt = memberRepo.findById(id);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (member.getStatus() != MemberStatus.ACTIVE) {
                member.setStatus(MemberStatus.ACTIVE);
                memberRepo.save(member);
                return true;
            }
        }
        return false;
    }

    // List only active members
    public List<Member> listActiveMembers(){
        return memberRepo.findAll().stream()
                .filter(m->m.getStatus() == MemberStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    // List only Inactive members
    public List<Member> listInactiveMembers(){
        return memberRepo.findAll().stream()
                .filter(m->m.getStatus()==MemberStatus.INACTIVE)
                .collect(Collectors.toList());
    }
}
