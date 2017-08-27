package pratik.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo; 
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test; 
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;



public class GoogleRestAPI  
{ 
  GooglePlaceSearchAPI gplace;
 @BeforeTest 
 public void setUp()
 { 
     gplace=new GooglePlaceSearchAPI();

 }
 
  @Test  
  public void getRequestTest()
  { 
    gplace.getRequestPlaceSearch();

  }
  @Test  
  public void postRequest()
  {
    gplace.postRequestPlaceSearch(); 

  } 

  @Test  
  public void deleteRequest()  
  {  	 
    gplace.deleteRequestPlaceSearch();
  }
}
