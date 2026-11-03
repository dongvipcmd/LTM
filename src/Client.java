import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args){

        String studentCode = "B23DCCN162";
        String qCode = "cqdRnqVO";

        String host = "36.50.135.242";
        int port = 1099;

        try{

            Registry registry = LocateRegistry.getRegistry(host, port);

            ByteService service = (ByteService) registry.lookup("RMIByteService");

            byte[] data = service.requestData(studentCode, qCode);

            Map<Byte, Integer> mp = new HashMap<>();

            for(byte x : data){
                mp.put(x, mp.getOrDefault(x, 0) + 1);
            }

            byte min = data[0];
            int minCount = mp.get(min);

            for(int i= 0; i<data.length; i++){
                if(minCount > mp.get(data[i])  ){
                    min = data[i];
                    minCount = mp.get(data[i]);
                }
            }

            byte[] result = new byte[2];
            result[0] = min;
            result[1] = (byte) minCount;

            service.submitData(studentCode, qCode, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
