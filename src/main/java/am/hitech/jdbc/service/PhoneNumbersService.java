package am.hitech.jdbc.service;

import am.hitech.jdbc.model.PhoneNumbers;
import am.hitech.jdbc.repo.PhoneNumbersRepo;

public class PhoneNumbersService {
    PhoneNumbersRepo phoneNumbersRepo = new PhoneNumbersRepo();

    public int createPhoneNumber(PhoneNumbers phoneNumbers) {
        return phoneNumbersRepo.createPhoneNumber(phoneNumbers);
    }

    public int checkCountOfNumberWithCode(String number) {
        return phoneNumbersRepo.checkCountOfNumberWithCode(number);
    }

    public int checkCountOfCode(String number) {
        return phoneNumbersRepo.checkCountOfCode(number);
    }

    public int getId(String number) {
        return phoneNumbersRepo.getId(number);
    }

    public boolean isValidString(String s) {
        if (s.length() != 8) return false;
        else if (s.charAt(0) == '0') return false;

        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)) return false;
        }
        return true;
    }

    public boolean isAllSpace(String s) {
        if (s == " " || s.length() == 0) return false;
        int i;
        int n;
        for (i = 0, n = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                n++;
            }
        }
        if (n == i) return false;
        return true;
    }
}
