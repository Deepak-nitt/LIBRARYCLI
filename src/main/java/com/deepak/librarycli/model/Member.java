package com.deepak.librarycli.model;

public class Member {
    private String id;
    private String name;
    private Email email;
    private MemberStatus status;


    public Member(String id , String name , Email email){
        this.id= id;
        this.name=name;
        this.email=email;
        this.status =MemberStatus.ACTIVE;


    }
    // getter for the id
    public String getId(){
        return id;
    }
    // getter for the name;

    public String getName(){
        return name;
    }

    // returning the email
    public  Email getEmail(){
        return email;
    }

    // Returning the status
    public MemberStatus getStatus(){
        return status;
    }

    // setter
    public void setStatus(MemberStatus status){
        this.status =status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s", id, name, email.value(), status);
    }


    public void put(Email email, Member member) {
    }
}
