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
    // Getter and Setters
    // getter for the id
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id =id;
    }
    // getter for the name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    // returning the email
    public  Email getEmail(){
        return email;
    }

    public void setEmail(Email email){
        this.email = email;
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
        return String.format("Member[ID=%s, Name=%s, Email=%s, Status=%s]",
                id, name, email.getValue(), status);
    }
}
