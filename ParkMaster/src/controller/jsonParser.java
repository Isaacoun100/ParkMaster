package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.other.*;
import model.*;

import static java.lang.String.valueOf;

public class jsonParser {

    static String customerFile = "src/misc/customer.json";

    public static Customer loginCustomer( int password, String email ) throws IOException, ParseException {

        System.out.println("Attempting to read filed");
        Object array = new JSONParser().parse(new FileReader(customerFile));
        System.out.println("File read");
        JSONArray newArray = (JSONArray) array;

        for(Object item : newArray) {

            JSONObject jsonObject = (JSONObject) item;

            if( valueOf(jsonObject.get("email")).equals(email) && Integer.parseInt(valueOf(jsonObject.get("PIN"))) == password ) {

                JSONObject paymentMethod = (JSONObject) jsonObject.get("paymentMethod");
                JSONArray vehicles = (JSONArray) jsonObject.get("vehicles");

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
                        valueOf(jsonObject.get("name")),
                        valueOf(jsonObject.get("lastName")),
                        valueOf(jsonObject.get("phoneNumber")),
                        valueOf(jsonObject.get("email")),
                        valueOf(jsonObject.get("billingAddress")),
                        valueOf(jsonObject.get("id")),
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

    public Admin loginAdmin( String password, String email ){
        return null;
    }

    public Inspector loginInspector( String password, String email ){
        return null;
    }

}
