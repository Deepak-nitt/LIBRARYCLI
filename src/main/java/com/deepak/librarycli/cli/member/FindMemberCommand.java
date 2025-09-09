package com.deepak.librarycli.cli.member;

import com.deepak.librarycli.model.Member;
import com.deepak.librarycli.service.MemberService;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "find", description = "Find members by id, name, or email")
public class FindMemberCommand implements Runnable {

    @CommandLine.ParentCommand
    private MemberCommands parent;

    @CommandLine.Option(names = "--id" , description = "Search by member ID")
    private String id;

    @CommandLine.Option(names = "--name" , description = " Search by member name ")
    private String name;

    @CommandLine.Option(names = "--email" , description = "Search by member email")
    private String email;

    @Override
    public void run() {
        try {
            if (id != null) {
                parent.getMemberService().searchById(id)
                        .ifPresentOrElse(
                                System.out::println,
                                () -> System.out.println("No member found with ID: " + id));
            } else if (name != null) {
                List<Member> members = parent.getMemberService().searchByName(name);
                if (members.isEmpty()) {
                    System.out.println("No members found with name containing: " + name);
                } else {
                    members.forEach(System.out::println);
                }
            } else if (email != null) {
                List<Member> members = parent.getMemberService().searchByEmail(email);
                if (members.isEmpty()) {
                    System.out.println("No members found with email containing: " + email);
                } else {
                    members.forEach(System.out::println);
                }
            } else {
                System.out.println("Provide at least one of --id, --name, or --email to search.");
            }
        } catch (Exception e) {
            System.err.println("Error finding member: " + e.getMessage());
        }
    }
}
