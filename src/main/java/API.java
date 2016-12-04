package main.java;

import static com.mongodb.client.model.Filters.eq;
import static spark.Spark.*;

import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import org.bson.BSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;


 
public class API {
	
public static Boolean valid=true;	


	public static void main(String[] args) {
		
		port(Integer.valueOf(System.getenv("PORT")));
		//MongoConnection mc = new MongoConnection("mongodb://test:test@ds143777.mlab.com:43777/heroku_6shk89xn", "heroku_6shk89xn");


		get("/login/:Username/:Password", (request, response) -> {
			String username = request.params(":Username");
			String password = request.params(":Password");
			System.out.println(""+username+" "+password);

				    try {
				    	MongoClientURI uri  = new MongoClientURI("mongodb://test:test@ds143777.mlab.com:43777/heroku_6shk89xn"); 
				        MongoClient client = new MongoClient(uri);
				        DB db = client.getDB(uri.getDatabase());
				        DBCollection user = db.getCollection("Users");
				        
				        

				        BasicDBObject findQuery = new BasicDBObject("Username", username);
				        

				        DBCursor docs = user.find(findQuery);
				        
				        while(docs.hasNext()){
				        	
				            DBObject doc = docs.next();
				            
				            	
				            if(username.equals(doc.get("Username"))){
				            	
				            	valid=false;
				            	return doc.get("Role");
				            }
				        }    		  			    				    			    	                
					    } catch (MongoException e) {
				    	e.printStackTrace();
				        }
			
			
			return "";
			
			
		});
		
		// basic help response to a blank call to the webpage
		get("/test/:Username/:Password/:email/:role", (request, response) -> {
			String username = request.params(":Username");
			String password = request.params(":Password");
			String role = request.params(":role");
			String email = request.params(":email");
			System.out.println(""+username+" "+password+" "+role+" "+email);

				    try {
				    	MongoClientURI uri  = new MongoClientURI("mongodb://test:test@ds143777.mlab.com:43777/heroku_6shk89xn"); 
				        MongoClient client = new MongoClient(uri);
				        DB db = client.getDB(uri.getDatabase());
				        DBCollection user = db.getCollection("Users");
				        
				        BasicDBObject findQuery = new BasicDBObject("Username", username);
				        

				        DBCursor docs = user.find(findQuery);
				        
				        while(docs.hasNext()){
				        	
				            DBObject doc = docs.next();
				            if(username.equals(doc.get("Username"))){
				            	
				            	valid=false;
				            	return "Duplicate";
				            }
				           
				        }
				        if(valid=true){
					    	
			            	/**** Insert ****/
					    	// create a document to store key and value
					    	BasicDBObject document = new BasicDBObject();
					    	document.put("Username", username);
					    	document.put("Password", password);
					    	document.put("Email", email);
					    	document.put("Role", role);				    	
					    	
					    	user.insert(document);
			            	return "success";
			            
			    }
					
				    } catch (MongoException e) {
				    	e.printStackTrace();
				        }
				    
			
			return "";
			
			
		});
		
		get("/rota/:username/:dayOfWeek/:date/:time/:details/:hours", (request, response) -> {
			String dayOfWeek = request.params(":dayOfWeek");
			String username = request.params(":username");
			String date = request.params(":date");
			String time = request.params(":time");
			String details = request.params(":details");
			String hours = request.params(":hours");
			System.out.println(""+username+" "+date+" "+time+" "+details+" "+hours);
			
			try {
		    	MongoClientURI uri  = new MongoClientURI("mongodb://test:test@ds143777.mlab.com:43777/heroku_6shk89xn"); 
		        MongoClient client = new MongoClient(uri);
		        DB db = client.getDB(uri.getDatabase());
		        DBCollection user = db.getCollection("Users");
		        
		        BasicDBObject findQuery = new BasicDBObject("Username", username);
		        

		        DBCursor docs = user.find(findQuery);
		        
		        while(docs.hasNext()){
		        	
		            DBObject doc = docs.next();
		            if(username.equals(doc.get("Username"))){
		            	BasicDBObject set = new BasicDBObject("$set", new BasicDBObject("Username", username));
		            	
				    	
				    	switch (dayOfWeek) {
				    	case "Monday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Monday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;
							
				    	case "Tuesday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Tuesday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;
				    	case "Wednesday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Wednesday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;
				    	case "Thursday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Thursday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;
				    	case "Friday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Friday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;
				    	case "Saturday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Saturday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;
				    	case "Sunday":
				    		set.append("$set", new BasicDBObject("Rota", new BasicDBObject()));
							set.append("$set", new BasicDBObject("Sunday", new BasicDBObject("Date", date)
							.append("Time", time)
							.append("Details", details)
							.append("Hours", hours)));	
							break;

						default:
							break;
						}
				    	
		            	user.update(docs.curr(), set);
		            	return "success";
		            	
		            }
		           
		        }
		                  
		        	
			
		    } catch (MongoException e) {
		    	e.printStackTrace();
		        }
		    
	
	return "";
	
		});
		
		
		get("/viewRota/:username", (request, response) -> {
			String username = request.params(":username");			
			System.out.println(""+username);
			
			try {
		    	MongoClientURI uri  = new MongoClientURI("mongodb://test:test@ds143777.mlab.com:43777/heroku_6shk89xn"); 
		        MongoClient client = new MongoClient(uri);
		        DB db = client.getDB(uri.getDatabase());
		        DBCollection user = db.getCollection("Users");
		        
		        BasicDBObject findQuery = new BasicDBObject("Username", username);
		        

		        DBCursor docs = user.find(findQuery);
		        
		        while(docs.hasNext()){
		        	
		            DBObject doc = docs.next();
		            if(username.equals(doc.get("Username"))){
		            	 ArrayList<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		            	 obj.add((BasicDBObject) doc.get("Monday"));
		            	 obj.add((BasicDBObject) doc.get("Tuesday"));
		            	 obj.add((BasicDBObject) doc.get("Wenesday"));
		            	 obj.add((BasicDBObject) doc.get("Thursday"));
		            	 obj.add((BasicDBObject) doc.get("Friday"));
		            	 obj.add((BasicDBObject) doc.get("Saturday"));
		            	 obj.add((BasicDBObject) doc.get("Sunday"));
		            	return obj ;
		            	
		            	

						}
				    	
		            	
		            	//return test;
		            	
		            }
		           
		        }
		        catch (MongoException e) {
		    	e.printStackTrace();
		        }
		    
	
	return "";
	
		});
		
		get("/getusername/", (request, response) -> {
			String username = request.params(":username");			
			System.out.println(""+username);
			
			try {
		    	MongoClientURI uri  = new MongoClientURI("mongodb://test:test@ds143777.mlab.com:43777/heroku_6shk89xn"); 
		        MongoClient client = new MongoClient(uri);
		        DB db = client.getDB(uri.getDatabase());
		        DBCollection user = db.getCollection("Users");
		        
		        
		        
		        
		        BasicDBObject allQuery = new BasicDBObject();
		        BasicDBObject fields = new BasicDBObject();
		        fields.put("Username", 1);
		        ArrayList<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		        DBCursor docs = user.find(allQuery, fields);
		        while (docs.hasNext()) {
		        	//System.out.println(docs.next());
		        	
	            	 obj.add((BasicDBObject) docs.next());
	            	
	            	 System.out.println(obj);
	             
		        }
		        return obj ;
		           
		        }
		        catch (MongoException e) {
		    	e.printStackTrace();
		        }
		    
	
	return "";
	
		});
		
		
		
		

		
	}
}