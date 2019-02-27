/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlas.core;

import com.atlas.core.exceptions.IsNullException;
import com.atlas.core.utils.AtlasResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Raphson
 */
public class AtlasTest {
    
    @Test(expected = Exception.class)
    public void testNothingWasNotPassedToAtlasConstructor() throws Exception {
        new Atlas(null, null);
    }
    
    
    @Test
    public void testClassInstance() throws MalformedURLException , IsNullException {
        Atlas client = new Atlas(
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX");
        
        assert(client instanceof Atlas);
    }
    
    @Test
    public void testGetWith401Error() throws MalformedURLException , IsNullException, IOException {
        Atlas client = new Atlas(
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXXX");
        
    }
    
}
