package UDP;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 20171107;
    private String id;
    private String code;
    private String name;
    private String email;

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public String chuanHoaTen(String name){
        String[] str = name.split(" ");
        String result = "";
        for(int i=0; i<str.length; i++){
            result += Character.toUpperCase(str[i].charAt(0));
            for(int j = 1; j<str[i].length(); j++){
                result+= Character.toLowerCase(str[i].charAt(j));
            }
            if(i!= str.length-1){
                result+=" ";
            }
        }
        return result;
    }

    public String createEmail(String name){
        String[] str = name.split(" ");
        String result = str[str.length-1].toLowerCase();
        for(int i=0; i<str.length-1; i++){
            result+=Character.toLowerCase(str[i].charAt(0));
        }
        return result +"@ptit.edu.vn";
    }

    public Student(String code) {
        this.code = code;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
