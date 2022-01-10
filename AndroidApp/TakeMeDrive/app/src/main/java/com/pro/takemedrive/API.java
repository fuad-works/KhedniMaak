package com.pro.takemedrive;

import com.pro.takemedrive.Models.FullTrip;
import com.pro.takemedrive.Models.Participate;
import com.pro.takemedrive.Models.Trip;
import com.pro.takemedrive.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("login.php")
    Call<User> Login( @Field("user_name") String uname,@Field("password") String password);

    @FormUrlEncoded
    @POST("mytrips.php")
    Call<List<FullTrip>> get_my_trips(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("browseusers.php")
    Call<List<User>> get_all_useres(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("browsetrips.php")
    Call<List<FullTrip>> get_all_trips(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("regtrips.php")
    Call<List<FullTrip>> get_reg_trips(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("archive.php")
    Call<List<FullTrip>> get_archive_trips(@Field("user_id") int user_id);


    @FormUrlEncoded
    @POST("participate.php")
    Call<List<Participate>> getparticipate(@Field("user_id") int user_id, @Field("trip_id") int trip_id);

}
