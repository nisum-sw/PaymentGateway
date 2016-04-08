Java, JPA, Spring profile, Spring Data JPA, Spring Rest, Spring Security.

Rest urls:

headers</br>
 	Authorization: Basic dXNlcjp1c2VyUGFzcw==</br>
	Content-Type: application/json</br>

<b>http://localhost:8080/payment-service/transaction/{YOURNAME} </b></br>
Method : GET</br>

<b>http://localhost:8080/payment-service/transaction/purchase</b></br>
Method : POST</br>
Body   : {
  "merchant_ref": "Astonishing-Sale",
  "transaction_type": "purchase",
  "method": "token",
  "amount": "200",
  "currency_code": "USD",
  "token": {
    "token_type": "FDToken",
    "token_data": {
      "type": "visa",
      "value": "2537446225198291",
      "cardholder_name": "JohnSmith",
      "exp_date": "1030"
    }
  }
}</br>

<b>http://localhost:8080/payment-service/transaction/authorize</b></br>
Method : POST</br>
Body :
{
  "merchant_ref": "Astonishing-Sale",
  "transaction_type": "authorize",
  "method": "token",
  "amount": "200",
  "currency_code": "USD",
  "token": {
    "token_type": "FDToken",
    "token_data": {
      "type": "visa",
      "value": "2537446225198291",
      "cardholder_name": "JohnSmith",
      "exp_date": "1030"
    }
  }
}

With H2 Database</br>
Use Maven 3.2.5 for build
