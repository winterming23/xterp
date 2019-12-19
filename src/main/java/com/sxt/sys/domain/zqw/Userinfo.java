package com.sxt.sys.domain.zqw;


import java.sql.Timestamp;

public class Userinfo {

  private long id;
  private String loginName;
  private String password;
  private String userName;
  private String gender;
  private String email;
  private String phone;
  private String address;
  private Timestamp birthday;
  private Timestamp hireDate;
  private long deptNo;
  private long mar;
  private double sal;
  private double comm;


  public Userinfo() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(Timestamp birthday) {
    this.birthday = birthday;
  }

  public Timestamp getHireDate() {
    return hireDate;
  }

  public void setHireDate(Timestamp hireDate) {
    this.hireDate = hireDate;
  }

  public long getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(long deptNo) {
    this.deptNo = deptNo;
  }

  public long getMar() {
    return mar;
  }

  public void setMar(long mar) {
    this.mar = mar;
  }

  public double getSal() {
    return sal;
  }

  public void setSal(double sal) {
    this.sal = sal;
  }

  public double getComm() {
    return comm;
  }

  public void setComm(double comm) {
    this.comm = comm;
  }

  public Userinfo(long id, String loginName, String password, String userName, String gender, String email, String phone, String address, Timestamp birthday, Timestamp hireDate, long deptNo, long mar, double sal, double comm) {
    this.id = id;
    this.loginName = loginName;
    this.password = password;
    this.userName = userName;
    this.gender = gender;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.birthday = birthday;
    this.hireDate = hireDate;
    this.deptNo = deptNo;
    this.mar = mar;
    this.sal = sal;
    this.comm = comm;
  }

  @Override
  public String toString() {
    return "Userinfo{" +
            "id=" + id +
            ", loginName='" + loginName + '\'' +
            ", password='" + password + '\'' +
            ", userName='" + userName + '\'' +
            ", gender='" + gender + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", birthday=" + birthday +
            ", hireDate=" + hireDate +
            ", deptNo=" + deptNo +
            ", mar=" + mar +
            ", sal=" + sal +
            ", comm=" + comm +
            '}';
  }
}
