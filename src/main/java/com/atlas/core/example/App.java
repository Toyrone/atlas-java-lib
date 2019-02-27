package com.atlas.core.example;



import com.atlas.core.Atlas;
import com.atlas.core.exceptions.IsNullException;
import com.atlas.core.utils.AtlasResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String API_KEY = "09de5bec3937d2d032d8a75b41f9081b";
    public static final String SECRET_KEY = "68501b2b40767fc51e4f936d7db40fc0";
  
    public static void main( String[] args ){
        try {
            final Atlas client = new Atlas(API_KEY, SECRET_KEY);
            final Map<String, String> smsParams = new HashMap<String, String>();
            
            smsParams.put("to", "08091167643"); // Replace with a valid phone number
            smsParams.put("from", "Toyrone"); // Replace with a valid phone number in your account
            smsParams.put("message", "Welcome to Atlas Java lib");
            
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
            
            
        } catch (IsNullException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
