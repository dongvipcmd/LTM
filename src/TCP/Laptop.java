package TCP;

import java.io.Serializable;

public class Laptop implements Serializable {

    private static final long serialVersionUID = 20150711L;

    private int id;
    private String code;
    private String name;
    private int quantity;

    public Laptop(int id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = (quantity);
    }

    public String changeName(String name){
        String[] str = name.split(" ");
        String result = str[str.length-1]+" ";
        for(int i=1; i<str.length-1; i++){
            result += str[i] +" ";
        }
        result += str[0];
        return result;

    }

    public int changeQuantity(int quantity){
        int num = 0;
        while (quantity != 0){
            int tmp = quantity %10;
            num = num * 10 + tmp;
            quantity /= 10;
        }
        return num;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Laptop{id=" + id + ", code=" + code + ", name=" + name + ", quantity=" + quantity + "}";
    }
}