package com.example.bankinformation.objectmodel;

public class BranchInfo
{
  private String bankName;
  private String branchName;
  private String ifscCode;
  private String address;
  private String city;
  private String district;
  private String state;

  public BranchInfo(String bankName, String branchName, String ifscCode, String address, String city, String district, String state)
  {
    this.bankName = bankName;
    this.branchName = branchName;
    this.ifscCode = ifscCode;
    this.address = address;
    this.city = city;
    this.district = district;
    this.state = state;
  }

  //Branch Name
  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public String getIfscCode() {
    return ifscCode;
  }

  public void setIfscCode(String ifscCode) {
    this.ifscCode = ifscCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
