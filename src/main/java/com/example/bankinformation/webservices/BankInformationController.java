package com.example.bankinformation.webservices;

import com.example.bankinformation.objectmodel.BankInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankInformationController
{
  public BankInformationController()
  {
    //nothing
  }

  @GetMapping(value="/")
  public ResponseEntity serviceInfo()
  {
    return ResponseEntity.ok("Get information about bank and their branches given the IFSC code");
  }

  @GetMapping(value="/bank")
    public ResponseEntity getBankInfo(@RequestParam(value="bankName") String bankName)
  {
    return ResponseEntity.ok(new BankInfo("HDFC", "JP Nagar", "HDFC00009", 12345));
  }
}
