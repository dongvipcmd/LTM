import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.net.*;

public class Example {

    public static void main(String[] args){
        String host = "36.50.135.242";
        int port = 2208;

        String studentCode = "B23DCCN162";
        String qCode = "bWAxvu2r";
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);

            String message = ";"+studentCode+";"+qCode;
            byte[] sendData = message.getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, serverAddress, port));

            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length );
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(),0,receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println(response);

            byte[] result = response.getBytes();
            socket.send(new DatagramPacket(result, result.length, serverAddress, port));


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
