package com.example.bankinformation.repository.dataaccess;

import com.example.bankinformation.objectmodel.BranchInfo;
import com.example.bankinformation.repository.dataobject.BranchDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchDAO
{
  @Autowired
  BankBranchInformationRepository repo;

  public BranchInfo getBranchDetailsProvidedIfscCode(String ifscCode)
  {
    System.out.println("DAO: Going to find the branch details for the ifsc: " + ifscCode);
    BranchDO branchDO = repo.getBranchDetailsGivenIFSCCode(ifscCode);
    if (branchDO == null)
    {
      System.out.println("DAO: No branch found for the ifsc: " + ifscCode);
      return null;
    }

    System.out.println("DAO: Branch " + branchDO.getBranchName() + " belonging to " + branchDO.getBank().getName() + " found for the ifsc: " + ifscCode);
    BranchInfo branchInfo = new BranchInfo(branchDO.getBank().getName(), branchDO.getBranchName(), branchDO.getIfsc(), branchDO.getAddress(), branchDO.getCity(), branchDO.getDistrict(), branchDO.getState());
    return branchInfo;
  }

  public List<BranchInfo> getBranchDetailsProvidedBankNameAndCity(String bankName, String city)
  {
    List<BranchDO> branches = repo.getBranchDetailsGivenBankNameAndCity(bankName, city);
    if (branches == null || branches.isEmpty())
    {
      System.out.println("DAO: No branch found for the bank: " + bankName + " in city: " + city);
      return null;
    }

    System.out.println("DAO: Found " + branches.size() + " for the bank: " + bankName + " in city: " + city);
    List<BranchInfo> branchesInfo = new ArrayList<BranchInfo>();
    for (BranchDO branchDO: branches) {
      BranchInfo branchInfo = new BranchInfo(branchDO.getBank().getName(), branchDO.getBranchName(), branchDO.getIfsc(), branchDO.getAddress(), branchDO.getCity(), branchDO.getDistrict(), branchDO.getState());
      branchesInfo.add(branchInfo);
    }
    return branchesInfo;
  }



}
