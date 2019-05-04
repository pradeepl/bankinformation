package com.example.bankinformation.webservices;

import com.example.bankinformation.objectmodel.BranchInfo;
import com.example.bankinformation.repository.dataaccess.BranchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankInformationController
{
  @Autowired
  BranchDAO branchInfoAccessService;

  @GetMapping(value="/branch_info")
  public ResponseEntity getBranchInfo(@RequestParam(value="bank_name", required=false) String bankName, @RequestParam(value="city", required=false) String city, @RequestParam(value="ifsc", required=false) String ifsc  )
  {
    if (ifsc == null && (bankName != null && city != null)){
       List<BranchInfo> branches = branchInfoAccessService.getBranchDetailsProvidedBankNameAndCity(bankName, city);
       if (branches == null)
       {
         //Heroku dumps the contents printed on the STDOUT to the heroku logs. So using System.out.println
         System.out.println("WS: No branch found for the bank: " + bankName + " in city: " + city);
         return constructNotFoundMessage();
       }
       else
       {
          System.out.println("WS: " + branches.size() + " branches found for the bank: " + bankName + " in city: " + city);
          return ResponseEntity.ok(branches);
       }
    }
    else if ( ifsc != null && (bankName == null && city == null ))
    {
      BranchInfo branchInfo = branchInfoAccessService.getBranchDetailsProvidedIfscCode(ifsc);

      if (branchInfo == null){
        System.out.println("WS: No branch found for the IFSC code: " + ifsc);
        return constructNotFoundMessage();
      }
      else{
        System.out.println("WS: Branch " + branchInfo.getBranchName() + " belonging to bank " + branchInfo.getBankName() +  "found for the IFSC code: " + ifsc);
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
