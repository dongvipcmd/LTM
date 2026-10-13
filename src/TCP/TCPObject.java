package TCP;

import java.io.*;
import java.net.Socket;

public class TCPObject {

    public static void main(String[] args) {

        String host = "36.50.135.242";
        int port = 2209;

        String studentCode = "B23DCCN162";
        String qCode = "u9JuEZLN"; // thay đúng qCode theo đề của bạn

        Socket socket = null;

        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            String request = studentCode + ";" + qCode;
            out.writeObject(request);
            out.flush();

            System.out.println("Da gui request: " + request);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Dang doc object...");
            Object obj = in.readObject();

            Laptop laptop = (Laptop) obj;


            System.out.println("Nhan duoc Laptop:");
            System.out.println("ID: " + laptop.getId());
            System.out.println("Code: " + laptop.getCode());
            System.out.println("Name: " + laptop.getName());
            System.out.println("Quantity: " + laptop.getQuantity());
            System.out.println("Class: " + laptop.getClass().getName());

            laptop.setName(laptop.changeName(laptop.getName()));
            laptop.setQuantity(laptop.changeQuantity(laptop.getQuantity()));

            out.writeObject(laptop);
            out.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}