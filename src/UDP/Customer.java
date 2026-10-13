package UDP;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 20151107;
    private String id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(String id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    public String chuanHoaTen(String name){
        String[] str = name.split(" ");
        String result = str[str.length - 1].toUpperCase() +", ";
        for(int i=0; i<str.length-1; i++){
            result+= Character.toUpperCase(str[i].charAt(0));
            for(int j = 1; j<str[i].length(); j++){
                result += Character.toLowerCase(str[i].charAt(j));
            }
            if(i != str.length-2){
                result+= " ";
            }
        }
        return result;
    }

    public String createUser(String name){
        String[] str = name.split(" ");
        String result = "";
        for(int i=0; i<str.length-1; i++){
            result+=Character.toLowerCase(str[i].charAt(0));
        }
        result += str[str.length-1].toLowerCase();
        return result;
    }

    public String chuanHoaNS(String dayOfBirth){
        String[] str = dayOfBirth.split("-");
        return str[1]+"/"+str[0]+"/"+str[2];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
