package TCP;

import java.net.Socket;
import java.io.*;

public class TCPOb {

    public static void main(String[] args) {

        String host = "36.50.135.242";
        int port = 2209;

        String studentCode = "B23DCCN162";
        String qCode = "XzdSe9tV";

        try {

            Socket socket = new Socket(host, port);

            ObjectOutputStream out =
                    new ObjectOutputStream(socket.getOutputStream());

            String request = studentCode + ";" + qCode;

            // Gửi mã sinh viên + mã câu hỏi
            out.writeObject(request);
            out.flush();

            ObjectInputStream in =
                    new ObjectInputStream(socket.getInputStream());

            // Nhận Customer từ server
            Object obj = in.readObject();

            Customer customer = (Customer) obj;

            // In thông tin ban đầu
            System.out.println("Thong tin ban dau:");

            System.out.println(customer.getId());
            System.out.println(customer.getCode());
            System.out.println(customer.getName());
            System.out.println(customer.getDayOfBirth());
            System.out.println(customer.getUserName());

            // Lưu tên gốc trước khi thay đổi
            String name = customer.getName();

            // Chuẩn hóa thông tin
            customer.setName(customer.chuanHoaTen(name));

            customer.setDayOfBirth(customer.chuyenNgay());

            customer.setUserName(customer.chuyenUserName(name));

            // In thông tin sau khi chuẩn hóa
            System.out.println("Thong tin sau khi chuan hoa:");

            System.out.println(customer.getId());
            System.out.println(customer.getCode());
            System.out.println(customer.getName());
            System.out.println(customer.getDayOfBirth());
            System.out.println(customer.getUserName());

            // Gửi Customer đã chuẩn hóa về server
            out.writeObject(customer);
            out.flush();

            // Đóng socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}