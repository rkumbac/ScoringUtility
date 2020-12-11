package org.abc.domain.service;

import org.abc.domain.algorithm.NameScoringAlgorithmImpl;

import java.io.IOException;
import java.math.BigInteger;

public class NameScoringService {

    public BigInteger computeScore(String fileName) throws IOException {
        return (new NameScoringAlgorithmImpl().calcScore(fileName));
    }
}
