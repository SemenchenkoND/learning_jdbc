package ru.nsemenchenko;

import ru.nsemenchenko.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class getTicketsByFlightId {

    public static List<Integer> get(Integer flight_id){
        List<Integer> tickets = new ArrayList<>();
        String sql = "select * from ticket where flight_id = ?;";
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setFetchSize(2);
            statement.setInt(1 ,flight_id);
            var result = statement.executeQuery();

            while (result.next()){
                tickets.add(result.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

}
