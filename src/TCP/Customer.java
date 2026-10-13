package TCP;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 20170711L;

    private int id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(int id, String code, String name,
                    String dayOfBirth, String userName) {

        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    // Chuyển ngày từ mm-dd-yyyy -> dd/mm/yyyy
    public String chuyenNgay() {

        String[] arr = this.dayOfBirth.trim().split("-");

        return arr[1] + "/" + arr[0] + "/" + arr[2];
    }

    // Ví dụ:
    // nguyen van hai duong
    // -> DUONG, Nguyen Van Hai
    public String chuanHoaTen(String name) {

        String[] arr = name.trim().split("\\s+");

        String result = arr[arr.length - 1].toUpperCase() + ", ";

        for (int i = 0; i < arr.length - 1; i++) {

            String tmp = "";

            for (int j = 0; j < arr[i].length(); j++) {

                if (j == 0) {
                    tmp += Character.toUpperCase(arr[i].charAt(j));
                } else {
                    tmp += arr[i].charAt(j);
                }
            }

            result += tmp;

            if (i < arr.length - 2) {
                result += " ";
            }
        }

        return result;
    }

    // Ví dụ:
    // nguyen van hai duong
    // -> nvhduong
    public String chuyenUserName(String name) {

        String[] arr = name.trim().toLowerCase().split("\\s+");

        String result = "";

        for (int i = 0; i < arr.length - 1; i++) {

            result += arr[i].charAt(0);
        }

        result += arr[arr.length - 1];

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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