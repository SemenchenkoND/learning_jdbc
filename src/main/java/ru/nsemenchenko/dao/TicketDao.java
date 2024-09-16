package ru.nsemenchenko.dao;

import ru.nsemenchenko.entity.Ticket;
import ru.nsemenchenko.exception.DaoException;
import ru.nsemenchenko.utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();

    public static final String SAVE_SQL = "INSERT INTO ticket" +
                                        "(passport_no, passenger_name, flight_id, seat_no, cost) " +
                                        "VALUES (?, ?, ?, ?, ?)";

    public static final String DELETE_SQL = "DELETE FROM ticket WHERE id = ?";
    public Ticket save(Ticket ticket) throws DaoException {

        try(var connection  = ConnectionManager.get();
        var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, ticket.getPassport_no());
            statement.setString(2, ticket.getPassenger_name());
            statement.setInt(3, ticket.getFlight_id());
            statement.setString(4, ticket.getSeat_no());
            statement.setBigDecimal(5, ticket.getCost());

            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if(keys.next()){
                ticket.setId(keys.getInt("id"));
            }
            return ticket;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(int id) throws DaoException {

        try(var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(DELETE_SQL)){
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    public static TicketDao getInstance(){
        return INSTANCE;
    }

    private TicketDao(){
    }
}
