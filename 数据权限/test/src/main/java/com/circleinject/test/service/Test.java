package com.circleinject.test.service;

public class Test {
    
    public static void main(String[] args) {
        String str = "\\S+\\(\\d+\\)";
        System.out.println("aaa(1)".matches(str));
    }
}
