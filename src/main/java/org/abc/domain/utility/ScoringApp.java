package org.abc.domain.utility;

import org.abc.domain.service.NameScoringService;

import java.io.IOException;
import java.math.BigInteger;

public class ScoringApp {

    public static void main(String args[]) throws IOException {
        // The command line utility uses only one argument which is the full input file name path
        // Commands without input file name or more than one argument is considered invalid
        if(args.length == 1) {
            String fileName = args[0];
            BigInteger score = new NameScoringService().computeScore(fileName);
            System.out.println("Input File Name = " + fileName);
            System.out.println("Score = " + score);
        } else {
            System.out.println("Invalid command! Full Path of the InputFileName must be passed as a command line argument");
        }
    }
}
