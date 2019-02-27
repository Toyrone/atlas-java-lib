/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlas.core.connection;

import com.atlas.core.utils.AtlasResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Raphson
 */
public class AtlasClient {
    
    /**
     * Atlas API Base URL 
     */
    private final String baseURL = "https://devsms.atlas.money/v1";
    
    /**
     * HttpClient User Agent
     */
    private final String USER_AGENT = "Mozilla/5.0";
    
    
    /**
     * Instance of HttpClient
     */
    private HttpClient client = null;
    
    
    /**
     * Instance of HttpResponse
     */
    private HttpResponse response = null;
    
    
    /**
     * Response Code
     */
    private int responseCode;
    
    
    /**
     * Instance of StringBuffer
     */
    private StringBuffer result = null;
    
    
    /**
     * Auth Encoding 
     */
    private String encoding = null;
    
    
    /**
     * class constructor
     * @param apiKey
     * @param secretKey
     * @throws java.net.MalformedURLException
     */
    public AtlasClient(String apiKey, String secretKey) throws MalformedURLException{
        encoding = Base64.encodeBase64String((apiKey + ":" + secretKey).getBytes());
        client = HttpClientBuilder.create().build();
    }
  
    /**
     * Perform a POST request
     * @param relativeUrl
     * @param data
     * @return 
     * @throws java.io.UnsupportedEncodingException 
     */
    public AtlasResponse performPostRequest(String relativeUrl, Map<String, String> data) 
            throws UnsupportedEncodingException, IOException {
        HttpPost request = new HttpPost(baseURL + relativeUrl);

        // add header
        request.setHeader("User-Agent", USER_AGENT);
        request.setHeader("Authorization", "Basic " + encoding);
        
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("to", data.get("to")));
        urlParameters.add(new BasicNameValuePair("from", data.get("from")));
        urlParameters.add(new BasicNameValuePair("message", data.get("message")));
        request.setEntity(new UrlEncodedFormEntity(urlParameters));

        response = client.execute(request);
        responseCode = response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null)
            result.append(line);
        
        
        return new AtlasResponse(responseCode, result.toString());     
    }
}
