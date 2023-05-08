package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.PhoneNumbers;
import am.hitech.jdbc.service.PhoneNumbersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InputNumbersServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PhoneNumbersService phoneNumbersService = new PhoneNumbersService();
        PrintWriter printWriter = resp.getWriter();
        String number = req.getParameter("number");

        if (number.length() == 0) {
            printWriter.write("empty row");
        } else if (!phoneNumbersService.isAllSpace(number)) {
            printWriter.write("empty row");
        } else if (!phoneNumbersService.isValidString(number)) {
            printWriter.write("Invalid number");
        } else if (phoneNumbersService.checkCountOfCode(number) == 0) {
            printWriter.write("Invalid code");
        } else if (phoneNumbersService.checkCountOfNumberWithCode(number) > 0) {
            printWriter.write("Number is busy");
        } else {

            phoneNumbersService.getId(number);
            PhoneNumbers phoneNumbers = new PhoneNumbers();
            phoneNumbers.setId(0);
            phoneNumbers.setNumber(Integer.parseInt(number.substring(2, 8)));
            phoneNumbers.setPhoneCodeId(phoneNumbersService.getId(number));
            phoneNumbers.setUserId(0);

            phoneNumbersService.createPhoneNumber(phoneNumbers);
            printWriter.write("Number is created");
        }
    }
}
