package com.hspedu.hspliving;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-04-29 10:55
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试 streamApi / 流式计算
 */

public class TestStream {

    public static void main(String[] args) {
        Person a = new Person(1, "a", 22);
        Person b = new Person(2, "b", 23);
        Person c = new Person(3, "c", 24);
        Person d = new Person(4, "d", 25);
        Person e = new Person(5, "e", 26);

        List<Person> list = Arrays.asList(a,b,c,d,e);
        System.out.println(list);


        //List.stream():把List转成流对象，目的是为了使用流的方法  ==》 可以完成一些复杂的业务

        Stream<Person> stream = list.stream();

        //1.stream 的 filter 方法
        //2.filter 传入的是Predicate,这个断言返回boolean
        //3.collect()传入Collector,将数据收集到集合
        //4.map操作：希望给过滤得到的person.对象加入cat对象 ，会影响到原来的 list 对象
        //5. sorted :排序传入的是Comparator
        List<Person> collect = stream.filter(person -> {
            return person.getId() > 3;
        }).map(person -> {
            person.setCat(new Cat(person.getId()+100,"狸花猫","花色"));
            return person;
        }).sorted((p1,p2) -> {
            return p1.getId() - p2.getId();
        }).collect(Collectors.toList());


        System.out.println(collect);


    }

}

class Person{
    private Integer id;
    private String name;
    private Integer age;
    private Cat cat;

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cat=" + cat +
                '}';
    }
}
class Cat{
    private Integer id;
    private String name;
    private String color;

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public Cat(Integer id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}