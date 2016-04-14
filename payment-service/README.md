Java, JPA, Spring profile, Spring Data JPA, Spring Rest, Spring Security.

Rest urls:

headers</br>
 	Authorization: Basic dXNlcjp1c2VyUGFzcw==</br>
	Content-Type: application/json</br>

<b>http://localhost:8080/payment-service/transaction/{YOURNAME} </b></br>
Method : GET</br>


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


<b>http://localhost:8080/payment-service/transaction/capture</b></br>
Method : POST</br>
Body :
{
  "merchant_ref": "Astonishing-Sale",
  "transaction_id": "ET174457",
  "transaction_tag": "1871007",
  "transaction_type": "capture",
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

<b>http://localhost:8080/payment-service/transaction/void</b></br>
Method : POST</br>
Body :
{
  "merchant_ref": "Astonishing-Sale",
  "transaction_id": "ET174457",
  "transaction_tag": "1871010",
  "transaction_type": "void",
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

<b>http://localhost:8080/payment-service/transaction/refund</b></br>
Method : POST</br>
Body :
{
  "merchant_ref": "Astonishing-Sale",
  "transaction_id": "ET174457",
  "transaction_type": "refund",
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


FOR QA Purpose: Eclipse setup is not required. Directly Run server from command prompt.

mvn package
and then 
java -jar target/dependency/jetty-runner.jar target/*.war

Hit service url without project name like below :
http://localhost:8080/transaction/Ashish

