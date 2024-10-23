package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import model.other.*;
import model.*;

import static java.lang.String.valueOf;

public class JTool {

    // These are the files that will be accessed
    static String customerFile = "src/misc/customer.json";
    static String inspectorFile = "src/misc/inspector.json";
    static String adminFile = "src/misc/admin.json";
    static String settingsFile = "src/misc/settings.json";
    static String parkingSpots = "src/misc/parkingSpots.json";

    /**
     * This method will receive a password and email and will look up in the file if the
     * customer exists in the JSON file
     * @param password The password from the client
     * @param id The email the client used
     * @return A Customer object that contains the customer information or a NULL if none
     * was found
     * @throws IOException If the file is not found
     * @throws ParseException If the file cannot be parsed
     */
    public static Customer loginCustomer( String password, String id ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(customerFile));
        JSONArray customerList = (JSONArray) array;

        for(Object item : customerList) {

            JSONObject customer = (JSONObject) item;

            if( valueOf(customer.get("id")).equals(id) && valueOf(customer.get("PIN")).equals(password) ) {

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
                        valueOf(customer.get("email")),
                        valueOf(customer.get("billingAddress")),
                        id,
                        password,
                        new PaymentMethod(
                                (String)paymentMethod.get("cardNumber"),
                                (String)paymentMethod.get("expiryDate"),
                                (String)paymentMethod.get("cvv")
                        ),
                        vehicleList,
                        LocalDate.parse(valueOf(customer.get("signUpDate")))
                );
            }

        }

        return null;
    }


    /**
     * This method will receive a password and email and will look up in the file if the
     * admin exists in the JSON file
     * @param password Admin password to check up
     * @param id Username to check up
     * @return An Admin object with the admin information or a NULL if none was found
     * @throws IOException No file was found
     * @throws ParseException Unable to parse the file
     */
    public static Admin loginAdmin( String password, String id ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(adminFile));
        JSONArray adminList = (JSONArray) array;

        for(Object item : adminList) {

            JSONObject admin = (JSONObject) item;

            if( valueOf(admin.get("id")).equals(id) && valueOf(admin.get("PIN")).equals(password) ) {

                return new Admin(
                        valueOf(admin.get("name")),
                        valueOf(admin.get("lastName")),
                        valueOf(admin.get("phoneNumber")),
                        valueOf(admin.get("email")),
                        valueOf(admin.get("billingAddress")),
                        id,
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
     * @param id The inspector email
     * @return An Inspector object that contains the user information or a NULL if
     * none was found
     * @throws IOException If the file was not found
     * @throws ParseException If the file could not be parsed
     */
    public static Inspector loginInspector( String password, String id ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(inspectorFile));
        JSONArray inspectorList = (JSONArray) array;

        for(Object item : inspectorList) {

            JSONObject inspector = (JSONObject) item;

            if( valueOf(inspector.get("id")).equals(id) && valueOf(inspector.get("PIN")).equals(password) ) {

                return new Inspector(
                        valueOf(inspector.get("name")),
                        valueOf(inspector.get("lastName")),
                        valueOf(inspector.get("phoneNumber")),
                        valueOf(inspector.get("email")),
                        valueOf(inspector.get("billingAddress")),
                        id,
                        password,
                        LocalDate.parse(valueOf(inspector.get("hireDate"))),
                        valueOf(inspector.get("terminal"))
                );

            }

        }

        return null;
    }

    /**
     * This method adds the customer to the customer.json file where the customers are stored
     * @param customer The new customer to add to the file
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void addCustomer( Customer customer) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(customerFile));
        JSONArray customerList = (JSONArray) array;

        JSONObject paymentMethod = new JSONObject();
        paymentMethod.put("cardNumber", customer.getPaymentMethod().getCardNumber());
        paymentMethod.put("expiryDate", customer.getPaymentMethod().getExpiryDate());
        paymentMethod.put("cvv", customer.getPaymentMethod().getCvv());

        JSONArray vehicles = new JSONArray();

        for( Vehicle vehicle : customer.getVehicles() ) {
            JSONObject vehicleObj = new JSONObject();
            vehicleObj.put("vehicleID", valueOf(vehicle.getVehicleID()));
            vehicleObj.put("Brand", valueOf(vehicle.getBrand()));
            vehicleObj.put("Model", valueOf(vehicle.getModel()));
            vehicles.add(vehicleObj);
        }

        JSONObject newCustomer = new JSONObject();
        newCustomer.put("name", customer.getName());
        newCustomer.put("lastName", customer.getLastName());
        newCustomer.put("phoneNumber", customer.getPhoneNumber());
        newCustomer.put("email", customer.getEmail());
        newCustomer.put("billingAddress", customer.getBillingAddress());
        newCustomer.put("id", customer.getId());
        newCustomer.put("PIN", customer.getPIN());
        newCustomer.put("paymentMethod", paymentMethod);
        newCustomer.put("vehicles", vehicles);
        newCustomer.put("signUpDate", valueOf(customer.getSignUpDate()));
        customerList.add(newCustomer);

        PrintWriter pw = new PrintWriter(customerFile);
        pw.write(customerList.toJSONString());

        pw.flush();
        pw.close();

    }

    /**
     * This method adds the admin to the admin.json file where the admins are stored
     * @param admin The new admin to add to the file
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void addAdmin( Admin admin ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(adminFile));
        JSONArray adminList = (JSONArray) array;

        JSONObject newAdmin = new JSONObject();
        newAdmin.put("name", admin.getName());
        newAdmin.put("lastName", admin.getLastName());
        newAdmin.put("phoneNumber", admin.getPhoneNumber());
        newAdmin.put("email", admin.getEmail());
        newAdmin.put("billingAddress", admin.getBillingAddress());
        newAdmin.put("id", admin.getId());
        newAdmin.put("PIN", admin.getPIN());
        newAdmin.put("hireDate", valueOf(admin.getHireDate()));
        adminList.add(newAdmin);

        PrintWriter pw = new PrintWriter(adminFile);
        pw.write(adminList.toJSONString());

        pw.flush();
        pw.close();
    }

    /**
     * This method adds the admin to the inspector.json file where the inspectors are stored
     * @param inspector The new inspector to add to the file
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void addInspector( Inspector inspector ) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(inspectorFile));
        JSONArray inspectorList = (JSONArray) array;

        JSONObject newInspector = new JSONObject();
        newInspector.put("name", inspector.getName());
        newInspector.put("lastName", inspector.getLastName());
        newInspector.put("phoneNumber", inspector.getPhoneNumber());
        newInspector.put("email", inspector.getEmail());
        newInspector.put("billingAddress", inspector.getBillingAddress());
        newInspector.put("id", inspector.getId());
        newInspector.put("PIN", inspector.getPIN());
        newInspector.put("hireDate", valueOf(inspector.getHireDate()));
        newInspector.put("terminalID", inspector.getTerminal());
        inspectorList.add(newInspector);

        PrintWriter pw = new PrintWriter(inspectorFile);
        pw.write(inspectorList.toJSONString());

        pw.flush();
        pw.close();

    }

    /**
     * It updates the information on the file to update the customer
     * @param admin The admin object to update
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void updateAdmin( Admin admin ) throws IOException, ParseException {
        Object array = new JSONParser().parse(new FileReader(adminFile));
        JSONArray adminList = (JSONArray) array;

        for (int i = 0; i < adminList.size(); i++) {

            JSONObject adminObj = (JSONObject) adminList.get(i);

            if(adminObj.get("id").equals(admin.getId())) {
                adminObj.put("name", admin.getName());
                adminObj.put("lastName", admin.getLastName());
                adminObj.put("phoneNumber", admin.getPhoneNumber());
                adminObj.put("email", admin.getEmail());
                adminObj.put("billingAddress", admin.getBillingAddress());
                adminObj.put("id", admin.getId());
                adminObj.put("PIN", admin.getPIN());
                adminObj.put("hireDate", valueOf(admin.getHireDate()));

                adminList.remove(i);
                adminList.add(adminObj);
                break;
            }

        }

        PrintWriter pw = new PrintWriter(adminFile);
        pw.write(adminList.toJSONString());

        pw.flush();
        pw.close();
    }

    /**
     * It updates the information on the file to update the customer
     * @param customer The customer object to update
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void updateCustomer( Customer customer ) throws IOException, ParseException {
        Object array = new JSONParser().parse(new FileReader(customerFile));
        JSONArray customerList = (JSONArray) array;

        for (int i = 0; i < customerList.size(); i++) {

            JSONObject adminObj = (JSONObject) customerList.get(i);

            if(adminObj.get("id").equals(customer.getId())) {
                adminObj.put("name", customer.getName());
                adminObj.put("lastName", customer.getLastName());
                adminObj.put("phoneNumber", customer.getPhoneNumber());
                adminObj.put("email", customer.getEmail());
                adminObj.put("billingAddress", customer.getBillingAddress());
                adminObj.put("id", customer.getId());
                adminObj.put("PIN", customer.getPIN());
                adminObj.put("signUpDate", valueOf(adminObj.get("signUpDate")));

                JSONObject paymentMethodObj = new JSONObject();
                paymentMethodObj.put("cardNumber", customer.getPaymentMethod().getCardNumber());
                paymentMethodObj.put("expiryDate", customer.getPaymentMethod().getExpiryDate());
                paymentMethodObj.put("cvv", customer.getPaymentMethod().getCvv());

                JSONArray vehicleList =  new JSONArray();

                for( Vehicle vehicle : customer.getVehicles()){
                    JSONObject vehicleObj = new JSONObject();
                    vehicleObj.put("Brand", vehicle.getBrand());
                    vehicleObj.put("Model", vehicle.getModel());
                    vehicleObj.put("vehicleID", vehicle.getVehicleID());
                    vehicleList.add(vehicleObj);
                }

                adminObj.put("vehicles", vehicleList);
                customerList.remove(i);
                customerList.add(adminObj);
                break;
            }

        }

        PrintWriter pw = new PrintWriter(customerFile);
        pw.write(customerList.toJSONString());

        pw.flush();
        pw.close();

    }

    /**
     * It updates the information on the file to update the inspector
     * @param inspector The inspector object to update
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void updateInspector( Inspector inspector) throws IOException, ParseException {
        Object array = new JSONParser().parse(new FileReader(inspectorFile));
        JSONArray inspectorList = (JSONArray) array;

        for (int i = 0; i < inspectorList.size(); i++) {

            JSONObject inspectorObj = (JSONObject) inspectorList.get(i);

            if(inspectorObj.get("id").equals(inspector.getId())) {
                inspectorObj.put("name", inspector.getName());
                inspectorObj.put("lastName", inspector.getLastName());
                inspectorObj.put("phoneNumber", inspector.getPhoneNumber());
                inspectorObj.put("email", inspector.getEmail());
                inspectorObj.put("billingAddress", inspector.getBillingAddress());
                inspectorObj.put("id", inspector.getId());
                inspectorObj.put("PIN", inspector.getPIN());
                inspectorObj.put("hireDate", valueOf(inspector.getHireDate()));
                inspectorObj.put("terminalID", inspector.getTerminal());

                inspectorList.remove(i);
                inspectorList.add(inspectorObj);
                break;
            }

        }

        PrintWriter pw = new PrintWriter(inspectorFile);
        pw.write(inspectorList.toJSONString());

        pw.flush();
        pw.close();

    }

    /**
     * Returns the settings for the Park Master program
     * @return A Settings file with all the information to set up the system
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static Settings getSettings() throws IOException, ParseException {

        Object object = new JSONParser().parse(new FileReader(settingsFile));
        JSONObject settingsObj = (JSONObject) object;

        return new Settings(
                LocalTime.parse( valueOf(settingsObj.get("fromTime"))),
                LocalTime.parse( valueOf(settingsObj.get("toTime"))),
                Integer.parseInt(valueOf(settingsObj.get("price"))),
                Integer.parseInt(valueOf(settingsObj.get("minimumTime"))),
                Integer.parseInt(valueOf(settingsObj.get("ticketPrice")))
        );
    }

    /**
     * Receives a settings object and assigns its values to the settings.json file
     * @param settings The class with the info to change
     * @throws IOException If the file was not found
     * @throws ParseException If there is an error with the parsing
     */
    public static void setSettings(Settings settings) throws IOException, ParseException {
        Object object = new JSONParser().parse(new FileReader(settingsFile));
        JSONObject settingsObj = (JSONObject) object;

        settingsObj.put("fromTime", valueOf(settings.getFromTime()));
        settingsObj.put("toTime", valueOf(settings.getToTime()));
        settingsObj.put("price", settings.getPrice());
        settingsObj.put("minimumTime", settings.getMinimumTime());
        settingsObj.put("ticketPrice", settings.getTicketPrice());

        PrintWriter pw = new PrintWriter(settingsFile);
        pw.write(settingsObj.toJSONString());

        pw.flush();
        pw.close();
    }

    public static void addParkingSpots(int from, int to) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(parkingSpots));
        JSONArray parkingList = (JSONArray) array;

        while( from <= to){

            JSONObject parkingObj = new JSONObject();
            parkingObj.put("ID", valueOf(from));
            parkingObj.put("scheduledBy", "0");
            parkingObj.put("earnings", 0);
            parkingObj.put("tickets", 0);
            parkingList.add(parkingObj);
            from++;
        }

        PrintWriter pw = new PrintWriter(parkingSpots);
        pw.write(parkingList.toJSONString());

        pw.flush();
        pw.close();

    }

    public static void deleteParkingSpots(int from, int to) throws IOException, ParseException {

        Object array = new JSONParser().parse(new FileReader(parkingSpots));
        JSONArray parkingList = (JSONArray) array;

        int counter = 0;

        while(counter<parkingList.size()){
            JSONObject parkingObj = (JSONObject) parkingList.get(counter);

            if(from <= Integer.parseInt(valueOf(parkingObj.get("ID"))) && Integer.parseInt(valueOf(parkingObj.get("ID"))) <= to)
                parkingList.remove(counter);
            else
                counter++;

        }

        PrintWriter pw = new PrintWriter(parkingSpots);
        pw.write(parkingList.toJSONString());

        pw.flush();
        pw.close();

    }

}
