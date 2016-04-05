# Payeezy bindings for WebBased app

Refer to the lastest Payeezy JS file for your development and Integration. 
*	Current version for merchants domiciled in US – v3.2
*	Current version for non-US domiciled merchants – v4.1

![alt tag](https://github.com/payeezy/payeezy_js/raw/master/ignore/PayeezyJS_GetToken.png)

1. Payeezy.js (which is included on the page using a &lt; script> tag) intercepts the form submit, and then
asynchronously posts the credit card details to Payeezy. This call uses JSONP over https, passing in API key and an
identifier– trtoken. Both of these values are provisioned for the merchant‘s developer via the developer portal.
2. On success Payeezy returns a token (type=FDtoken) to payeezy.js. Failure messages are returned in the response,
which can be handled as appropriate.
3. Payeezy.js then submits the form with the primary and secondary transactions as shown in the diagram above.


For more details on 'Payeezy JS' example refer [payeezy_js example](../../tree/master/example)

To run and test sample files online click - [codepen.io/payeezy](http://s.codepen.io/payeezyjs/debug/yNvVyy?) or visit [list of files to run/debug files online](http://codepen.io/payeezyjs/public-list/)

For more details on 'authorize' and 'purchase' transactions using javascript refer [nodeJS](https://github.com/payeezy/payeezy_direct_API/tree/master/payeezy_node/example/lib)

For more details on 'Payeezy JS Guide' refer [payeezy_js guide](https://github.com/payeezy/payeezy_js/raw/master/guide/payeezy_js07012015.pdf)

# Token Based Transactions - 
Token is generated for the card and the transactions are made using the token.US merchants will receive Transarmor multi-use tokens and EU merchants will receive Datavault tokens.

For Sample urls, [download](https://github.com/payeezy/payeezy_js/raw/master/guide/Payeezy_TokenBased_Transactions.pdf) or [visit](https://github.com/payeezy/payeezy_js/blob/master/guide/Payeezy_TokenBased_Transactions.pdf) Payeezy_TokenBased_Transactions.pdf 

For more information, visit payeezy js guide

# Payeezy_JS version
Payeezy_v1 and Payeezy_v2.1/Payeezy_v2.2 are deprecated (after 1-Jul-15 ) 

# Payeezy JS demo ? 

Visit [codepen.io](http://s.codepen.io/payeezyjs/debug/yNvVyy?)

# Getting Started with Payeezy
Using below listed steps, you can easily integrate your mobile/web payment application with Payeezy APIs and go LIVE!
*	LITE  - REGISTRATION  
*	GET CERTIFIED
*	ADD MERCHANTS 
*	LIVE!

![alt tag](https://github.com/payeezy/get_started_with_payeezy/raw/master/payeezy_flow_diagram.png)

For more information on getting started, visit  [get_start_with_payeezy guide] (https://github.com/payeezy/get_started_with_payeezy/blob/master/get_started_with_payeezy042015.pdf) or [download](https://github.com/payeezy/get_started_with_payeezy/raw/master/get_started_with_payeezy042015.pdf)

# Testing - Payeezy {SANDBOX region}
For test credit card,eCheck,GiftCard please refer to [test data ](https://github.com/payeezy/testing_payeezy/blob/master/payeezy_testdata042015.pdf) or [download] (https://github.com/payeezy/testing_payeezy/raw/master/payeezy_testdata042015.pdf)

# Error code/response - Payeezy {SANDBOX/PROD region}
For HTTP status code, API Error codes and error description please refer to [API error code ](https://developer.payeezy.com/payeezy_new_docs/apis) and select your API

#Questions?
We're always happy to help with code or other questions you might have! Check out [FAQ page](https://developer.payeezy.com/faq-page) or call us. 

## Contributing

1. Fork it 
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request  

## Feedback
We appreciate the time you take to try out our sample code and welcome your feedback. Here are a few ways to get in touch:
* For generally applicable issues and feedback, create an issue in this repository.
* support@payeezy.com - for personal support at any phase of integration
* [1.855.799.0790](tel:+18557990790)  - for personal support in real time 

## Terms of Use

Terms and conditions for using Payeezy JavaScript: Please see [Payeezy Terms & conditions](https://developer.payeezy.com/terms-use)
 
### License
The Payeezy JavaScript is open source and available under the MIT license. See the LICENSE file for more info.
