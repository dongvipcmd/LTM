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

            System.out.println(str);

            int num = Integer.parseInt(str);

            String result = "";

            int[] values = {
                    1000, 900, 500, 400, 100, 90,
                    50, 40, 10, 9, 5, 4, 1
            };

            String[] romans = {
                    "M", "CM", "D", "CD", "C", "XC",
                    "L", "XL", "X", "IX", "V", "IV", "I"
            };

            for (int i = 0; i < values.length; i++) {
                while (num >= values[i]) {
                    result += romans[i];
                    num -= values[i];
                }
            }

            System.out.println(result);

            service.submitCharacter(studentCode, qCode, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

