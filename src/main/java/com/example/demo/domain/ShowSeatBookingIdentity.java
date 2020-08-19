package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowSeatBookingIdentity implements Serializable {
    @Column(name = "seatId")
    private int seatId;

    @Column(name = "showId")
    private int showId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowSeatBookingIdentity identity = (ShowSeatBookingIdentity) o;
        return seatId == identity.seatId &&
                showId == identity.showId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId, showId);
    }
}
