package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	 Faker faker;
	 User userpayload;
	 
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		 faker=new Faker();
		 userpayload=new User();  //pojo
		 
		 userpayload.setId(faker.idNumber().hashCode());
		 userpayload.setUsername(faker.name().fullName());
		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().safeEmailAddress());
		 userpayload.setPassword(faker.internet().password(5, 10));
		 userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		 
		 //logs
		 logger=LogManager.getLogger(this.getClass());
	}

	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("***********creating user***********");
		
		Response response = UserEndPoints.createUser(userpayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("***********user is created***********");
		
		
	}
	
	@Test(priority=2)
	public void testgetUserByName()
	{
		logger.info("***********Reading user info***********");
		
		Response response = UserEndPoints.readUser(this.userpayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("***********user info is displaying***********");
	}
	

	@Test(priority=3)
	public void testPutUserByName()
	{
		
		logger.info("***********update user***********");
		//update data using payload
		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().safeEmailAddress());
		 
		Response response = UserEndPoints.updateUser(userpayload,this.userpayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//checking data after updated
		
        Response responseAfterUpdated = UserEndPoints.readUser(this.userpayload.getUsername());
		
		Assert.assertEquals(responseAfterUpdated.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testdeleteUserByName()
	{
		
		logger.info("***********deleting user***********");
		
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
}
