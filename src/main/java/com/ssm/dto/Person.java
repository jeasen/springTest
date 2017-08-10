package com.ssm.dto;

public class Person {
    private Integer id;

    private String name;

    private String sex;

    private String email;

    private String password;

    private String phone;

    private String ownMoney;

    private String outMoney;

    private String earnMoney;

    private String toPerson;

    private String fromPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOwnMoney() {return ownMoney;}

    public void setOwnMoney(String ownMoney) {this.ownMoney = ownMoney;}

    public String getOutMoney() {return outMoney;}

    public void setOutMoney(String outMoney) {this.outMoney = outMoney;}

    public String getEarnMoney() {return earnMoney;}

    public void setEarnMoney(String earnMoney) {this.earnMoney = earnMoney;}

    public String getToPerson() {return toPerson;}

    public void setToPerson(String toPerson) {this.toPerson = toPerson;}

    public String getFromPerson() {return fromPerson;}

    public void setFromPerson(String fromPerson) {this.fromPerson = fromPerson;}
}