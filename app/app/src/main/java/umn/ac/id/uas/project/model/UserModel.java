package umn.ac.id.uas.project.model;

public class UserModel {
    private String id, name, email, photo_profile_path, phone_number;
    private BuildDetailModel user_package;
    private String message;

    public UserModel(String id, String name, String email, String photo_profile_path) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo_profile_path = photo_profile_path;
    }

    public String changeProfilePicture() {
        return message;
    }

    public BuildDetailModel getUserPackage() {
        return user_package;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto_profile_path() {
        return photo_profile_path;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
