import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class UDP02 {

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

           DatagramPacket sendPacket = new DatagramPacket(
                   sendData, sendData.length, serverAddress, port
           );
           socket.send(sendPacket);

           byte[] receiveData = new byte[4096];
           DatagramPacket receivePacket = new DatagramPacket(
                   receiveData, receiveData.length
           );

           socket.receive(receivePacket);

           String response = new String(receivePacket.getData(),
                   0,
                   receivePacket.getLength(),
                   StandardCharsets.UTF_8);

           System.out.println(response);
           String[] parts = response.split(";");
           String s = parts[1];

           Map<Character, Integer> map = new HashMap<>();
           for(char c : s.toCharArray()){
               map.put(c, map.getOrDefault(c, 0) + 1);
           }

           char maxChar = ' ';
           int max = 0;

           for(Map.Entry<Character, Integer> entry : map.entrySet() ){
               if(entry.getValue() > max){
                   max = entry.getValue();
                   maxChar = entry.getKey();
               }
           }
//           char maxChar = s.charAt(0);
//           int max = 0;
//
//           for (char c : s.toCharArray()) {
//               int count = map.get(c);
//
//               if (count > max) {
//                   max = count;
//                   maxChar = c;
//               }
//           }

           String result = parts[0] +";"+maxChar+":";
           for(int i  = 0; i<s.length(); i++){
                if(s.charAt(i) == maxChar){
                    result += i+1 +",";
                }
           }
           System.out.println(result);

           byte[] answerData = result.getBytes(StandardCharsets.UTF_8);
           DatagramPacket answerPacket = new DatagramPacket(
                   answerData,
                   answerData.length,
                   receivePacket.getAddress(),
                   receivePacket.getPort()
           );
           socket.send(answerPacket);

       }catch (Exception e){
           e.printStackTrace();
       }

    }

}
