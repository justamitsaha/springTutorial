package com.saha.amit;

import java.util.Optional;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        new SumOfSquares().calculateSumOfSquares(5);
    }
}

class SumOfSquares {
    public long calculateSumOfSquares(int n) {
        if (n<0){
            return  -1;
        }
        Stream<Integer> st = Stream.iterate(1,s->s+1);
        Optional<Integer> lg = st.limit(n).reduce((a, b)->(int)Math.pow(a,2)+(int)Math.pow(b,2));
        if(lg.isPresent())
            return lg.get();
        else
            return -1;
    }
}
