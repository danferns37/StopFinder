package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import javax.lang.model.element.TypeParameterElement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import Jsonmappedclasses.APIList;

import bsh.org.objectweb.asm.Type;

public class CommonFunctions<T> {
	 public  static <T>  T readJson(String json,Class<T> elementclass) throws FileNotFoundException  { 
		    GsonBuilder builder = new GsonBuilder(); 
		    Gson gson = builder.create(); 
		    File f = new File(json);
		  //  System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
		    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    URL resource = classLoader.getResource(f.getAbsolutePath());
		    BufferedReader bufferedReader = new BufferedReader(
		       new FileReader(json)); 
		   /* java.lang.reflect.Type listType = new TypeToken<Class<T>>(){}
		    .getParameterized(listType, elementclass)
		    */
		   
           
		 // java.lang.reflect.Type integerType = new TypeToken<T>() {}.getType(); 
		
		    
		    T t1 = gson.fromJson(bufferedReader, elementclass); 
		    
		    return t1; 
		    
		    
		  
		 }
	 
	 public  static   APIList readJson1(String json) throws FileNotFoundException   { 
		    GsonBuilder builder = new GsonBuilder(); 
		    Gson gson = builder.create(); 
		    File f = new File(json);
		//    System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
		    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    URL resource = classLoader.getResource(f.getAbsolutePath());
		    BufferedReader bufferedReader = new BufferedReader(
		       new FileReader(json)); 
		    
		
		    
		    APIList obj = gson.fromJson(bufferedReader, APIList.class); 
		  //  System.out.println("obj.stopFinder"+obj.getClass());
		   //System.out.println("obj.stopFinder"+obj.stopFinder);
		    return obj; 
		    
		    
		  
		 }
	
	
	
	
}
