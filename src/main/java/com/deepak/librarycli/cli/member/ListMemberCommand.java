package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.model.Member;
import com.deepak.librarycli.model.MemberStatus;
import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "list", description = "List all members")
public class ListMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private MemberCommands parent;

    @CommandLine.Option(names = "--status", description = "Filter by status: ACTIVE/INACTIVE")
    private MemberStatus status;

    @Override
    public void run() {
        try {
            List<Member> members;
            if (status == null) {
                members = parent.getMemberService().listMembers();
            } else if (status == MemberStatus.ACTIVE) {
                members = parent.getMemberService().listActiveMembers();
            } else {
                members = parent.getMemberService().listInactiveMembers();
            }

            if (members.isEmpty()) {
                System.out.println("No members found.");
            } else {
                members.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Error listing members: " + e.getMessage());
        }
    }
}