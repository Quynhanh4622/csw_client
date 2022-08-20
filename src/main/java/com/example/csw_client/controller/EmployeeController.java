package com.example.csw_client.controller;



import com.example.csw_client.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class EmployeeController {

    private final String REST_API_LIST = "http://localhost:8080/employee";
    private final String REST_API_CREATE = "http://localhost:8080/create";
    private final String REST_API_DELETE = "http://localhost:8080/delete/";
    private final String REST_API_UPDATE = "http://localhost:8080/update/";
    private final String REST_API_GET_ID = "";

    private static Client createJerseyRestClient() {
        ClientConfig clientConfig = new ClientConfig();

        // Config logging for client side
        clientConfig.register( //
                new LoggingFeature( //
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), //
                        Level.INFO, //
                        LoggingFeature.Verbosity.PAYLOAD_ANY, //
                        10000));

        return ClientBuilder.newClient(clientConfig);
    }

    @GetMapping(value = { "/list"})
    public String index(Model model) {
        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_LIST);
        List<Employee> list =  target.request(MediaType.APPLICATION_JSON_TYPE).get(List.class);

        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "create")
    public String create() {
        return "create";
    }

    @RequestMapping(value = "save")
    public String save(@RequestParam String name, @RequestParam String salary) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);

        String jsonEmployee = convertToJson(employee);
        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_CREATE);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(jsonEmployee, MediaType.APPLICATION_JSON));
        return "redirect:/";
    }

    @RequestMapping(value = "delete")
    public String delete(Integer id) {
        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_DELETE + id);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).delete();
        return "redirect:/";
    }

    private static String convertToJson(Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}