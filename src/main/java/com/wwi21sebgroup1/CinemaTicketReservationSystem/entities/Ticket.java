package com.wwi21sebgroup1.CinemaTicketReservationSystem.entities;

import com.wwi21sebgroup1.CinemaTicketReservationSystem.util.QRCodeGenerator;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Showing showing;
    @OneToOne
    private Seat seat;

    private byte[] qrCode;

    public Ticket(){}

    public Ticket(Showing showing, Seat seat) {
        this.showing = showing;
        this.seat = seat;
        try {
            qrCode = QRCodeGenerator.generateQRCode(showing.getId().toString() + "," + seat.getSeatNumber().toString());
        }catch (Exception e){
            System.err.println(e);
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }
}
