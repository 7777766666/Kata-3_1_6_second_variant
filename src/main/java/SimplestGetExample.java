

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import entity.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SimplestGetExample {

    static final String URL_EMPLOYEES = "http://94.198.50.185:7081/api/users";

    static final String URL_DELETE = "http://94.198.50.185:7081/api/users/3";
    private static final String FILE_NAME = "C:\\Users\\shara\\Documents\\Алексей\\Words\\text15";
    



  /*  Добавление пользователя - …/api/users ( POST )
    Изменение пользователя - …/api/users ( PUT )
    Удаление пользователя - …/api/users /{id} ( DELETE )
    */


    public static void main(String[] args) throws IOException {


        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entity, String.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);

        // Status Code: 200
        if (statusCode == HttpStatus.OK) {
            // Response Body Data
            String result = response.getBody();
            System.out.println(result);


        }


        HttpHeaders header = response.getHeaders();
        List<String> cookies = header.get("Set-Cookie");


        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        //  headers.add ("Cookie", String.valueOf(header.get("Set-Cookie")));

        User newUser = new User(3, "James", "Brown", 30);

        // Data attached to the request.
        // HttpEntity<Employee> requestBody = new HttpEntity<>(newEmployee, headers);

        HttpEntity<User> entity1 = new HttpEntity<>(new User(3, "James", "Brown", 30), headers);
        // Send request with POST method.
        ResponseEntity<String> response2 = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.POST, entity1, String.class);


        System.out.println(response2.getBody());


        //  HttpEntity<Employee> requestBody2 = new HttpEntity<>(newEmployee, headers);

        HttpEntity<User> entity2 = new HttpEntity<>(new User(3, "Thomas", "Shelby", 30), headers);
        // Send request with POST method.
        ResponseEntity<String> response3 = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.PUT, entity2, String.class);


        System.out.println(response3.getBody());


        //  HttpEntity<Employee> requestBody3 = new HttpEntity<>(newEmployee, headers);

        HttpEntity<User> entity3 = new HttpEntity<>(new User(3, "Thomas", "Shelby", 30), headers);
        // Send request with POST method.
        ResponseEntity<String> response4 = restTemplate.exchange(URL_DELETE, HttpMethod.DELETE, entity3, String.class);


        System.out.println(response4.getBody());

        }

    }


}

