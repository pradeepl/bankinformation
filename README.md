Project Definition:
-------------------
Write a simple restful webservice which can retrieve the information about the bank and it's branches provided the ifsc code or bank name and the city.

Frameworks used:
-----------------
1. Spring boot (Version: v2.1.4.RELEASE) for defining the Restful webservices.
2. Postgresql (Version: 11.2) is used as the relational DB.
3. Heroku (Version: v11.14.0) is used for deploying the application.
4. Maven (Version: 3.6.1) is used for the project build. 

Source for the bank information:
--------------------------------
The static data holding the information about the variou banks and their branches are source controlled at https://github.com/snarayanank2/indian_banks.
The above data has been imported to the postgresql associated to the heroku application. 

Heroku application endpoint:
----------------------------
https://indian-bank-information.herokuapp.com/

Restwebservice endpoint:
------------------------
1. Search based on IFSC code:
-----------------------------
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?ifsc=<IFSC_CODE_INPUT>
HTTP Method   : GET
Response code : 200 - When a match is found
                404 - When no match is found
Response body : JSON containing the Bank and Branch details on 200 Response code

Example:
--------
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?ifsc=ABNA0100318
Response code : 200 
Response body : 
{
"bankName": "THE ROYAL BANK OF SCOTLAND N V",
"branchName": "BANGALORE",
"ifscCode": "ABNA0100318",
"address": "PRESTIGE TOWERS', GROUND FLOOR, 99 & 100, RESIDENCY ROAD, BANGALORE 560 025.",
"city": "BANGALORE",
"district": "BANGALORE URBAN",
"state": "KARNATAKA"
}

2. Search Based on Bank Name and City:
----------------------------------------
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?bank_name=<BANK_NAME_INPUT>&city=<CITY_INPUT>
HTTP Method   : GET
Response code : 200 - When match is found
                404 - When no match is found
Response body : JSON containing the List of Branch details on 200 Response code

Example:
--------
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?bank_name=ABHYUDAYA COOPERATIVE BANK LIMITED&city=MUMBAI
Response code : 200 
Response body : 
[
{
"bankName": "ABHYUDAYA COOPERATIVE BANK LIMITED",
"branchName": "ABHYUDAYA NAGAR",
"ifscCode": "ABHY0065002",
"address": "ABHYUDAYA EDUCATION SOCIETY, OPP. BLDG. NO. 18, ABHYUDAYA NAGAR, KALACHOWKY, MUMBAI - 400033",
"city": "MUMBAI",
"district": "GREATER MUMBAI",
"state": "MAHARASHTRA"
},
{
"bankName": "ABHYUDAYA COOPERATIVE BANK LIMITED",
"branchName": "AIROLI",
"ifscCode": "ABHY0065032",
"address": "ABHYUDAYA BANK BLDG., PLOT NO.26 SECTOR 17, AIROLI, NAVI MUMBAI-400701",
"city": "MUMBAI",
"district": "GREATER MUMBAI",
"state": "MAHARASHTRA"
},
.
.
.
.
.
]

3. Invalid Search based on other combinations:
-----------------------------------------------
Any invalid Search done based on combinations other than the above 2 will result in 400 (Bad Request) Response code with message "Invalid Search Criteria Provided".

Example:
--------
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?ifsc=ABNA0100318&city=MUMBAI
Response code : 400
Response Body : Invalid Search Criteria Provided
