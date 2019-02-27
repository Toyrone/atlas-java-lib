/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlas.core;

import com.google.common.base.Strings;
import com.atlas.core.connection.AtlasClient;
import com.atlas.core.exceptions.IsNullException;
import com.atlas.core.utils.AtlasResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 *
 * @author Raphson
 */
public class Atlas {
    
    /**
     * public key
     * @var String
     */
    private String apiKey;
    
    /**
     * Access Token
     * @var String
     */
    private String secretKey;
    
    /**
     * Instance of AtlasClient
     * @var object
     */
    private AtlasClient client = null;
    
    
    public Atlas(String apiKey, String secretKey) throws IsNullException, MalformedURLException{
        if(Strings.isNullOrEmpty(apiKey))
            throw new IsNullException("The API Key can not be null or empty. Please pass it to the constructor");
        
        if(Strings.isNullOrEmpty(secretKey))
            throw new IsNullException("The Secret key can not be null or empty. Please pass it to the constructor");
        
        
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        
        client = new AtlasClient(this.apiKey, this.secretKey);
    }
    
    
    /**
     * Send SMS using the Atlas API
     * @param payload
     * @return 
     * @throws com.atlas.core.exceptions.IsNullException
     * @throws java.net.MalformedURLException
     */
    public AtlasResponse sendSMS(Map<String, String> payload) 
            throws IsNullException, MalformedURLException, IOException{
        if (this.isNullOrEmpty(payload)) {
            throw new IsNullException("Message Payload can not be empty. Please fill the appropriate details");
        }
        
        return client.performPostRequest("/sms/send_sms", payload);
    }
 
    /**
     * check if map collection is empty...
     * @param m
     * @return 
     */
    public boolean isNullOrEmpty( final Map< ?, ? > m ) {
        return m == null || m.isEmpty();
    }
    
}
