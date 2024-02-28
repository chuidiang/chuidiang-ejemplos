package com.chuidiang.ejemplos.regex;

import java.util.regex.Pattern;

/**
 * @author fjabellan 28/02/2024
 */
public class RegexBasicExamples {
    public static void main(String[] args) {
        System.out.println("ABC matches ABC : " + Pattern.matches("ABC", "ABC"));
        System.out.println("ABC contains : " + Pattern.compile("A").matcher("ABC").find());
        System.out.println("ABC".matches("A"));


        System.out.println("AC matches A.C : " + Pattern.matches("A.C", "AC"));
        System.out.println("ABC matches A.C : " + Pattern.matches("A.C", "ABC"));
        System.out.println("A2C matches A.C : " + Pattern.matches("A.C", "A2C"));


        System.out.println("AA matches A{2,4} : " + Pattern.matches("A{2,4}", "A"));
        System.out.println("AA matches A{2,4} : " + Pattern.matches("A{2,4}", "AA"));
        System.out.println("AAA matches A{2,4} : " + Pattern.matches("A{2,4}", "AAA"));
        System.out.println("AAAA matches A{2,4} : " + Pattern.matches("A{2,4}", "AAAA"));
        System.out.println("AAAAA matches A{2,4} : " + Pattern.matches("A{2,4}", "AAAAA"));

        System.out.println("A.C matches A\\.C : " + Pattern.matches("A\\.C", "A.C"));
        System.out.println("ABC matches A\\.C : " + Pattern.matches("A\\.C", "ABC"));

        System.out.println("3 matches [0-9] : " + Pattern.matches("[0-9]", "3"));
    }
}
