package com.deepak.librarycli.repository;

import com.deepak.librarycli.model.Member;

import java.util.*;

public class MemberRepository {
    private final Map<String , Member> members = new HashMap<>();

    // save or add a member
    public void save(Member member){
        if(member ==null || member.getId()==null){
            throw new IllegalArgumentException("Member or ID cannot be null");
        }
        members.put(member.getId(),member);

    }

    // update the existing member
    public boolean update(String id , Member updateMember){
        if(!members.containsKey(id)) return false;
        members.put(id,updateMember);
        return true;
    }
    // find Member by the IDS
    public Optional<Member> findById(String id){
        return Optional.ofNullable(members.get(id));
    }

    // List all the members
    public List<Member> findAll(){
        return new ArrayList<>(members.values());
    }

    // delete a member
    public boolean delete(String id){
        members.remove(id);
        return false;
    }

    // check if member exist  by ID
    public  boolean existsById(String id){
        return members.containsKey(id);
    }
}
