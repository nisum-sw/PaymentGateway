# Project: Payment Gateway
# Author: Vasantha Kumar

Scenario: Verify error message in generate token service with valid card number with valid expiry date and invalid CVV

Given user navigates to generate token service
When I enter valid card number
And I enter valid expiry date
And I enter invalid CVV
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.type		|visa							|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the error message as invalid CVV

Scenario: Verify error message in generate token service with valid card number with invalid expiry date and valid CVV

Given user navigates to generate token service
When I enter valid card number
And I enter invalid expiry date
And I enter valid CVV
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.type		|visa							|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the error message as invalid expiry date

Scenario: Verify error message in generate token service with valid card number with invalid expiry date and invalid CVV

Given user navigates to generate token service
When I enter valid card number
And I enter invalid expiry date
And I enter invalid CVV
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.type		|visa							|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the error message as invalid expiry date and CVV

Scenario: Verify error message in generate token service with valid card number with valid expiry date and valid CVV of expired credit card

Given user navigates to generate token service
When I enter expired card number
And I enter valid expiry date
And I enter valid CVV
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.type		|visa							|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the error message as credit card expired

Scenario: Verify error message in generate token service with valid card number with valid expiry date and valid CVV of blocked credit card

Given user navigates to generate token service
When I enter blocked card number
And I enter valid expiry date
And I enter valid CVV
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.type		|visa							|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the error message as blocked credit card

Scenario: Verify for success message in generate token service with valid card number with valid expiry date and valid CVV

Given user navigates to generate token service
When I enter valid card number
And I enter valid expiry date
And I enter valid CVV
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.type		|visa							|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the message as success

Scenario: Verify error message in generate token service with valid card number with valid expiry date valid CVV and invalid card type

Given user navigates to generate token service
When I enter valid card number
And I enter valid expiry date
And I enter valid CVV
And I enter invalid card type
And I enter other <"required"> fields with <"values">
|required			|values							|
|apikey 			|y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a			|
|js_security_key		|js-6125e57ce5c46e10087a545b9e9d7354c23e1a1670d9e9c7	|
|ta_token			|NOIW							|
|auth				|true							|
|callback			|Payeezy.callback					|
|type 				|FDToken						|
|credit_card.cardholder_name	|John Smith						|
And run the generate token service
Then I should see the error message as invalid card type

Scenario: Verify for error message in Authorize service with valid card token details and 0 amount transaction

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|0			|
|currency_code		|USD			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service
Then I should see the error message as invalid amount

Scenario: Verify for error message in Authorize service with valid card token details and amount as negative value

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|-200			|
|currency_code		|USD			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service
Then I should see the error message as invalid amount


Scenario: Verify for error message in Authorize service with valid card token details and amount in invalid format

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|200.122			|
|currency_code		|USD			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service
Then I should see the error message as invalid format

Scenario: Verify for error message in Authorize service with valid card token details and invalid currency

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|0			|
|currency_code		|UST			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service
Then I should see the error message as invalid currency

Scenario: Verify for error message in Authorize service with valid card token details and varying amount from actual transaction 300 instead of 200

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|300			|
|currency_code		|USD			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service
Then I should see the error message as invalid amount

Scenario: Verify for error message in Authorize service with valid card token details and when response inprogress prod the network 

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|200			|
|currency_code		|USD			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service
And prob the network while response inprogress
Then I should see the error message as network failure

Scenario: Verify for error message in Authorize service with valid card token details when the server is not up

Given user navigates to Authorize service
When I enter <"required"> fields with <"values">
|required		|values			|
|merchant_ref		|Astonishing-Sale	|
|transaction_type	|authorize		|
|method			|token			|
|amount			|200			|
|currency_code		|USD			|
|token_type		|FDToken		|
|type			|visa			|
|value			|2537446225198291	|
|cardholder_name	|JohnSmith		|
|exp_date		|1030			|
And run the Auhorize service when the server is not up
Then I should see the error message as server not up