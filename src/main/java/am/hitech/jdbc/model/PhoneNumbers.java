package am.hitech.jdbc.model;

public class PhoneNumbers {
    private int id;
    private int number;
    private int phoneCodeId;
    private int userId;

    public PhoneNumbers(int id, int number, int phoneCodeId, int userId) {
        this.id = id;
        this.number = number;
        this.phoneCodeId = phoneCodeId;
        this.userId = userId;
    }
    public PhoneNumbers() {}

    @Override
    public String toString() {
        return "PhoneNumbers{" +
                "id=" + id +
                ", number=" + number +
                ", phoneCodeId=" + phoneCodeId +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPhoneCodeId() {
        return phoneCodeId;
    }

    public void setPhoneCodeId(int phoneCodeId) {
        this.phoneCodeId = phoneCodeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
