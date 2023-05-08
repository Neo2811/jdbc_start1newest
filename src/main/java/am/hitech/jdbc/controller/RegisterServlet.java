package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.service.AddressService;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        PrintWriter printWriter = resp.getWriter();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String ageStr = req.getParameter("age");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String homeStr = req.getParameter("home");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (name.length() == 0 || surname.length() == 0 || ageStr.length() == 0 || email.length() == 0 || country.length() == 0 || city.length() == 0
                || street.length() == 0 || homeStr.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            printWriter.write("empty row");
        } else if (!(userService.isAllSpace(name) || userService.isAllSpace(surname) || userService.isAllSpace(ageStr)
                || userService.isAllSpace(email) || userService.isAllSpace(country) || userService.isAllSpace(city)
                || userService.isAllSpace(street) || userService.isAllSpace(homeStr) || userService.isAllSpace(password)
                || userService.isAllSpace(confirmPassword))) {
            printWriter.write("empty row");
        } else if (!userService.isValidName(name)) {
            printWriter.write("Invalid name");
        } else if (!userService.isValidName(surname)) {
            printWriter.write("Invalid surname");
        } else if (!userService.isValidAge(ageStr)) {
            printWriter.write("Invalid age");
        } else if (!userService.isValidEmail(email)) {
            printWriter.write("Invalid email");
        } else if (userService.getCountByEmail(email) == 1) {
            printWriter.write("There are user with this email, please enter another email!!!");
        } else if (!userService.isValidCountry(country)) {
            printWriter.write("Invalid country");
        } else if (!userService.isValidCity(city)) {
            printWriter.write("Invalid city");
        } else if (!userService.isValidStreet(street)) {
            printWriter.write("Invalid street");
        } else if (!userService.isValidHome(homeStr)) {
            printWriter.write("Invalid home");
        } else if (!userService.isValidPassword(password)) {
            printWriter.write("Invalid password");
        } else if (!password.equals(confirmPassword)) {
            printWriter.write("password and confirm password is not equal");
        } else {
            int age = Integer.parseInt(ageStr);
            int home = Integer.parseInt(homeStr);

            User user = new User();
            Address address = new Address();

            user.setId(0);
            user.setFirstName(name);
            user.setLastName(surname);
            user.setEmail(email);
            user.setAge(age);
            user.setPassword(password);
            user.setPasswordToken("0");

            try {
                userService.createUserV2(user);
            } catch (InternalServerError e) {
                throw new RuntimeException(e);
            }
            User user1 = new User();
            try {
                user1 = userService.getByUser(email);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            address.setCountry(country);
            address.setCity(city);
            address.setStreet(street);
            address.setHome(home);
            address.setUserId(user1.getId());

            AddressService addressService = new AddressService();
            try {
                addressService.createAddress(address);
            } catch (InternalServerError e) {
                throw new RuntimeException(e);
            }
            printWriter.write("user and address are created");
        }
    }
}
