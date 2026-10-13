import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args){
        String host = "36.50.135.242";
        int port = 2208;

        String studentCode = "B23DCCN162";
        String qCode = "NFPkPyu4";

        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            if(socket == null) System.out.println("Can not connect to server");
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            if(socket!=null){
                System.out.println("Connect server successful");
            }
            System.out.println(socket);

            out.write(studentCode+";"+qCode);
            out.newLine();
            out.flush();

            String data = in.readLine();
            System.out.println("Received from server: ");
            System.out.println(data);

            String[] domains = data.split("\\s*,\\s*");
            StringBuilder result = new StringBuilder();

            for(String domain : domains){
                if(domain.endsWith(".edu")){
                    if(result.length()>0){
                        result.append(", ");
                    }
                    result.append(domain);
                }
            }

            out.write(result.toString());
            out.newLine();
            out.flush();

            System.out.println("Sent to server");
            System.out.println(result);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
