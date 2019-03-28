package sample;

import java.io.BufferedReader;
import java.io.IOException;

public class Validator {

    public static boolean LoginCheck(BufferedReader reader){
        try {
            reader.readLine();
            return true;
        }catch (IOException e){
            return false;
        }
    }
}
