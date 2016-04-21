Java, JPA, Spring profile, Spring Data JPA, Spring Rest, Spring Security.

<b>FOR QA Purpose: Eclipse setup is not required. Directly Run server from command prompt. </b></br>


<b>Run project directly from CMD on tomcat: </b></br>
1. <!-- By default port 8080--></br>
mvn tomcat:run</br>
2. <!-- change server port --></br>
mvn -Dmaven.tomcat.port=8181 tomcat:run</br>

Hit url : http://localhost:8080/payment-service/transaction/Ashish</br>

<b> Run project directly from CMD on Jetty: </b></br>
mvn package </br>
and then </br>
java -jar target/dependency/jetty-runner.jar target/*.war</br>

Hit service url without project name like below :</br>
http://localhost:8080/transaction/Ashish</br>



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


