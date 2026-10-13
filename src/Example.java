import java.util.*;

public class Example {

    public static void main(String[] args){
        String name = "nguyen van hai duong";

        String[] str = name.split(" ");
        String result = str[str.length - 1].toUpperCase() +", ";
        for(int i=0; i<str.length-1; i++){
            result+= Character.toUpperCase(str[i].charAt(0));
            for(int j = 1; j<str[i].length(); j++){
                result += Character.toLowerCase(str[i].charAt(j));
            }
            if(i != str.length-2){
                result+= " ";
            }
        }
        System.out.println(result);

    }

}
