package ru.nsemenchenko.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
    private Integer id;
    private String passport_no;
    private String passenger_name;
    private Integer flight_id;
    private String seat_no;
    private BigDecimal cost;

    public Ticket() {
    }

    public Ticket(int id, String passport_no, String passenger_name, int flight_id, String seat_no, BigDecimal cost) {
        this.id = id;
        this.passport_no = passport_no;
        this.passenger_name = passenger_name;
        this.flight_id = flight_id;
        this.seat_no = seat_no;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passport_no='" + passport_no + '\'' +
                ", passenger_name='" + passenger_name + '\'' +
                ", flight_id=" + flight_id +
                ", seat_no='" + seat_no + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && flight_id == ticket.flight_id && Objects.equals(passport_no, ticket.passport_no) && Objects.equals(passenger_name, ticket.passenger_name) && Objects.equals(seat_no, ticket.seat_no) && Objects.equals(cost, ticket.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passport_no, passenger_name, flight_id, seat_no, cost);
    }
}
