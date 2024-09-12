package ru.nsemenchenko;

import ru.nsemenchenko.utils.ConnectionManager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        System.out.println(getTicketsByFlightId(1));
        System.out.println("---------------------------");
        System.out.println(getFlightsBetween(LocalDate.of(2024, 6, 30).atStartOfDay(),
                                            LocalDate.of(2024, 9, 20).atStartOfDay()));
        System.out.println("-------------------------");
        checkMetaData();
    }

    public static List<Integer> getTicketsByFlightId(Integer flight_id){
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

    public static List<Integer> getFlightsBetween(LocalDateTime start, LocalDateTime end){
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

    public static void checkMetaData() throws SQLException {
        try(Connection connection = ConnectionManager.get()){
            var metaData = connection.getMetaData();
            var catalogs = metaData.getCatalogs();
            while(catalogs.next()){
                System.out.println(catalogs.getString(1));
            }
        }
    }
}
