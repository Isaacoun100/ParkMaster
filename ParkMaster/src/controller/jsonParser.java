package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import model.other.*;
import model.*;

import static java.lang.String.valueOf;

public class jsonParser {

    // These are the files that will be accessed
    static String customerFile = "src/misc/customer.json";
    static String inspectorFile = "src/misc/inspector.json";
    static String adminFile = "src/misc/admin.json";

    /**
     * This method will receive a password and email and will look up in the file if the
     * customer exists in the JSON file
     * @param password The password from the client
     * @param email The email the client used
     * @return A Customer object that contains the customer information or a NULL if none
     * was found
     * @throws IOException If the file is not found
     * @throws ParseException If the file cannot be parsed
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

                for (Object o : vehicles) {

                    JSONObject vehicle = (JSONObject) o;
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


    /**
     * This method will receive a password and email and will look up in the file if the
     * admin exists in the JSON file
     * @param password Admin password to check up
     * @param email Email to check up
     * @return An Admin object with the admin information or a NULL if none was found
     * @throws IOException No file was found
     * @throws ParseException Unable to parse the file
     */
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

    /**
     * This method will receive a password and email and will look up in the file if the
     * inspector exists in the JSON file
     * @param password The inspector password
     * @param email The inspector email
     * @return An Inspector object that contains the user information or a NULL if
     * none was found
     * @throws IOException If the file was not found
     * @throws ParseException If the file could not be parsed
     */
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

    public static void addCustomer( String name, String lastName, String phoneNumber, String email, String billingAddress, String id, String PIN, String cardNumber, String expirityDate, String ccv, ArrayList<Vehicle> vehicleList) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(customerFile));
        JSONArray customerList = (JSONArray) array;

        JSONObject paymentMethod = new JSONObject();
        paymentMethod.put("cardNumber", cardNumber);
        paymentMethod.put("expiryDate", expirityDate);
        paymentMethod.put("cvv", ccv);

        JSONArray vehicles = new JSONArray();

        for( Vehicle vehicle : vehicleList ) {
            JSONObject vehicleObj = new JSONObject();
            vehicleObj.put("vehicleID", valueOf(vehicle.getVehicleID()));
            vehicleObj.put("Brand", valueOf(vehicle.getBrand()));
            vehicleObj.put("Model", valueOf(vehicle.getModel()));
            vehicles.add(vehicleObj);
        }

        JSONObject newCustomer = new JSONObject();
        newCustomer.put("name", name);
        newCustomer.put("lastName", lastName);
        newCustomer.put("phoneNumber", phoneNumber);
        newCustomer.put("email", email);
        newCustomer.put("billingAddress", billingAddress);
        newCustomer.put("id", id);
        newCustomer.put("PIN", PIN);
        newCustomer.put("paymentMethod", paymentMethod);
        newCustomer.put("vehicles", vehicles);
        customerList.add(newCustomer);

        PrintWriter pw = new PrintWriter(customerFile);
        pw.write(customerList.toJSONString());

        pw.flush();
        pw.close();

    }

    public static void addAdmin(String name, String lastName, String phoneNumber, String email, String billingAddress, String id, String PIN, LocalDate hireDate) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(adminFile));
        JSONArray adminList = (JSONArray) array;

        JSONObject newAdmin = new JSONObject();
        newAdmin.put("name", name);
        newAdmin.put("lastName", lastName);
        newAdmin.put("phoneNumber", phoneNumber);
        newAdmin.put("email", email);
        newAdmin.put("billingAddress", billingAddress);
        newAdmin.put("id", id);
        newAdmin.put("PIN", PIN);
        newAdmin.put("hireDate", hireDate.toString());
        adminList.add(newAdmin);

        PrintWriter pw = new PrintWriter(adminFile);
        pw.write(adminList.toJSONString());

        pw.flush();
        pw.close();
    }

    public static void addInspector( String name, String lastName, String phoneNumber, String email, String billingAddress, String id, String PIN, LocalDate hireDate, String terminalID) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(inspectorFile));
        JSONArray inspectorList = (JSONArray) array;

        JSONObject newInspector = new JSONObject();
        newInspector.put("name", name);
        newInspector.put("lastName", lastName);
        newInspector.put("phoneNumber", phoneNumber);
        newInspector.put("email", email);
        newInspector.put("billingAddress", billingAddress);
        newInspector.put("id", id);
        newInspector.put("PIN", PIN);
        newInspector.put("hireDate", hireDate.toString());
        newInspector.put("terminalID", terminalID);
        inspectorList.add(newInspector);

        PrintWriter pw = new PrintWriter(inspectorFile);
        pw.write(inspectorList.toJSONString());

        pw.flush();
        pw.close();

    }

}
