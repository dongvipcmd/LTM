import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMIData1 {

    public static void main(String[] args){
        String studentCode = "B23DCCN162";
        String qCode = "WoQZA82R";

        String host = "36.50.135.242";
        int port = 1099;

        try {

            Registry registry = LocateRegistry.getRegistry(host, port);

            DataService service =(DataService) registry.lookup("RMIDataService");

            Object obj = service.requestData(studentCode, qCode);

            int[] num = (int[]) obj;

            ArrayList<Integer> arr = new ArrayList<>();

            if (num[1] < num[0]){
                arr.add(1);
            }

            for(int i=1; i<num.length-1; i++ ){
                if(num[i] > num[i-1] && num[i] > num[i+1]){
                    arr.add(i+1);
                }
            }

            if(num[num.length-2] < num[num.length-1]){
                arr.add(num.length);
            }

            Object data = arr;

            service.submitData(studentCode, qCode, data);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
