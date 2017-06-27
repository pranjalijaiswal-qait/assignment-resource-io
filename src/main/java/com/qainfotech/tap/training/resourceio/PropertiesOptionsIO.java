package com.qainfotech.tap.training.resourceio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
	Properties prop=new Properties();
	FileInputStream input=null;
	FileOutputStream output=null;
	
    
    public Object getOptionValue(String optionKey) throws IOException 
    {
    	try
    	{
    	input=new FileInputStream("C:/Users/pranjalijaiswal/git/assignment-resource-io/src/main/resources/options.properties");
    	prop.load(input);
        return  (Object)prop.getProperty(optionKey);
    	}
    	catch(Exception e)
    	{
        throw new UnsupportedOperationException("Not implemented.");
    	}
    }

    public void addOption(String optionKey, Object optionValue) throws IOException 
    {
    try
    {
    	output=new FileOutputStream("C:/Users/pranjalijaiswal/git/assignment-resource-io/src/main/resources/options.properties", true);
    	prop.setProperty(optionKey, (String)optionValue);
    	prop.store(output, null);
    }
    	catch(Exception e)
    	{
        throw new UnsupportedOperationException("Not implemented.");
    	}
    }
}
