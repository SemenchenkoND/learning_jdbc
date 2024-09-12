package ru.nsemenchenko.dao;

import ru.nsemenchenko.entity.Ticket;
import ru.nsemenchenko.exception.DaoException;
import ru.nsemenchenko.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();

    public Ticket save(Ticket ticket){
        try(var connection  = ConnectionManager.get();
        var statement = connection.prepareStatement()){

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
