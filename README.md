# CREDIT APPLICATION APP

With the ID number, name, surname, monthly income, phone number, and date of birth, credit application service is accessed and the credit score of the relevant person is obtained and the credit result is shown to the user.

## Webservices ##

### Create Client ###

The first webservice is on client creation. When creating a client, the request will include id no, name, surname, monthly income, phone number, date of birth and credit score of the user. So a request will come to the service as follows.
```json
    {
        "idNo" :12121212121,
        "name" : "Hakan",
        "surname" : "Boranbay",
        "monthlyIncome" : 3000.00,
        "phoneNo" : "905555555555",
        "dateOfBirth" : "1996-11-17",
        "creditScore" : 400
    }
```

### Update Client ###

The second webservice is on client update. When updating a clietn, the request will include id no, new credit score, new and monthly income of the user. So a request will come to the service as follows.
```json
    {
        "idNo" :12121212121,
        "creditScore" : 750,
        "monthlyIncome" : 7500.00
    }
```

### Delete Client ###

The second webservice is on client deletion. When updating a client, the request will include id no, new credit score, new and monthly income of the user. So a request will come to the service as follows.
```json
    {
        "idNo" :12121212121
    }
```

### Credit Application ###

The fourth webservice is on credit application. When a client is applying to credit, the request will include client id no, and assurence of the user. So a request will come to the service as follows.
```json
    {
        "clientIdNo" : 12121212121,
        "assurance" : 5000.0
    }
```

### List Client Applications ###

This service is the service that directly retrieves all the application details of the client. clientIdNo and clientDateOfBirth are taken as paths from the URL.

## Credit Application Rules ##

* If the credit score is below 500, the user is rejected. (Credit result: Reject)
* If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the user's credit application is approved and the user is assigned a limit of 10,000 TL (Credit Result: Approval). If the user has provided assurance, 10 percent of the assurance amount is added to the credit limit.
* If the credit score is between 500 points and 1000 points and the monthly income is between 5000 TL and 10000 TL, the user's credit application is approved and the user is assigned a limit of 20,000 TL (Credit Result: Approval). If the user has provided assurance, 20 percent of the assurance amount is added to the credit limit.
* If the credit score is between 500 points and 1000 points and the monthly income is above 10,000 TL, the user's credit application is approved and the user is assigned a limit equal to MONTHLY INCOME INFORMATION * CREDIT LIMIT CONSTANT/2. (Loan Result: Approval) If the user has provided assurance, 25 percent of the assurance amount is added to the credit limit.
* If the credit score is equal to or above 1000 points, the user is assigned a limit equal to the MONTHLY INCOME INFORMATION * CREDIT LIMIT CONSTANT. (Credit Result: Approval) If the user has provided assurance, 50 percent of the assurance amount is added to the credit limit.
* Credit limit constant is 4 by default.
