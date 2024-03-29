package me.shopian.shopian3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/** //from asana
 * Название
 * Дата начала
 * Push text
 * Описание
 * Дата окончания
 * Картинка
 */


@Entity
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 128)
    private String title;

    @Column(length = 128, name = "pushtext")
    private String pushText;

    @Column(length = 8 * 1024)
    private String content;

    @Column(name = "datestart")
    private Date dateStart= new Date();

    @Column(name = "datestop")
    private Date dateStop;

    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;

    @Column(name = "img_width", nullable = false)
    private int imgWidth=0;

    @Column(name = "img_height", nullable = false)
    private int imgHeight=0;

//    @JsonIgnore
    @ManyToOne
    private User user;

//    @JsonIgnore
    @ManyToOne
    private Shop shop;

//    @JsonIgnore
    @ManyToOne
    private Department department;

    public Ad(){}
    public Ad(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public int getImgVer(){
        if (this.img==null) return 0;
        else return this.img.length;
    }

    //////////////////////////////////////////////////////////////////////


    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPushText() {
        return pushText;
    }

    public void setPushText(String pushText) {
        this.pushText = pushText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(Date dateStop) {
        this.dateStop = dateStop;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


}








