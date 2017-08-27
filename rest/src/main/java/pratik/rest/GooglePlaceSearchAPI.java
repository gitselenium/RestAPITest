package pratik.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class GooglePlaceSearchAPI {
  public static Response responseApi;
  public static JsonPath jsonPath;

  public  void getRequestPlaceSearch()
  {
    RestAssured.baseURI="https://maps.googleapis.com"; 
    given()
    .param("location", "20.6503455,85.5972218")
    .param("radius", "500")
    .param("type", "restaurant") 
    .param("key", "AIzaSyBD13yiw9fOHTS5jto3tkiuorfYmzZAO6I")
    .when() 
    .get("/maps/api/place/nearbysearch/json").then().and().
    assertThat().statusCode(200).and().contentType(ContentType.JSON).and().and().and().and().
    body("results[0].name",equalTo("Hotel Indu"));

  }
  @Test
  public void postRequestPlaceSearch()
  {

    RestAssured.baseURI="https://maps.googleapis.com"; 

      responseApi= given().queryParam("key", "AIzaSyBD13yiw9fOHTS5jto3tkiuorfYmzZAO6I").
        body("{"+
            "\"location\": {"+ 
            "\"lat\": 20.661636,"+
            "\"lng\": 85.577049 "+
            "},"+
            "\"accuracy\": 50,"+
            "\"name\": \"Pinku's Home\","+
            "\"phone_number\": \"(02) 9374 4000\","+
            "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
            "\"types\": [\"shoe_store\"],"+
            "\"website\": \"http://www.google.com.au/\","+
            "\"language\": \"en-AU\""+
            "} ").when().post("https://maps.googleapis.com/maps/api/place/add/json")
        .then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
        .body("status", equalTo("OK")).extract().response();

    String responseString=responseApi.asString();
    System.out.println(responseString);

      jsonPath=new JsonPath(responseString);
    System.out.println(jsonPath.get("place_id"));
  } 
  @Test
  public void deleteRequestPlaceSearch()  
  {
    postRequestPlaceSearch();
    RestAssured.baseURI="https://maps.googleapis.com"; 
    given().
    queryParam("key", "AIzaSyBD13yiw9fOHTS5jto3tkiuorfYmzZAO6I").
    body("{"+"\"place_id\":\""+jsonPath.get("place_id")+"\"}") 
    .when().post("https://maps.googleapis.com/maps/api/place/delete/json").andReturn().then().body("status",equalTo("OK"));

  }

}
