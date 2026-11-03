import RMI.ObjectService;
import RMI.TicketSla;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIObject {

    public static void main(String[] args){
        String studentCode = "B23DCCN162";
        String qCode = "6XRdx5kT";

        String host = "36.50.135.242";
        int port = 1099;

        try {

            Registry registry = LocateRegistry.getRegistry(host, port);

            ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

            Object obj = service.requestObject(studentCode, qCode);

            TicketSla ticket = (TicketSla) obj;

            ticket.check();

            ticket.setAction(ticket.checkAction(ticket.getPriority(), ticket.getOpenedHoursAgo()));

            java.io.Serializable data = (java.io.Serializable) ticket;

            service.submitObject(studentCode, qCode, data);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
