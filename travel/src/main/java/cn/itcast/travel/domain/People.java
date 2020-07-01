package cn.itcast.travel.domain;

public class People {
    private Integer id;
    private String username;
    private String number;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
