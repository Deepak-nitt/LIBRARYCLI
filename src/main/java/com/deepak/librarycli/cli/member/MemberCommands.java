package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "member",
        description = "Manage members",
        subcommands = {
                AddMemberCommand.class,
                ListMemberCommand.class,
                FindMemberCommand.class,
                UpdateMemberCommand.class,
                DeleteMemberCommand.class,
                DeactivateMemberCommand.class,
                ActivateMemberCommand.class
        }
)
public class MemberCommands implements Runnable {

    private final MemberService memberService;

    public MemberCommands(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void run() {
        System.out.println("Use 'member <subcommand> --help' to see available commands.");
    }

    public MemberService getMemberService() {
        return memberService;
    }
}
