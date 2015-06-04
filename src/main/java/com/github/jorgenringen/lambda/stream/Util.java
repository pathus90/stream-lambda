package com.github.jorgenringen.lambda.stream;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Util {

    public static List<String> mapToUppercase(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(toList());
    }

    public static List<String> removeElementsWithMoreThanFourCharacters(List<String> collection) {
        return collection.stream()
                .filter(s -> s.length() < 4)
                .collect(toList());
    }

    public static List<String> sortStrings(List<String> values) {
        return values.stream()
                .sorted(Comparator.naturalOrder())
                .collect(toList());
    }

    public static List<Integer> sortIntegers(List<String> values) {
        return values.stream()
                .map(Integer::valueOf)
                .sorted()
                .collect(toList());
    }

    public static List<Integer> sortIntegersDescending(List<String> values) {
        return values.stream()
                .map(Integer::valueOf)
                .sorted(Comparator.<Integer>reverseOrder())
                .collect(toList());
    }

    public static Integer sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<String> flattenToSingleCollection(List<List<String>> collection) {
        return collection.stream()
                .flatMap(List::stream)
                .collect(toList());
    }

    public static String separateNamesByComma(List<Person> collection) {
        return collection.stream()
                .map(Person::getName)
                .collect(joining(", ", "Names: ", "."));
    }

    public static String findNameOfOldestPerson(List<Person> collection) {
        return collection.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .get()
                .getName();
    }

    public static List<String> filterPeopleLessThan18YearsOld(List<Person> collection) {
        return collection.stream()
                .filter(p -> p.getAge() < 18)
                .map(Person::getName)
                .collect(toList());
    }

    public static IntSummaryStatistics getSummaryStatisticsForAge(List<Person> collection) {
        return collection.stream()
                .mapToInt(Person::getAge)
                .summaryStatistics();
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> collection) {
        return collection.stream()
                .collect(partitioningBy(p -> p.getAge() > 18));
    }

    public static Map<String, List<Person>> partitionByNationality(List<Person> collection) {
        return collection.stream()
                .collect(groupingBy(Person::getCountry));
    }
}
