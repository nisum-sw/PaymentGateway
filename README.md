# PaymentGateway
Firstdata
===
Sandbox
https://api-cert.payeezy.com/ v1
Live
https://api.payeezy.com/v1

Create an account  
===
Create an account to payeezy
https://developer.payeezy.com/faqs/what-payment-methods-and-integration-options-are-available-my-region

Create a project .
Once you are created the project for SANBOX click on the project that you created .You will see tabs
Click Keys
	API Key	xXBx7W1qlHGVEGfIOuWt3n9z4GHqWSRx
	API Secret	e332486420204aea5965ac46a13b9405fa16e8e57a16649f0e26394afda7b2c2
	Reporting Token	d32d5d7b23c3a9df
You have to get the Merchant KEY to identify API request 
Click on Merchants on top menu and you can see the Merchant Token
MerchantId:  3176752955
Merchant Name: Acme Sock
Token:fdoa-345720f7d0dc04150e161a3e6d5210bf345720f7d0dc0415
JS Security Key:js-836d6b7a9d10f8790462402af1b6f8a1836d6b7a9d10f879

In order to add merchant and go live with payeezy, we need to have certificates
Click on GetCertificate button and go down and click submit will generate certificate and Save

Test : 
AUTH : false : gives you a Token without $0 auth
 https://api-cert.payeezy.com/v1/securitytokens?apikey=
xXBx7W1qlHGVEGfIOuWt3n9z4GHqWSRx&js_security_key=js-836d6b7a9d10f8790462402af1b6f8a1836d6b7a9d10f879&callback=Payeezy.callback&typ
e=FDToken&credit_card.type=mastercard&credit_card.cardholder_name=kamal&credit_card
.card_number=5424180279791732&credit_card.exp_date=0416&credit_card.cvv=123&au
th=false&ta_token=NOIW

The token generated is valid for “authorize”, “purchase” and reversals (“capture”, “void” or “refund/settled”) transactions

Note: cvv check will not happen here and card information will not be validated.

AUTH : true : Gives you a FDToken with 0$ auth. The token generated is valid for “authorize” transaction only. 
https://api-cert.payeezy.com/v1/securitytokens?apikey=
xXBx7W1qlHGVEGfIOuWt3n9z4GHqWSRx&js_security_key=js-836d6b7a9d10f8790462402af1b6f8a1836d6b7a9d10f879&callback=Payeezy.callback&typ
e=FDToken&credit_card.type=mastercard&credit_card.cardholder_name=kamal&credit_card
.card_number=5424180279791732&credit_card.exp_date=0416&credit_card.cvv=123&au
th=true&ta_token=NOIW

GET /securitytokens
GET /transactions  - retrieve payment records. Supports complex filtering, sorting, pagination
	Eg: /transactions?start_date=20140817&end_date=20140819 
GET /transactions/{id} - 'capture' or 'void' an authorization or to 'refund' a charge.
POST /transactions
1.Submit payments credits. Supported transaction types are 'authorize' & 'purchase'
