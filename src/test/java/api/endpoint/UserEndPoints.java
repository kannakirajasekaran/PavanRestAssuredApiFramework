package api.endpoint;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//implementation of CRUD method(create,read,update,delete)

public class UserEndPoints {

	
	public static Response createUser(User payload)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return response;
	}
	 
	public static Response readUser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(Routes.get_url);
		
		return response;
		
	}
	
	public static Response updateUser(User payload,String userName)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(Routes.delete_url);
		
		return response;
		
	}
	
	
}
