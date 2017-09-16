package com.fluidsoft.fluidsoft.carsforu.Class;




public class ConfigInfo {


    public static final String LOGIN_URL = "http://go4intern.com/cars/app/login";

    public static final String signUp="http://go4intern.com/cars/app/signup";

    public static final String currentBid="http://go4intern.com/cars/app/user_bid";

    public static final String lostBid="http://go4intern.com/cars/app/lost_bid";

    public static final String wonBid="http://go4intern.com/cars/app/win_bid";


    public static final String edit="http://go4intern.com/cars/app/edit_profile";
    public static final String carDetails="http://go4intern.com/cars/app/car_report";

    public static final String carImages="http://go4intern.com/cars/app/gallery_image";

    public static final String extraInfo="";

    public static final String profileInfo="http://go4intern.com/cars/app/profile";

    public static final String bid_price="http://go4intern.com/cars/app/bid_price";

    public static final String singlecar="http://go4intern.com/cars/app/car_bid";

    public static final String home="http://go4intern.com/cars/app/home";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "username";

    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "ok";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

}
