package com.saha.amit;

import com.github.javafaker.Faker;
import com.saha.amit.dto.AddressDto;
import com.saha.amit.model.Address;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static Faker faker = new Faker();
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        for (int j =0; j <5; j++){
            stringList.add(faker.howIMetYourMother().catchPhrase());
        }
        System.out.println(stringList);
        addList(stringList);
        System.out.println(stringList);

        Person person = new Person();
        person.name = faker.funnyName().name();
        person.age = 18;
        System.out.println(person.toString());
        change(person);
        System.out.println(person);
    }

    static List<String> addList(List<String> list){
        list.add(faker.gameOfThrones().quote());
        return list;
    }

    static Person change(Person person){
        person.name = "Leura";
        person.age =79;
        return person;
    }
}

class Person{
    public String name;
    public int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
