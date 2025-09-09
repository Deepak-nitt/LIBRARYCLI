package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

@CommandLine.Command(name = "update", description = "Update a member")
public class UpdateMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private MemberCommands parent;


    @CommandLine.Option(names = "--id", required = true , description = "Member ID to update")
    private String id;

    @CommandLine.Option(names = "--name" , description = "New name for the member")
    private String name;

    @CommandLine.Option(names = "--email" , description =  "New email for the member")
    private String email;

    @Override
    public void run() {
        try {
            boolean updated = parent.getMemberService().updateMember(id, name, email);
            if (updated) {
                System.out.println("Member updated successfully.");
            } else {
                System.out.println("Member not found with ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error updating member: " + e.getMessage());
        }
    }
}

