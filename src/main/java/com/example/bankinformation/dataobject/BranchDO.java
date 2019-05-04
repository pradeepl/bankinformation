package com.example.bankinformation.dataobject;

import javax.persistence.*;

@Entity
@Table(name = "BRANCHES")
public class BranchDO
{

  @Column(name = "IFSC")
  private String ifsc;

  @Column(name = "BRANCH")
  private String branchName;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "CITY")
  private String city;

  @Column(name="DISTRICT")
  private String district;

  @Column(name="STATE")
  private String state;

  @ManyToOne
  @JoinColumn(name = "id")
  private BankDO bank;

  public BranchDO()
  {}

  public String getIfsc() {
    return ifsc;
  }

  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
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
