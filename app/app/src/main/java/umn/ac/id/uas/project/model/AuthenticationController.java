package umn.ac.id.uas.project.model;

public class AuthenticationController {
    private String message, token;
    private UserModel user;

    public Result login() {
        return new Result(message, token, user);
    }

    public String registerUser() {
        return message;
    }

    public UserModel getUser() {
        return user;
    }

    public class Result {
        private String message, token;
        private UserModel user;

        public Result(String message, String token, UserModel user) {
            this.message = message;
            this.token = token;
            this.user = user;
        }

        public String getMessage() {
            return message;
        }

        public String getToken() {
            return token;
        }

        public UserModel getUser() {
            return user;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "message='" + message + '\'' +
                    ", token='" + token + '\'' +
                    ", user=" + user +
                    '}';
        }
    }
}
