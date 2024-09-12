package ru.nsemenchenko.exception;

import java.sql.SQLException;

public class DaoException extends Throwable {
    public DaoException(SQLException e) {
    }
}
