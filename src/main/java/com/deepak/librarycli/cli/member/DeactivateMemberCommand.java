package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

@CommandLine.Command(name = "deactivate", description = "Deactivate a member")
public class DeactivateMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private MemberCommands parent;

    @CommandLine.Option(names = "--id", required = true, description = "ID of the member to deactivate")
    private String id;
    @Override
    public void run() {
        try {
            boolean success = parent.getMemberService().deactivateMember(id);
            if (success) {
                System.out.println("Member deactivated successfully: " + id);
            } else {
                System.out.println("Member not found or already inactive: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error deactivating member: " + e.getMessage());
        }
    }
}

