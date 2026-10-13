package UDP;

import java.net.*;
import java.io.*;

public class UDPObj01 {

    public static void main(String args[]){
        String host = "36.50.135.242";
        int port = 2209;

        String studentCode = "B23DCCN162";
        String qCode = "wXUMjav9";

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
            byte[] data = receivePacket.getData();
            int len = receivePacket.getLength();

            String requestId = new String(data, 0, 8);
            ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, len - 8);
            ObjectInputStream obis = new ObjectInputStream(bais);

            Student obj = (Student) obis.readObject();
            System.out.println(requestId);
            System.out.println(obj.getId());
            System.out.println(obj.getCode());
            System.out.println(obj.getName());

            obj.setName(obj.chuanHoaTen(obj.getName()));
            obj.setEmail(obj.createEmail(obj.getName()));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes(), 0, 8);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();

            byte[] result = baos.toByteArray();
            socket.send(new DatagramPacket(result, result.length, serverAddress, port));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
