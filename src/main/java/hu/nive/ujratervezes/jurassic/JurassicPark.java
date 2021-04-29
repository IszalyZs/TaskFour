package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }


    public List<String> checkOverpopulation() {
        List<String> dinos = new ArrayList<>();
        String sql = "SELECT breed FROM dinosaur WHERE actual > expected ORDER BY breed ASC;";
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dinos.add(resultSet.getString("breed"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dinos.size() == 0) return new ArrayList<>();
        return dinos;
    }
}
