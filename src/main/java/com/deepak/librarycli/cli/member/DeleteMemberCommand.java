package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

@CommandLine.Command(name = "delete", description = "Delete a member")
public class DeleteMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private MemberCommands parent;

    @CommandLine.Option(names = "--id", required = true , description = "Member ID to delete")
    private String id;

    @Override
    public void run() {
        try {
            boolean deleted = parent.getMemberService().deleteMember(id);
            if (deleted) {
                System.out.println("Member deleted successfully: " + id);
            } else {
                System.out.println("Member not found with ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error deleting member: " + e.getMessage());
        }
    }
}
