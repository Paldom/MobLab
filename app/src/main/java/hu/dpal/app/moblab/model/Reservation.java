package hu.dpal.app.moblab.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by dpal on 17/04/16.
 */
@Table
public class Reservation extends SugarRecord {

    private Long id;
    private Long partnerId;
    private String reservationCode;
    private String reservationDate;
    private String category;

    public Reservation() {
    }

    @Override
    public boolean equals(Object o) {
        Reservation r = (Reservation) o;
        return r.getId().equals(this.getId()) &&
                r.getPartnerId().equals(this.getPartnerId()) &&
                r.getReservationCode().equals(this.getReservationCode()) &&
                r.getReservationDate().equals(this.getReservationDate()) &&
                r.getCategory().equals(this.getCategory());
    }

    public Reservation(Long partnerId,
                       String reservationCode,
                       String reservationDate,
                       String category) {
        this.partnerId = partnerId;
        this.reservationCode = reservationCode;
        this.reservationDate = reservationDate;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
