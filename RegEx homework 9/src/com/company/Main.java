package com.company;

public class Main {

    public static void main(String[] args) {

        RegEx regEx = new RegEx();
        System.out.println(regEx.findWordsByLetter('о'));
        System.out.println(regEx.findSentencesWithOneWord());
        System.out.println(regEx.checkMobileNumber("+375291233214, 375331233212, +375(25)4321232, +375(29) 123-32-12, +375(29) 123 32 13"));
        System.out.println(regEx.dateFinder("dfdfdf2019.12.14 17:34dfdffd"));
        System.out.println(regEx.webSiteChecker("www.google.com или https://www.yandex.ru или tut.by или www.onliner.by"));
    }
}
