import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class TCP03 {
    public static void main(String[] args){
        String host = "36.50.135.242";
        int port = 2206;

        String studentCode = "B23DCCN162";
        String qCode = "a8wG8DkY";

        try{
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            if(socket == null) System.out.println("Can not connect to server");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            String request = studentCode+";"+qCode;
            out.write(request.getBytes());
            out.flush();

            byte[] buf = new byte[4096];
            int len = in.read(buf);
            String data = new String(buf, 0, len);

            System.out.println("Received data: "+ data);

            String[] arr = data.split(",");
            int[] numbers = new int[arr.length];
            for(int i=0; i<arr.length; i++){
                numbers[i] = Integer.parseInt(arr[i]);
            }
            int[] b = numbers.clone();
            int tmp = 0;
            Arrays.sort(numbers);
            for(int i = 0; i<arr.length; i++){
                if(numbers[arr.length-2] == b[i]){
                    tmp = i;
                }
            }

            String result = numbers[arr.length-2]+","+tmp;
            System.out.println(result);

            out.write(result.getBytes());
            out.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
