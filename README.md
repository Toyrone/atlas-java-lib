### Atlas-java-lib

 > Atlas Library for JAVA☕️. This is based on the Official API Documentation provided by [Atlas](https://sms.atlas.money) an African SMS Service.


### Registration

First, you will need to first create an account at [atlas.money](https://sms.atlas.money/signup/) and obtain your Public key and Access Token.

Once you have created an account, you can access your Api key and secret key.

# Installation

## Prerequisites

- Java version Oracle JDK 7, 8 or OpenJDK 7

### Maven
Include the following in your `pom.xml` for Maven:

```xml
<dependency>
    <groupId>com.atlas</groupId>
    <artifactId>atlas-java-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

###Jar-File
You can also download the jar file from the latest release on the [releases page](https://github.com/toyrone/atlas-java-lib/releases).

##Usage
A Atlas class provides three public methods for accessing the API. Instantianting the class is as given below:


```java
final Atlas client = new Atlas();
```

Its constructor takes two string parameters:

```java
public final String PUBLIC_KEY = "[Enter Public Key Here]";
public final String ACCESS_TOKEN = "[Enter Access Token]";

final Atlas client = new Atlas(apiKey, secretKey);
```

### Atlas Class Methods
Its methods are:

#### Send SMS
This lets you make a request to the Atlas API, to send an SMS. It takes a `java.util.Map<String, String>` object as a parameter and returns a `com.atlas.core.utils.AtlasResponse` object.

##### Usage
```java
try {
    final Atlas client = new Atlas("{API_KEY}", "{SECRET_KEY}");
    final Map<String, String> smsParams = new HashMap<String, String>();

    smsParams.put("to", "XXXXXXXXXX"); // Replace with a valid phone number
    smsParams.put("from", "John Doe"); // Replace with a valid Sender
    smsParams.put("message", "Welcome to Atlas SMS lib");

    AtlasResponse smsResponse = client.sendSMS(smsParams);
    System.out.println(smsResponse.toString());

    JSONObject smsResultObject = (JSONObject)new JSONParser().parse(smsResponse.getResponseMessage());
    if(smsResponse.getResponseCode() == 200){
        System.out.println("your SMS Message ID is " + smsResultObject.get("message_id"));
        System.out.println("your SMS Status is " + smsResultObject.get("status"));
        System.out.println("SMS credit used is " + smsResultObject.get("sms_credits_used"));
        System.out.println("your request speed is " + smsResultObject.get("request_speed"));
    } else {
        System.out.println(smsResultObject.get("error"));
    }
} catch (IsNullException ex) {
    System.out.println(ex.getMessage());
} catch (IOException ex) {
    System.out.println(ex.getMessage());
} catch (ParseException ex) {
    System.out.println(ex.getMessage());
}



### Atlas Models
You may have seen references to Classes such as `com.atlas.core.utils.AtlasResponse` in above sections of this documents.
- `AtlasResponse` class
      - `string responseCode`
      - `string responseMessage`
      - `string getResponseCode()`
      - `setResponseCode(int responseCode)`
      - `string getResponseMessage()`
      - `setResponseMessage(String responseMessage)`

# Example
Here is an example

```java
package com.atlas.core.example;

import com.atlas.core.Atlas;
import com.atlas.core.exceptions.IsNullException;
import com.atlas.core.utils.ClientResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Toyosi
 */
public class App {
    public static final String API_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static final String SECRET_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main( String[] args ){
        try {
            final Atlas client = new Atlas(API_KEY, SECRET_KEY);
            final Map<String, String> smsParams = new HashMap<String, String>();

            smsParams.put("to", "xxxxxxxxxxxx"); // Replace with a valid phone number
            smsParams.put("from", "toyrone"); // Replace with a valid phone number in your account
            smsParams.put("message", "Welcome to Atlas JAVA lib");

            AtlasResponse smsResponse = client.sendSMS(smsParams);
            System.out.println(smsResponse.toString());

            JSONObject smsResultObject =
                    (JSONObject)new JSONParser().parse(smsResponse.getResponseMessage());
            if(smsResponse.getResponseCode() == 200){
                System.out.println("your SMS Message ID is " + smsResultObject.get("message_id"));
                System.out.println("your SMS Status is " + smsResultObject.get("status"));
                System.out.println("SMS credit used is " + smsResultObject.get("sms_credits_used"));
                System.out.println("your request speed is " + smsResultObject.get("request_speed"));
            } else {
                System.out.println(smsResultObject.get("error"));
            }


            AtlasResponse balResponse = client.checkAvailableCredits();
            System.out.println(balResponse.toString());
            JSONObject balResultObject =
                        (JSONObject)new JSONParser().parse(balResponse.getResponseMessage());
            if(balResponse.getResponseCode() == 200){
                System.out.println("your SMS balance is " + balResultObject.get("sms_credits"));
                System.out.println("your request speed is " + balResultObject.get("request_speed"));
            } else {
                System.out.println(balResultObject.get("error"));
            }


            AtlasResponse deliveryResponse =
                    client.checkDeliveryStatus("w719zxz58q");
            System.out.println(deliveryResponse.toString());
            JSONObject deliveryResultObject =
                        (JSONObject)new JSONParser().parse(deliveryResponse.getResponseMessage());
            if(deliveryResponse.getResponseCode() == 200){
                System.out.println("your SMS Status is " + deliveryResultObject.get("status"));
                System.out.println("your SMS Message ID is " + deliveryResultObject.get("message_id"));
                System.out.println("your SMS Sent Date is " + deliveryResultObject.get("date_sent"));
                System.out.println("your SMS Delivered Date is "
                        + deliveryResultObject.get("date_delivered"));
                System.out.println("your request speed is " + deliveryResultObject.get("request_speed"));
            } else {
                System.out.println(deliveryResultObject.get("error"));
            }


        } catch (IsNullException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
```
## Contributing

## License

The MIT License (MIT). Please see [License File](LICENSE.md) for more information.
