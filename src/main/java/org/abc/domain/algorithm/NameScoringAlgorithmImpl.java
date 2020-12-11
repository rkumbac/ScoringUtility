package org.abc.domain.algorithm;

import org.abc.domain.model.Name;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NameScoringAlgorithmImpl implements NameScoringAlgorithm {

    // Read the first record of the input file
    Function<String, String> readFile = (s) -> readFile(s);

    // Parse name values and return as a List of sorted Name objects
    Function<String, List<Name>> parseNames = (s) -> parseNames(s);

    // Transform into List of names into List of Strings as desired
    Function<List<Name>, List<String>> extractNames = (l) -> l.stream()
            .map(Name::getFullName)
            .collect(Collectors.toList());

    // Item level Alphabetic Sum calculation
    Function<List<String>, List<BigInteger>> sumAlphabeticalValue = (l) -> l.stream()
            .map(String::toCharArray)
            .map(y -> IntStream.range(0, y.length)
                    .mapToObj(z -> y[z] != ' ' ? y[z] - 'A' + 1 : 0)
                    .map(a -> new BigInteger(a.toString()))
                    .reduce(new BigInteger("0"), BigInteger::add)
            )
            .collect(Collectors.toList());

    // Item level Alphabetic Sum multiplied by the index
    Function<List<BigInteger>, List<BigInteger>> multiplyByPosition = (l) ->
            IntStream.range(0, l.size())
                    .mapToObj(i -> new BigInteger(String.valueOf((i+1) * l.get(i).intValue())))
                    .collect(Collectors.toList());

    // Total of all the Item Level scores
    Function<List<BigInteger>, BigInteger> sumAllScores = (l) -> l.stream()
            .reduce(new BigInteger("0"), BigInteger::add);

    @Override
    public BigInteger calcScore(final String fileName)
    throws IOException {
        return readFile
                .andThen(parseNames)
                .andThen(extractNames)
                .andThen(sumAlphabeticalValue)
                .andThen(multiplyByPosition)
                .andThen(sumAllScores).apply(fileName);
    }
}
