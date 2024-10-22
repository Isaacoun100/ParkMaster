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

public class JTool {

    // These are the files that will be accessed
    static String customerFile = "src/misc/customer.json";
    static String inspectorFile = "src/misc/inspector.json";
    static String adminFile = "src/misc/admin.json";

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
     * It updates the information on the file to have the new admin
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

}
