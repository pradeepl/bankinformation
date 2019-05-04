package com.example.bankinformation.webservices;

import com.example.bankinformation.objectmodel.BranchInfo;
import com.example.bankinformation.repository.dataaccess.BranchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankInformationController
{
  @Autowired
  BranchDAO branchInfoAccessService;

  public BankInformationController()
  {
  }

  @GetMapping(value="/branch_info")
  public ResponseEntity getBranchInfo(@RequestParam(value="bank_name", required=false) String bankName, @RequestParam(value="city", required=false) String city, @RequestParam(value="ifsc", required=false) String ifsc  )
  {
    if (ifsc != null && (bankName == null && city == null )){
       List<BranchInfo> branches = branchInfoAccessService.getBranchDetailsProvidedBankNameAndCity(bankName, city);
       if (branches == null)
       {
         return constructNotFoundMessage();
       }
       else
       {
          return ResponseEntity.ok(branches);
       }
    }
    else if ( ifsc == null && (bankName != null && city != null))
    {
      BranchInfo branchInfo = branchInfoAccessService.getBranchDetailsProvidedIfscCode(bankName);
      if (branchInfo == null){
        return constructNotFoundMessage();
      }
      else{
        return ResponseEntity.ok(branchInfo);
      }
    }
    else
    {
      return ResponseEntity.badRequest().body("Invalid Search Criteria Provided");
    }
  }


  private ResponseEntity constructNotFoundMessage()
  {
    return ResponseEntity.notFound().build();
  }
}
