package com.example.demo.functionalInterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestInterfaceTest {

    @Test
    public void testt() {
        TT tt = new TT();
        List<String> sourceList = Arrays.asList("Java8", "new", "feature", "Stream", "API");
        Util.test(sourceList,TT::staticMethod);  //静态方法：类名引用
        Util.test(sourceList,tt::instanceMethod);  //实例方法：实例对象引用
        Runnable aNew = TT::new;   //构造方法
    }
    @Test
    public void test2() {

        List<TT> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new TT());
        }
        //list.stream().forEach(Util::msg);
    }

    @Test
    public void testStream() {
        String[] strArr = {"Java8", "new", "feature", "Stream", "API"};
        Stream.of(strArr).filter(s -> s.length() < 4).forEach(System.out::println);
//        Arrays.asList(strArr).parallelStream().filter(w -> w.length() > 3).forEach(System.out::println);
    }

    /**
     * 这个是将数据流元素按照逻辑映射成输出流的元素，函数引用  ：：
     */
    @Test
    public void testStreamMap1() {

        String[] strArr = {"Java8", "new", "feature", "Stream", "API"};
        List<String> list = Arrays.asList(strArr);
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        // output.forEach(System.out::println);
        System.out.println("---------------");
        list.forEach(System.out::println);
//        Arrays.asList(strArr).parallelStream().filter(w -> w.length() > 3).forEach(System.out::println);
    }

    /**
     * 这个是将数据流元素按照逻辑映射成输出流的元素
     */
    @Test
    public void testStreamMap2() {
        Integer[] strArr = {1, 2, 3, 4};
        List<Integer> list = Arrays.asList(strArr);
        list.stream().map(i -> i * i).forEach(System.out::println);
        // output.forEach(System.out::println);
        System.out.println("---------------");
        list.forEach(System.out::println);
//        Arrays.asList(strArr).parallelStream().filter(w -> w.length() > 3).forEach(System.out::println);
    }

    /**
     * 将多个输入流转换成一个输出流  flatMap  然后进行操作
     */
    @Test
    public void testFlatMap() {
        long count = Stream.of(
                Arrays.asList(12, 312, 318, 2376),
                Arrays.asList(231, 32, 318234, 2372346),
                Arrays.asList(172, 31212, 3182, 12)
        ).flatMap(list -> list.stream()).count();
        System.out.println(count);
    }

    /**
     * peek
     */
    @Test
    public void testPeek() {

        String[] strArr = {"Java8", "new", "feature", "Stream", "API"};
        List<String> list = Arrays.asList(strArr);
        Stream.of("Java8")
                .filter(e ->{
                    System.out.println("111");
                    return e.length() > 0;} )
                .peek(e -> {
                    System.out.println("222" + e);
                        } )
                .map(e->{
                    System.out.println("333"+e);
                    return e;
                })
                .forEach(System.out::println);
    }
    /**
     * 局部变量
     */
    @Test
    public void testVariable() {

        String[] strArr = {"Java8", "new", "feature", "Stream", "API"};
        List<String> list = Arrays.asList(strArr);

    }



}

class TT  {


    public static void staticMethod(String s){
        System.out.println(s);
    }


    public  void instanceMethod(String s) {
        System.out.println("实例方法："+s);

    }
}

class Util {
    public static void test(List<String> list ,TestInterface testInterface){
        if (list != null){
            for (String s :
                    list) {
                testInterface.msg(s);
            }
        }
    }

    public void msg(TT tt){

    }
}