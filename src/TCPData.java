import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.net.Socket;

public class TCPData {

    public static void main(String[] args){
        String host ="36.50.135.242";
        int port = 2207;

        String studentCode = "B23DCCN162";
        String qCode = "iE3Mv6GN";

        try {

            Socket socket = new Socket(host, port);
            if(socket == null) System.out.println("Can not connect to server");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(studentCode+";"+qCode);
            out.flush();

            int a = in.readInt();
            int b = in.readInt();

            int sum = a+b;
            int pro = a*b;

            out.writeInt(sum);
            out.writeInt(pro);
            out.flush();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
