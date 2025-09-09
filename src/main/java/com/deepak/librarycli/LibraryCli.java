package com.deepak.librarycli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "library-cli",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "CLI app to manage Books, Members, and Loans."


)
public class LibraryCli  implements  Runnable{
    @Override
    public void run(){

    }
}



