package com.saha.amit;

import org.springframework.data.jpa.repository.Query;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        ArrayList<Taata> taatas = new ArrayList<>();
        Taata taata = new Taata("land", 8);
        Taata taata1 = new Taata("lasun",8);
        taatas.add(taata);
        taatas.add(taata1);
        System.out.println(taatas);
        taatas.forEach(taata2 -> {
            taata2.alu ="Land Lasun";
            taata2.i =99;
        });

        System.out.println(taatas);
    }
}
class Taata {
    public String alu;
    public int i;

    public Taata(String alu, int i) {
        this.alu = alu;
        this.i = i;
    }

    @Override
    public String toString() {
        return "Taata{" +
                "alu='" + alu + '\'' +
                ", i=" + i +
                '}';
    }
}