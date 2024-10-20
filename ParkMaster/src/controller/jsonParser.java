package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.other.*;
import model.*;

import static java.lang.String.valueOf;

public class jsonParser {

    // This are the files that will be accessed
    static String customerFile = "src/misc/customer.json";
    static String inspectorFile = "src/misc/inspector.json";
    static String adminFile = "src/misc/admin.json";

    /**
     * This method will receive a password and email and will look up in the file if the
     * customer exists in the JSON file
     * @param password The password from the client
     * @param email The email the client used
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static Customer loginCustomer( String password, String email ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(customerFile));
        JSONArray customerList = (JSONArray) array;

        for(Object item : customerList) {

            JSONObject customer = (JSONObject) item;

            if( valueOf(customer.get("email")).equals(email) && valueOf(customer.get("PIN")).equals(password) ) {

                JSONObject paymentMethod = (JSONObject) customer.get("paymentMethod");
                JSONArray vehicles = (JSONArray) customer.get("vehicles");

                ArrayList<Vehicle> vehicleList = new ArrayList<>();

                for (int i = 0; i < vehicles.size() ; i++) {

                    JSONObject vehicle = (JSONObject) vehicles.get(i);
                    Vehicle vehicleObj = new Vehicle(
                            valueOf(vehicle.get("vehicleID")),
                            valueOf(vehicle.get("Brand")),
                            valueOf(vehicle.get("Model"))
                    );
                    vehicleList.add(vehicleObj);
                }

                return new Customer(
                        valueOf(customer.get("name")),
                        valueOf(customer.get("lastName")),
                        valueOf(customer.get("phoneNumber")),
                        email,
                        valueOf(customer.get("billingAddress")),
                        valueOf(customer.get("id")),
                        password,
                        new PaymentMethod(
                                (String)paymentMethod.get("cardNumber"),
                                (String)paymentMethod.get("expiryDate"),
                                (String)paymentMethod.get("cvv")
                        ),
                        vehicleList
                );
            }

        }

        return null;
    }


    public static Admin loginAdmin( String password, String email ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(adminFile));
        JSONArray adminList = (JSONArray) array;

        for(Object item : adminList) {

            JSONObject admin = (JSONObject) item;

            if( valueOf(admin.get("email")).equals(email) && valueOf(admin.get("PIN")).equals(password) ) {

                return new Admin(
                        valueOf(admin.get("name")),
                        valueOf(admin.get("lastName")),
                        valueOf(admin.get("phoneNumber")),
                        email,
                        valueOf(admin.get("billingAddress")),
                        valueOf(admin.get("id")),
                        password,
                        LocalDate.parse(valueOf(admin.get("hireDate")))
                );

            }

        }

        return null;
    }

    public static Inspector loginInspector( String password, String email ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(inspectorFile));
        JSONArray inspectorList = (JSONArray) array;

        for(Object item : inspectorList) {

            JSONObject inspector = (JSONObject) item;

            if( valueOf(inspector.get("email")).equals(email) && valueOf(inspector.get("PIN")).equals(password) ) {

                return new Inspector(
                        valueOf(inspector.get("name")),
                        valueOf(inspector.get("lastName")),
                        valueOf(inspector.get("phoneNumber")),
                        email,
                        valueOf(inspector.get("billingAddress")),
                        valueOf(inspector.get("id")),
                        password,
                        LocalDate.parse(valueOf(inspector.get("hireDate"))),
                        valueOf(inspector.get("terminal"))
                );

            }

        }

        return null;
    }

}
