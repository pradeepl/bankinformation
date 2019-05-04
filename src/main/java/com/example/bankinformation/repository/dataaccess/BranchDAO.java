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
    BranchDO branchDO = repo.getBranchDetailsGivenIFSCCode(ifscCode);
    if (branchDO == null)
    {
      return null;
    }
    BranchInfo branchInfo = new BranchInfo(branchDO.getBank().getName(), branchDO.getBranchName(), branchDO.getIfsc(), branchDO.getAddress(), branchDO.getCity(), branchDO.getDistrict(), branchDO.getState());
    return branchInfo;
  }

  public List<BranchInfo> getBranchDetailsProvidedBankNameAndCity(String bankName, String city)
  {
    List<BranchDO> branches = repo.getBranchDetailsGivenBankNameAndCity(bankName, city);
    if (branches == null || branches.isEmpty())
    {
      return null;
    }

    List<BranchInfo> branchesInfo = new ArrayList<BranchInfo>();
    for (BranchDO branchDO: branches) {
      BranchInfo branchInfo = new BranchInfo(branchDO.getBank().getName(), branchDO.getBranchName(), branchDO.getIfsc(), branchDO.getAddress(), branchDO.getCity(), branchDO.getDistrict(), branchDO.getState());
      branchesInfo.add(branchInfo);
    }
    return branchesInfo;
  }



}
