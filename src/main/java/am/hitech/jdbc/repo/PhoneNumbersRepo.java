package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.PhoneNumbers;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneNumbersRepo {
    Connection connection = DataSource.getConnection();

    public int createPhoneNumber(PhoneNumbers phoneNumbers) {
        String query = "insert into `phone_numbers` values(0,?,?,null )";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,phoneNumbers.getNumber());
            statement.setInt(2,phoneNumbers.getPhoneCodeId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  int checkCountOfNumberWithCode(String number) {
        int count = 0;
        String query = "SELECT  COUNT(*) FROM `phone_numbers` WHERE phone_code_id = (SELECT id FROM phone_codes WHERE CODE = ?) AND NUMBER = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,Integer.parseInt(number.substring(0,2)));
            statement.setInt(2,Integer.parseInt(number.substring(2,8)));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int checkCountOfCode(String number) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM phone_codes WHERE CODE = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(number.substring(0,2)));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getId(String number) {
        int count = 0;
        String query = "select id from phone_codes where code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,Integer.parseInt(number.substring(0,2)));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
