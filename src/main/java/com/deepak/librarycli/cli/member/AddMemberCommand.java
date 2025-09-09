package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.model.Member;
import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Add/Register a new member")
public class AddMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private  MemberCommands parent;

    @CommandLine.Option(names = "--name", required = true)
    private String name;

    @CommandLine.Option(names = "--email", required = true)
    private String email;

    @Override
    public void run() {
        try {
            Member member = parent.getMemberService().registerMember(name, email);
            System.out.println("Member added successfully: " + member);
        } catch (Exception e) {
            System.err.println("Error adding member: " + e.getMessage());
        }
    }
}

