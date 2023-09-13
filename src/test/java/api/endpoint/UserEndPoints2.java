package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//implementation of CRUD method(create,read,update,delete)

public class UserEndPoints2 {

	//method created for getting URL from properties file
	public static ResourceBundle getUrl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url= getUrl().getString("post_url");
		
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);
		
		return response;
	}
	 
	public static Response readUser(String userName)
	{
		String get_url= getUrl().getString("get_url");
		
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(get_url);
		
		return response;
		
	}
	
	public static Response updateUser(User payload,String userName)
	{
		String update_url= getUrl().getString("update_url");
		
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url= getUrl().getString("delete_url");
		
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(delete_url);
		
		return response;
		
	}
	
	
}
