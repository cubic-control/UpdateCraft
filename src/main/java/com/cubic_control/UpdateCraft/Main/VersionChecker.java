package com.cubic_control.UpdateCraft.Main;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

public class VersionChecker implements Runnable{
    private static boolean isLatestVersion = false;
    private static String latestVersion = "";
    
    @Override
    public void run() {
        InputStream in = null;
        try{
            in = new URL("").openStream();
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        try{
            latestVersion = IOUtils.readLines(in).get(0);
        }catch(IOException e) {
            e.printStackTrace();
        }
        finally{
            IOUtils.closeQuietly(in);
        }
        System.out.println(RefStrings.NAME+":Latest mod version = "+latestVersion);
        System.out.println(RefStrings.NAME+":Current mod version = "+RefStrings.VERSION);
        isLatestVersion = RefStrings.VERSION.equals(latestVersion);
        System.out.println(RefStrings.NAME+":Are you running latest version = "+isLatestVersion);
    }
    
    public boolean isLatestVersion() {
    	return isLatestVersion;
    }
    
    public String getLatestVersion() {
    	return latestVersion;
    }
}