package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.util.QRCodeGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private Showing showing;
    @ManyToOne
    private SeatNumber seatNumber;

    private byte[] qrCode;

    public Ticket(){}

    public Ticket(Showing showing, SeatNumber seatNumber, Booking booking) {
        this.showing = showing;
        this.seatNumber = seatNumber;
        this.booking = booking;
        try {
            QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
            qrCode = qrCodeGenerator.generateQRCode(    booking.getId() + "," +
                                                        showing.getId().toString() + "," +
                                                        seatNumber.toString());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public SeatNumber getSeat() {
        return seatNumber;
    }

    public void setSeat(SeatNumber seat) {
        this.seatNumber = seat;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(booking, ticket.booking) && Objects.equals(showing, ticket.showing) && Objects.equals(seatNumber, ticket.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking, showing, seatNumber);
    }
}
