import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class RMIByte2 {

    public static void main(String[] args){

        String studentCode = "B23DCCN162";
        String qCode = "KkBXYrGW";

        String host = "36.50.135.242";
        int port = 1099;

        try{

            Registry registry = LocateRegistry.getRegistry(host, port);

            ByteService service = (ByteService) registry.lookup("RMIByteService");

            byte[] data = service.requestData(studentCode, qCode);

            Map<Byte, Integer> mp = new HashMap<>();

            for(byte b : data){
                mp.put(b, mp.getOrDefault(b, 0) + 1);
            }

            byte max = data[0];
            int maxCount = mp.get(max);

            for(byte b : data){
                if(mp.get(b) > maxCount){
                    max = b;
                    maxCount = mp.get(b);
                }
            }

            byte[] result = new byte[2];
            result[0] = max;
            result[1] = (byte) maxCount;
            service.submitData(studentCode, qCode, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
