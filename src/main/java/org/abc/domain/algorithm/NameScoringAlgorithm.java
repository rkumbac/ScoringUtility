package org.abc.domain.algorithm;

import org.abc.domain.model.Name;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@FunctionalInterface
public interface NameScoringAlgorithm {

    // Read the input file and return the first row record
    default String readFile(String fileName) {

        String firstLine = null;

        try {
            Path path = Paths.get(fileName);
            firstLine = Files.readAllLines(path).get(0);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return firstLine;
    }

    // Extracts Name from the comma-separated quoted string names and return as a List
    default  List<Name> parseNames(String names, Supplier<Comparator<Name>> supplier) {
        // Extract names from the line to a list and sort by name
        List<Name> list = Arrays.asList(names.split(","))
                .stream()
                .map(x -> x.replace("\"", "").toUpperCase())
                .map(x -> getName(x))
                .sorted(supplier.get())
                .collect(Collectors.toList());

        return list;
    }

    default  List<Name> parseNames(String names) {
        return parseNames(names, () -> Comparator.comparing(Name::getFullName));
    }

    default Name getName(String name) {
        Name n = new Name();
        String[] names = name.split(" ");

        switch(names.length) {
            default:

            case 2:
                n.setLastName(names[1]);

            case 1:
                n.setFirstName(names[0]);

            case 0:
                break;

        }

        return n;
    }

    BigInteger calcScore(final String fileName)
            throws IOException;
}
