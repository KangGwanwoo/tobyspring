package chap1.springbook.learningtest.template;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by daum on 16. 1. 1..
 */
public class CalcSumTest {

    @Test
    public void sumOfNumbers() throws IOException{
        Calculator calculator = new Calculator();

        int sum = calculator.calcSum("/Users/daum/Downloads/tobySpring/numbers.txt");

        Assert.assertThat(sum, is(10));
    }
}
