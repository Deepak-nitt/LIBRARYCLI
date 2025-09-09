package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

@CommandLine.Command(name = "activate", description = "Activate a member")
public class ActivateMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private MemberCommands parent;

    @CommandLine.Option(names = "--id", required = true, description = "ID of the member to activate")
    private String id;

    @Override
    public void run() {
        try {
            boolean success = parent.getMemberService().activateMember(id);
            if (success) {
                System.out.println("Member activated successfully: " + id);
            } else {
                System.out.println("Member not found or already active: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error activating member: " + e.getMessage());
        }
    }
}

