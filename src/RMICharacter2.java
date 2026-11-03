import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICharacter2 {

    public static void main(String[] args){

        String studentCode = "B23DCCN162";
        String qCode = "42iS8KIT";

        String host = "36.50.135.242";
        int port = 1099;

        try {

            Registry registry = LocateRegistry.getRegistry(host, port);

            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

            String str = service.requestCharacter(studentCode, qCode);

            int num = Integer.parseInt(str);
            while (num != 0){
                int tmp = num%
            }

            System.out.println(str);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

