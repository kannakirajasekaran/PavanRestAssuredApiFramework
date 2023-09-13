package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoint.UserEndPoints;
import api.payload.User;
import api.utility.DataProviders;
import io.restassured.response.Response;

public class DDtest {

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String userName,String fName,String lName,String Email,String pwd,String ph)
	{
		
		User userpayload=new User();
		
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fName);
		userpayload.setLastName(lName);
		userpayload.setEmail(Email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userpayload);
		
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
	
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
