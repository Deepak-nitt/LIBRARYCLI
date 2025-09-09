package com.deepak.librarycli.repository;

import com.deepak.librarycli.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private final Map<String , Member> members = new HashMap<>();

    public void save(Member member){
        members.put(member.getId(),member);

    }

    public Member findById(String id){
        return members.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(members.values());
    }

    public  void delete(String id){
        members.remove(id);
    }

    public  boolean existsById(String id){
        return members.containsKey(id);
    }
}
