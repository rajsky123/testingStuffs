package com.rajiv.test.RestAssuredTestNG;

import static org.junit.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AppTest 
    
{
    @Test(priority=3)
    public void testAppA()
    {
    	RestAssured.baseURI="http://demoqa.com/utilities/weather/city";
    	RequestSpecification req=RestAssured.given().log().all();
    	req.pathParam("cityNm", "Hyderabad");
    	Response response = req.get("/{cityNm}");
    	String j=response.getBody().asString();
    	System.err.println(JsonPath.given(j).get("Temperature"));
    	assertTrue(true);
    }
    
    @Test(groups="demo")
    public void testAppB() 
    {
    	RestAssured.given().pathParam("cityNm", "Hyderabad").when().get("http://demoqa.com/utilities/weather/city/{cityNm}").then().log().all()
    	.extract().path("Temperature");
 
    }
    
    
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod(){
    return new Object[][] {{2, 3 , 5}, {5, 7, 9}};
    }
    
      @Test (priority=1,dataProvider = "data-provider")
        public void myTest (int a, int b, int result) {
            int sum = a + b;
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(result, sum);
        }
}
