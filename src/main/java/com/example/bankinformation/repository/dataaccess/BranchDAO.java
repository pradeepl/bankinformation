package com.example.bankinformation.repository.dataaccess;

import com.example.bankinformation.repository.dataobject.BranchDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchDAO
{
  @Autowired
  BankBranchInformationRepository repo;

  public BranchDO getBranchDetailsProvidedIfscCode(String ifscCode)
  {
    return repo.getBranchDetailsGivenIFSCCode(ifscCode);
  }

}
