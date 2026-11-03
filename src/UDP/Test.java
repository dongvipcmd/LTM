package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class Test {

    public static void main(String[] args){

        String host = "36.50.135.242";
        int port = 2209;

        String studentCode = "B23DCCN162";
        String qCode = "wXUMjav9";
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);

            String message = ";"+studentCode+";"+qCode;
            byte[] sendData = message.getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, serverAddress, port));

            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(new DatagramPacket(receiveData, receiveData.length));

            byte[] data = receivePacket.getData();
            int len = receivePacket.getLength();

            String requestId = new String(data, 0, 8);
            ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, len-8);
            ObjectInputStream ois = new ObjectInputStream(bais);

            Student student = (Student) ois.readObject();

            student.setName(student.chuanHoaTen(student.getName()));
            student.setEmail(student.createEmail(student.getName()));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes());
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();

            byte[] result = baos.toByteArray();
            socket.send(new DatagramPacket(result, result.length, serverAddress, port));



        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
