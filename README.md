# Project Definition:
Write a simple restful webservice which can retrieve the information about the bank and it's branches provided the ifsc code or bank name and the city.

# Frameworks used:
1. Spring boot (Version: v2.1.4.RELEASE) for defining the Restful webservices.
2. Postgresql (Version: 11.2) is used as the relational DB.
3. Heroku (Version: v11.14.0) is used for deploying the application.
4. Maven (Version: 3.6.1) is used for the project build. 

# Source for the bank information:
The static data holding the information about the variou banks and their branches are source controlled at https://github.com/snarayanank2/indian_banks.
The above data has been imported to the postgresql associated to the heroku application. 

# Heroku application endpoint:
https://indian-bank-information.herokuapp.com/

# Restwebservice endpoint:
## Search based on IFSC code:
Note: The search performed is a case insensitive search
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?ifsc=<IFSC_CODE_INPUT>  
HTTP Method   : GET  
Response code : 200 - When a match is found  
                404 - When no match is found  
Response body : JSON containing the Bank and Branch details on 200 Response code  

### Example:
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?ifsc=ABNA0100318
Response code : 200 
Response body : 
```json
{
"bankName": "THE ROYAL BANK OF SCOTLAND N V",
"branchName": "BANGALORE",
"ifscCode": "ABNA0100318",
"address": "PRESTIGE TOWERS', GROUND FLOOR, 99 & 100, RESIDENCY ROAD, BANGALORE 560 025.",
"city": "BANGALORE",
"district": "BANGALORE URBAN",
"state": "KARNATAKA"
}
```

## Search Based on Bank Name and City:
Note: The search performed is a case insensitive search
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?bank_name=<BANK_NAME_INPUT>&city=<CITY_INPUT>  
HTTP Method   : GET  
Response code : 200 - When match is found  
                404 - When no match is found  
Response body : JSON containing the List of Branch details on 200 Response code  

### Example:
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?bank_name=ABHYUDAYA COOPERATIVE BANK LIMITED&city=MUMBAI  
Response code : 200   
Response body : 
```json
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
```

## Invalid Search based on other combinations:
Any invalid Search done based on combinations other than the above 2 will result in 400 (Bad Request) Response code with message "Invalid Search Criteria Provided".

### Example:
Endpoint      : https://indian-bank-information.herokuapp.com/branch_info?ifsc=ABNA0100318&city=MUMBAI  
Response code : 400  
Response Body : Invalid Search Criteria Provided 


# Other optimizations Done
## Query performance is improved for the search based on bank name and city by adding Index
### Underlying query that gets executed
select b.name, br.branch, br.city, br.address, br.city, br.district, br.state from banks b, branches br where br.bank_id = b.id and  upper(b.name) = 'ABHYUDAYA COOPERATIVE BANK LIMITED' and UPPER(city) = 'MUMBAI';
#### Query plan without adding Index
```
                                                      QUERY PLAN
----------------------------------------------------------------------------------------------------------------------
 Nested Loop  (cost=0.00..3057.67 rows=4 width=52) (actual time=0.169..126.418 rows=55 loops=1)
   Join Filter: (b.id = br.bank_id)
   Rows Removed by Join Filter: 3641
   ->  Seq Scan on banks b  (cost=0.00..2.68 rows=1 width=36) (actual time=0.142..0.280 rows=1 loops=1)
         Filter: (upper((name)::text) = 'ABHYUDAYA COOPERATIVE BANK LIMITED'::text)
         Rows Removed by Filter: 169
   ->  Seq Scan on branches br  (cost=0.00..3052.76 rows=638 width=32) (actual time=0.024..125.258 rows=3696 loops=1)
         Filter: (upper((city)::text) = 'MUMBAI'::text)
         Rows Removed by Filter: 124161
 Planning Time: 1.229 ms
 Execution Time: 126.496 ms
(11 rows)
```
### Indexes Added:
1. CREATE INDEX idx_branches_01 ON branches (bank_id, UPPER(branch), UPPER(city));
2. CREATE index idx_banks_01 ON banks (UPPER(name));

#### Query Plan after adding above indexes
```
                                                              QUERY PLAN                                                
--------------------------------------------------------------------------------------------------------------------------------------
 Nested Loop  (cost=0.08..26.59 rows=4 width=52) (actual time=0.060..0.173 rows=55 loops=1)
   ->  Seq Scan on banks b  (cost=0.00..2.68 rows=1 width=36) (actual time=0.044..0.126 rows=1 loops=1)
         Filter: (upper((name)::text) = 'ABHYUDAYA COOPERATIVE BANK LIMITED'::text)
         Rows Removed by Filter: 169
   ->  Index Scan using idx_branches_01 on branches br  (cost=0.08..23.89 rows=5 width=32) (actual time=0.013..0.035 rows=55 loops=1)
         Index Cond: ((bank_id = b.id) AND (upper((city)::text) = 'MUMBAI'::text))
 Planning Time: 0.536 ms
 Execution Time: 0.200 ms
(8 rows)

```
After the addition of indexes we can observe the below improvements.
1. Query Execution time came down from 126 ms to 0.200 ms.
2. Instead of a Full table scan on the highly loaded "branches" table, we can see the Index scan which is more efficient. 

