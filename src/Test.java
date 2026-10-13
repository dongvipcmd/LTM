import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;
import java.net.Socket;

public class Test{

    public static String mahoa(String str, int num){
        StringBuilder result = new StringBuilder();
        num = num %26;

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                char c = (char)( (ch + num - 'A')%26 + 'A');
                result.append(c);
            }
            else if(Character.isLowerCase(ch)){
                char c = (char) ((ch + num -'a')%26 + 'a');
                result.append(c);
            }
            else{
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String giaima(String str, int num){
        return mahoa(str, 26-(num %26));
    }

    public static void main(String args[]){

        String host = "36.50.135.242";
        int port = 2207;

        String studentCode = "B23DCCN162";
        String qCode = "gZc3iEab";

        try{

            Socket socket = new Socket(host, port);
            if(socket == null) System.out.println("Can not connect to server");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(studentCode+";"+qCode);
            out.flush();

            String str = in.readUTF();
            int num = in.readInt();

            System.out.println(str);
            System.out.println(num);

            out.writeUTF(giaima(str, num));
            out.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}