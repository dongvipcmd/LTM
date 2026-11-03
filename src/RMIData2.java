import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Locale;

public class RMIData2 {

    public static void main(String[] args){

        String studentCode = "B23DCCN162";
        String qCode = "9nRsjsi6";

        String host = "36.50.135.242";
        int port = 1099;

        try{

            Registry registry = LocateRegistry.getRegistry(host, port);

            DataService service = (DataService) registry.lookup("RMIDataService");

            Object obj = service.requestData(studentCode, qCode);

            String str = (String) obj;

            String[] arrStr = str.split(",");
            double[] arr = new double[arrStr.length];
            for(int i = 0; i<arr.length; i++){
                arr[i] = Double.parseDouble(arrStr[i]);
            }
            for (double x : arr){
                System.out.print(x+" ");
            }

            double sum = 0;
            for (double x : arr) {
                sum += x;
            }
            double avg = sum / arr.length;

            double variance = 0;
            for (double x : arr) {
                variance += Math.pow(x - avg, 2);
            }
            double stddev = Math.sqrt(variance / arr.length);

            Arrays.sort(arr);
            int index = (int) Math.ceil(arr.length * 0.95) - 1;
            double p95 = arr[index];


            String result = String.format(
                    Locale.US,
                    "average=%.2f;stddev=%.2f;p95=%.2f",
                    avg,stddev , p95
            );
            service.submitData(studentCode, qCode, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
