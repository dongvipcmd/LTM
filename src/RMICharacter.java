import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.URI;

public class RMICharacter {

    public static void main(String[] args){

        String studentCode = "B23DCCN162";
        String qCode = "1uOBwelr";

        String host = "36.50.135.242";
        int port = 1099;

        try{

            Registry registry = LocateRegistry.getRegistry(host, port);

            CharacterService service =(CharacterService) registry.lookup("RMICharacterService");

            String str = service.requestCharacter(studentCode, qCode);

            System.out.println(str);

            String result = new URI(null, null, str, null).toASCIIString();

            service.submitCharacter(studentCode, qCode, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
