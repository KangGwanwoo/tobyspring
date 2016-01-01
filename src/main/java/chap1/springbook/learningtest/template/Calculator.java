package chap1.springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by daum on 16. 1. 1..
 */
public class Calculator {
    public int calcSum(String path) throws IOException {
        BufferedReader br =null;
        try {
            br = new BufferedReader(new FileReader(path));
            Integer sum = 0;
            String line = null;

            while ((line = br.readLine()) != null) {
                sum += Integer.valueOf(line);
            }
            return sum;
        }catch (IOException e){
            throw e;
        }finally {
            if(br!=null){
                try {
                    br.close();
                }catch (IOException e){

                }
            }
        }
    }
}
