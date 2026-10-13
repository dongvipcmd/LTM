import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UDP01 {

    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        String host = "36.50.135.242";
        int port = 2207;

        InetAddress serverAddress = InetAddress.getByName(host);
        String qCode = "ah8OXJ5x";
        String studentCode = "B23DCCN162";

        String message = ";" + studentCode+";"+qCode;
        byte[] sendData = message.getBytes(StandardCharsets.UTF_8);

        DatagramPacket sendPacKet = new DatagramPacket(
          sendData, sendData.length, serverAddress, port
        );
        socket.send(sendPacKet);

        byte[] receiveData = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(
                receiveData, receiveData.length
        );
        socket.receive(receivePacket);

        String response = new String(receivePacket.getData(),
                0,
                receivePacket.getLength(),
                StandardCharsets.UTF_8);
        System.out.println("Du lieu nhan: "+response);

        String parts[]= response.split(";");
        for (String i : parts){
            System.out.println(i);
        }
        int n = Integer.parseInt(parts[1]);
        String[] arr = parts[2].split(",");
        Set<Integer> nums = new HashSet<>();
        for(int i = 0; i< arr.length; i++){
            nums.add(Integer.parseInt(arr[i]));
        }
        int[] b = new int[n+1];
        for(int i : nums){
            if(i >=1 && i <=n){
                b[i] = 1;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(parts[0]+";");

        for(int i = 1; i<=n ;i++){
            if(b[i] != 1){
                result.append(i+",");
            }
        }
        result = new StringBuilder(result.substring(0,result.length()-1));
        System.out.println(result);

        String answer = result.toString();
        byte[] answerData = answer.getBytes(StandardCharsets.UTF_8);

        DatagramPacket answerPacket = new DatagramPacket(
                answerData,
                answerData.length,
                receivePacket.getAddress(),
                receivePacket.getPort()
        );
        socket.send(answerPacket);

    }

}
