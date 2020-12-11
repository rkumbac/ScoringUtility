package org.abc.domain;

import org.abc.domain.algorithm.NameScoringAlgorithm;
import org.abc.domain.algorithm.NameScoringAlgorithmImpl;
import org.abc.domain.service.NameScoringService;

import java.io.IOException;
import java.math.BigInteger;

public class PlayWithScoringService {

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        String fileName = "C:\\bench\\java\\ScoringUtility\\src\\main\\resources\\names.txt";

        NameScoringService service = new NameScoringService();
        BigInteger score1 = service.computeScore(fileName);

        NameScoringAlgorithm calculator = new NameScoringAlgorithmImpl();
        BigInteger score = calculator.calcScore(fileName);

        System.out.println("Score = " + score1);
    }
}
