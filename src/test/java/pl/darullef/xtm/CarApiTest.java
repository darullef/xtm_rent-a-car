package pl.darullef.xtm;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CarApiTest {

    @Test
    // car table must me empty
    public void getAllCarsWhileEmptyDBShouldBe404() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/car");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void getOneCarWhatDoesNotExistsShouldBe404() throws IOException {
        UUID uuid = UUID.randomUUID();
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/car/" + uuid);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void getOneCarShouldBe200() throws IOException, ParseException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/car");

        String jsonString1 = "{\r\n    \"made\": \"Volvo\",\r\n    \"model\": \"XC40\"\r\n}";
        StringEntity entity1 = new StringEntity(jsonString1);
        httpPost.setEntity(entity1);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response1 = client.execute(httpPost);

        String responseBody = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responseBody);
        UUID uuid = UUID.fromString(json.get("car_id").toString());

        HttpUriRequest request = new HttpGet("http://localhost:8080/api/car/" + uuid);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        System.out.println(uuid);

        Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void addCarToDbShouldBe201() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/car");

        String json = "{\r\n    \"made\": \"Volvo\",\r\n    \"model\": \"XC90\"\r\n}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());
    }

    @Test
    public void updateCarShouldBe200() throws IOException, ParseException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/car");

        String jsonString1 = "{\r\n    \"made\": \"Volvo\",\r\n    \"model\": \"XC40\"\r\n}";
        StringEntity entity1 = new StringEntity(jsonString1);
        httpPost.setEntity(entity1);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response1 = client.execute(httpPost);

        String responseBody = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responseBody);
        UUID uuid = UUID.fromString(json.get("car_id").toString());

        HttpPut httpPut = new HttpPut("http://localhost:8080/api/car/" + uuid.toString());
        String jsonString2 = "{\r\n    \"made\": \"Volvo\",\r\n    \"model\": \"S60\"\r\n}";
        StringEntity entity2 = new StringEntity(jsonString2);
        httpPut.setEntity(entity2);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        CloseableHttpResponse response2 = client.execute(httpPut);

        Assert.assertEquals(HttpStatus.SC_OK, response2.getStatusLine().getStatusCode());
    }

    @Test
    public void deleteCarShouldBe200() throws IOException, ParseException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/car");

        String jsonString = "{\r\n    \"made\": \"Volvo\",\r\n    \"model\": \"XC40\"\r\n}";
        StringEntity entity = new StringEntity(jsonString);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response1 = client.execute(httpPost);

        String responseBody = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responseBody);
        UUID uuid = UUID.fromString(json.get("car_id").toString());

        HttpDelete httpDelete = new HttpDelete("http://localhost:8080/api/car/" + uuid.toString());
        CloseableHttpResponse response2 = client.execute(httpDelete);
        System.out.println(uuid);

        Assert.assertEquals(HttpStatus.SC_OK, response2.getStatusLine().getStatusCode());
    }

    @Test
    public void checkCarContent() throws IOException, ParseException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/car");

        String jsonString1 = "{\r\n    \"made\": \"Volvo\",\r\n    \"model\": \"XC40\"\r\n}";
        StringEntity entity1 = new StringEntity(jsonString1);
        httpPost.setEntity(entity1);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response1 = client.execute(httpPost);

        String responseBody = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responseBody);
        String model = json.get("model").toString();

        Assert.assertEquals(model, "XC40");
    }
}
