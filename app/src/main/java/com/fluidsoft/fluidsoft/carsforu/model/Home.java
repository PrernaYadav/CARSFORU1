package com.fluidsoft.fluidsoft.carsforu.model;

public class Home {

    private String carname;
    private String carfuel;
    private String carkm;
    private String carlocation;
    private String carmodel;
    private String cartransmission;
       private String currentbid;
    private String raiseby;
    private String users;
private String image;
    private String time;
    private String start;
    private String max;
    public Home() {
        // TODO Auto-generated constructor stub
    }

    public Home(String carname, String carfuel, String carkm, String carlocation,
                String carmodel, String cartransmission, String currentbid, String raiseby, String users,String max, String image,String time,String start) {
        super();
        this.carname = carname;
        this.carfuel = carfuel;
        this.carkm = carkm;
        this.carlocation = carlocation;
        this.carmodel = carmodel;
        this.cartransmission = cartransmission;
        this.currentbid = currentbid;
        this.raiseby = raiseby;
        this.users=users;
        this.image=image;
        this.time=time;
        this.start=start;
        this.max=max;
    }


    public String getCarName() {
        return carname;
    }

    public void setCarName(String carname) {
        this.carname = carname;
    }

    public String getCarfuel() {
        return carfuel;
    }

    public void setCarfuel(String carfuel) {
        this.carfuel =carfuel;
    }

    public String getCarkm() {
        return carkm;
    }

    public void setCarkm(String carkm) {
        this.carkm =carkm;
    }

    public String getCarlocation() {
        return carlocation;
    }

    public void setCarlocation(String carlocation) {
        this.carlocation = carlocation;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCartransmission() {
        return cartransmission;
    }

    public void setCartransmission(String cartransmission) {
        this.cartransmission = cartransmission;
    }

    public String getCurrentbid() {
        return currentbid;
    }

    public void setCurrentbid(String currentbid) {
        this.currentbid = currentbid;
    }
    public String getRaiseby() {
        return raiseby;
    }

    public void setRaiseby(String raiseby) {
        this.raiseby = raiseby;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

}
