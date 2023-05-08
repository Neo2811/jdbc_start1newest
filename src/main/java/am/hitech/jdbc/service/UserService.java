package am.hitech.jdbc.service;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.util.exceptionMessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;
import java.util.Random;

public class UserService {
    UserRepo userRepo = new UserRepo();

    public void updateUser(String firstName, String lastName, int id) throws InternalServerError {
        int row = userRepo.updateUser(firstName, lastName, id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createUser(User user) throws InternalServerError {
        int row = userRepo.createUser(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createUser2(User user) throws InternalServerError {
        int row = userRepo.createUser2(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createUserV2(User user) throws InternalServerError {
        int row = userRepo.createUserV2(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void deleteUser(int id) throws InternalServerError {
        int row = userRepo.deleteUser(id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public User getByUser(String userName) throws NotFoundException {

        if (userRepo.getByUser(userName) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getByUser(userName);
    }

    public User getById(int id) throws NotFoundException {
        if (userRepo.getById(id) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getById(id);
    }

    public User checkUser(String username, String password) {
        User user = null;
        try {
            user = userRepo.checkUser(username, password);
            if (user == null) {
                throw new NotFoundException(ErrorMessage.WRONG_LOGIN_OR_PASSWORD);
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getByUserMoreAgeThenGiven(int age) throws NotFoundException {
        if (userRepo.getByUserMoreAgeThenGiven(age) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getByUserMoreAgeThenGiven(age);
    }

    public List<User> getByUser2(String s) throws NotFoundException {
        List<User> list = userRepo.getByUser2(s);
        if (list.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getByUser2(s);
    }

    public String randomString() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public void updatePasswordToken(String email) throws NotFoundException {
        int row = userRepo.updatePasswordToken(email);
        if (row == 0) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public String getPasswordToken(String email) {
        return userRepo.getPasswordToken(email);
    }

    public void updateUserPassword(String email, int password) throws NotFoundException {
        int row = userRepo.updateUserPassword(email, password);
        if (row == 0) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public boolean isAllSpace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean isValidName(String s) {
        if (s.length() <= 2)
            return false;
        return true;
    }

    public boolean isValidAge(String s) {
        boolean isValidAge = true;
        if (s.charAt(0) == 0) {
            isValidAge = false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57) || s.charAt(i) == '-') {
                isValidAge = false;
            }
        }
        if (isValidAge) {
            int age = Integer.parseInt(s);
            if (age <= 17 || age > 120) {
                isValidAge = false;
            }

        }
        return isValidAge;
    }

    public boolean isValidEmail(String s) {
        if (s.length() < 7 || s.indexOf('@') == -1 || s.indexOf('.') == -1) {
            return false;
        } else if (s.indexOf('@') != s.lastIndexOf('@') || s.indexOf('.') != s.lastIndexOf('.')) {
            return false;
        }else if (s.substring(0, s.indexOf('@')).length() < 2
                || s.substring(s.indexOf('@') + 1, s.indexOf('.')).length() < 3
                || s.substring(s.indexOf('.') + 1).length() < 1) {
            return false;
        }
        return true;
    }

    public boolean isValidPassword(String s) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        if (s.length() < 6) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                count1++;
            }else if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                count2++;
            }else if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                count3++;
            }
        }
        if (count1 < 1 || count2 < 1 || count3 < 1) {
            return false;
        }
        return true;
    }

    public boolean isValidCountry(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) >= 97 && s.charAt(i) <= 122)
                    || (s.charAt(i) >= 65 && s.charAt(i) <= 90)
                    || s.charAt(i) == '-' || s.charAt(i) == ' ')) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidCity(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 97 && s.charAt(i) <= 122 || (s.charAt(i) >= 65 && s.charAt(i) <= 90))) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidStreet(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 97 && s.charAt(i) <= 122
                    || s.charAt(i) >= 65 && s.charAt(i) <= 90
                    || s.charAt(i) == '-')) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidHome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)) {
                return false;
            }
        }
        return true;
    }

    public int getCountByEmail(String email) {
        return userRepo.getCountByEmail(email);
    }
}
