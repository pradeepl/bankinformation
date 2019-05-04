package com.example.bankinformation.repository.dataaccess;

import com.example.bankinformation.repository.dataobject.BranchDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBranchInformationRepository extends CrudRepository<BranchDO, String>
{

  @Query("SELECT br FROM BranchDO br WHERE UPPER(br.bank.name) = UPPER(?1) AND UPPER(br.city) = UPPER(?2)")
  List<BranchDO> getBranchDetailsGivenBankNameAndCity(String bankName, String city);


  @Query("SELECT b from BranchDO b where UPPER(ifsc) = UPPER(?1)")
  BranchDO getBranchDetailsGivenIFSCCode(String ifsc);
}
