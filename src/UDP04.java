import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class UDP04 {

    public static void main(String[] args){

        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCCN162";
        String qCode = "X4wAAHAK";

        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);

            String message = ";"+studentCode+";"+qCode;
            byte[] sendData = message.getBytes();

            socket.send(new DatagramPacket(sendData, sendData.length, serverAddress, port));

            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(),
                    0,
                    receivePacket.getLength(),
                    StandardCharsets.UTF_8);
            System.out.println(response);

            String[] str = response.split(";");
            String[] s = str[1].split(" ");
            String result = str[0]+";";
            for(int i=0; i<s.length; i++){
                result += Character.toUpperCase(s[i].charAt(0));
                for(int j = 1; j<s[i].length(); j++){
                    result += Character.toLowerCase(s[i].charAt(j));
                }
                if(i != s.length){
                    result += " ";
                }
            }
            System.out.println(result);
            byte[] answer = result.getBytes();
            socket.send(new DatagramPacket(answer, answer.length, serverAddress, port));


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
