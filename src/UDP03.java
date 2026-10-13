import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UDP03 {

    public static void main(String[] args){
        String host = "36.50.135.242";
        int port = 2207;

        String studentCode = "B23DCCN162";
        String qCode = "DCnnVaDL";

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);

            String message = ";"+studentCode+";"+qCode;
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length, serverAddress, port
            );
            socket.send(sendPacket);

            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(
                    receiveData, receiveData.length
            );
            socket.receive(receivePacket);

            String response = new String(
                    receivePacket.getData(),
                    0,
                    receivePacket.getLength(),
                    StandardCharsets.UTF_8
            );
            System.out.println(response);
            String[] parts = response.split(";");
            String[] str = parts[1].split(",");
            int[] nums = new int[str.length];

            for(int i=0; i<str.length; i++){
                nums[i] = Integer.parseInt(str[i]);
            }
            Arrays.sort(nums);
            String result = parts[0] +";"+nums[str.length-1]+","+nums[0];
            System.out.println(result);

            byte[] answerData = result.getBytes();

            DatagramPacket answerPacket = new DatagramPacket(
                    answerData, answerData.length, serverAddress, port
            );

            socket.send(answerPacket);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
