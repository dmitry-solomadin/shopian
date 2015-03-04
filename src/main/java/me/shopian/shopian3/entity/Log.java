package me.shopian.shopian3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="logs")
public class Log {
    @Id
    @GeneratedValue
    private Long id;


    /**
     * real date
     */
    @Column(name = "date_server")
    private Date dateServer= new Date();

    /**
     * date recived from mobile (eventDate)
     */
    @Column(name = "date_mobile")
    private Date dateMobile;

    @ManyToOne
    Event event;

    @ManyToOne
    UserMobile userMobile;

    @ManyToOne
    Beacon beacon;

    /**
     * list of the ads sended to client in JSON.
     */
    @Column(nullable=false)
    @ManyToMany(cascade =CascadeType.ALL)
    private List<Ad> ads=new ArrayList();


    ///////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateServer() {
        return dateServer;
    }

    public void setDateServer(Date dateServer) {
        this.dateServer = dateServer;
    }

    public Date getDateMobile() {
        return dateMobile;
    }

    public void setDateMobile(Date dateMobile) {
        this.dateMobile = dateMobile;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserMobile getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(UserMobile userMobile) {
        this.userMobile = userMobile;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
