package com.example.bankinformation.objectmodel;

public class SearchCriteria
{
  private String bankName;
  private String city;

  public SearchCriteria(){

  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

}
