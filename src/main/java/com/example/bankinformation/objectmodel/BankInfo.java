package com.example.bankinformation.objectmodel;

public class BankInfo
{
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  private String bankName;
  private String branchName;
  private String ifscCode;
  private long id;


  public BankInfo(String bankName, String branchName, String ifscCode, long id)
  {
    this.bankName = bankName;
    this.branchName = branchName;
    this.ifscCode = ifscCode;
    this.id = id;
  }

}
