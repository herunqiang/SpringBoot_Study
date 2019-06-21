package com.example.demo.functionalInterface;
@FunctionalInterface
public interface TestInterface <T>{
    default void testt(){
        System.out.println("stestts");
    }

    public  void msg(String t);

}
