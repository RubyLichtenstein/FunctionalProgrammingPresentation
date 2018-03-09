package com.rubylich.exmple;

/**
 * Created by rl98880 on 02/03/2018.
 */

public class EitherExampleJava {
    int parse(String s) {
        if (s.matches("-?[0-9]+"))
            return Integer.parseInt(s);

        else throw new NumberFormatException(s + " is not a valid integer.");
    }

    double reciprocal(int i) {
        if (i == 0)
            throw new IllegalArgumentException("Cannot take reciprocal of 0.");

        else return 1.0 / i;
    }

    String stringify(double d) {
        return Double.toString(d);
    }

    String magic(String s) {
        return stringify(reciprocal(parse(s)));
    }

    public void consume() {
        try {
            //i don't know what the error that may happened
            //the compiler not tell me about that
            //also you don't have easy way to differ between the errors cant
            //what if other exception happen?
            //the code not tell me to much
            magic("2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
