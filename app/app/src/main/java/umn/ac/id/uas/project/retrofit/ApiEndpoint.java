package umn.ac.id.uas.project.retrofit;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import umn.ac.id.uas.project.model.AuthenticationController;
import umn.ac.id.uas.project.model.ComputerPackage;
import umn.ac.id.uas.project.model.MotherboardModel;
import umn.ac.id.uas.project.model.PowerSupplyModel;
import umn.ac.id.uas.project.model.ProcessorIntelModel;
import umn.ac.id.uas.project.model.UserModel;
import umn.ac.id.uas.project.model.UserPackagePick;
import umn.ac.id.uas.project.model.VgaCardModel;

public interface ApiEndpoint {
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("auth/login")
    Call<AuthenticationController> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("register/user")
    Call<AuthenticationController> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone_number") String phoneNumber);

    @GET("auth/user")
    @Headers("Accept: application/json")
    Call<AuthenticationController> getUser(@Header("Authorization") String authorization);

    @GET("computers")
    @Headers("Accept: application/json")
    Call<ComputerPackage> getAllComputers();

    @GET("top-specifications")
    @Headers("Accept: application/json")
    Call<ComputerPackage> getTopSpecificationComputers();

    @GET("processors")
    @Headers("Accept: application/json")
    Call<ProcessorIntelModel> getProcessors();

    @GET("processors/amd")
    @Headers("Accept: application/json")
    Call<ProcessorIntelModel> getAmdProcessors();

    @GET("vga")
    @Headers("Accept: application/json")
    Call<VgaCardModel> getVga();

    @GET("motherboards")
    @Headers("Accept: application/json")
    Call<MotherboardModel> getMotherboards();

    @GET("power-supplies")
    @Headers("Accept: application/json")
    Call<PowerSupplyModel> getPowerSupplies();

    @GET("users/package")
    @Headers("Accept: application/json")
    Call<UserModel> getUserPackage(@Header("Authorization") String authorization);

    @POST("users/select/processor")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    Call<UserPackagePick> pickProcessor(@Header("Authorization") String authorization,@Field("processor_id") int processorId);

    @POST("users/profile/photo")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    Call<UserModel> changeProfilePicture(@Header("Authorization") String authorization, @Field("image") String image);

    @POST("users/select/vga")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    Call<UserPackagePick> pickVga(@Header("Authorization") String authorization,@Field("vga_id") int vgaId);

    @POST("users/select/motherboard")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    Call<UserPackagePick> pickMotherboard(@Header("Authorization") String authorization,@Field("motherboard_id") int motherboardId);

    @POST("users/select/power-supply")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    Call<UserPackagePick> pickPowerSupply(@Header("Authorization") String authorization,@Field("power_supply_id") int powerSupplyId);
}
