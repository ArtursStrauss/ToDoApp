package lv.javaguru.java2ToDoApp.domain;

public class UserBuilder {

    private Long id;
    private String login;
    private String password;
    private String fullName;

    private UserBuilder() {
    }

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setFullName(fullName);
        return user;
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFullName(String fullName){
        this.fullName = fullName;
        return this;
    }
}
