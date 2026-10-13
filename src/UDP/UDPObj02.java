package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class UDPObj02 {

    public static void main(String[] args){

        String host = "36.50.135.242";
        int port = 2209;
        String studentCode = "B23DCCN162";
        String qCode = "vAk4h1TO";

        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(host);

            String message = ";"+studentCode+";"+qCode;
            byte[] sendData = message.getBytes();

            socket.send(new DatagramPacket(sendData, sendData.length, serverAddress, port));

            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            byte[] data = receivePacket.getData();
            int len = receivePacket.getLength();

            String requestId = new String(data, 0, 8);
            ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, len - 8);
            ObjectInputStream obis = new ObjectInputStream(bais);
            Customer customer = (Customer) obis.readObject();

            System.out.println(customer.getId());
            System.out.println(customer.getCode());
            System.out.println(customer.getName());
            System.out.println(customer.getDayOfBirth());
            System.out.println(customer.getUserName());

            customer.setUserName(customer.createUser(customer.getName()));
            customer.setName(customer.chuanHoaTen(customer.getName()));
            customer.setDayOfBirth(customer.chuanHoaNS(customer.getDayOfBirth()));


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes());
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(customer);

            byte[] result = baos.toByteArray();
            socket.send(new DatagramPacket(result, result.length, serverAddress, port));


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
