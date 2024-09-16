package ru.nsemenchenko;

import ru.nsemenchenko.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class getFlightsBetween {

    public static List<Integer> get(LocalDateTime start, LocalDateTime end){
        List<Integer> flights = new ArrayList<>();
        String sql = "select * from flight where departure_date BETWEEN ? AND ?";

        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setTimestamp(1, Timestamp.valueOf(start));
            statement.setTimestamp(2, Timestamp.valueOf(end));
            var result = statement.executeQuery();

            while (result.next()){
                flights.add(result.getInt("id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }
}
