package com.pathus.kata.lambda.stream;

import java.util.*;
import java.util.stream.Collectors;

public final class Util {

    private static final String COMMA = ", ";
    private static final String NAMES = "Names: ";
    private static final String DOT = ".";
    private static final int ADULT_AGE = 18;

    private Util(){

    }

    public static List<String> mapToUppercase(List<String> input) {
        return input.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static List<String> removeElementsWithMoreThanFourCharacters(List<String> input) {
        return input.stream().filter(s -> s.length() < 4).collect(Collectors.toList());
    }

    public static List<String> sortStrings(List<String> input) {
        return input.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    public static List<Integer> sortIntegers(List<String> input) {
        return input.stream().map(s -> Integer.parseInt(s))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public static List<Integer> sortIntegersDescending(List<String> input) {
        return sortIntegers(input).stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());    }

    public static Integer sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static List<String> flattenToSingleCollection(List<List<String>> input) {
        return input.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static String separateNamesByComma(List<Person> input) {
        String names =  input.stream().
                map(Person::getName)
                .collect(Collectors.joining(COMMA));

        return new StringBuilder(NAMES).append(names).append(DOT).toString();
    }

    public static String findNameOfOldestPerson(List<Person> input) {
       return input.stream().max(Comparator.comparingInt(Person::getAge)).map(Person::getName) .orElse(null);
    }

    public static List<String> filterPeopleLessThan18YearsOld(List<Person> input) {
        return input.stream().filter(person -> person.getAge() < ADULT_AGE)
                .map(Person::getName).collect(Collectors.toList());
    }

    public static IntSummaryStatistics getSummaryStatisticsForAge(List<Person> input) {
      return input.stream().collect(Collectors.summarizingInt(Person::getAge));
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> input) {
        return input.stream().collect(Collectors.partitioningBy(person -> person.getAge() > ADULT_AGE));   }

    public static Map<String, List<Person>> partitionByNationality(List<Person> input) {
        return input.parallelStream().collect(
                Collectors.groupingBy(Person::getCountry));   }
}
