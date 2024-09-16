package ru.nsemenchenko;

import ru.nsemenchenko.dao.TicketDao;
import ru.nsemenchenko.entity.Ticket;
import ru.nsemenchenko.exception.DaoException;
import ru.nsemenchenko.utils.ConnectionManager;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;


public class JdbcRunner {
    public static void main(String[] args) throws DaoException {
        var ticketDao = TicketDao.getInstance();
        Ticket ticket = new Ticket();
        ticket.setPassport_no("123421");
        ticket.setPassenger_name("sadjfd");
        ticket.setFlight_id(4);
        ticket.setSeat_no("B27");
        ticket.setCost(BigDecimal.TEN);

        System.out.println(ticketDao.save(ticket));

        System.out.println(ticketDao.delete(5));

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
