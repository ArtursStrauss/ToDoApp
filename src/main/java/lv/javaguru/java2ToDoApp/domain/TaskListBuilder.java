package lv.javaguru.java2ToDoApp.domain;

public class TaskListBuilder {

    private Long id;
    private String title;
    private User user;

    private TaskListBuilder(){}

    public static TaskListBuilder createTaskList(){
        return new TaskListBuilder();
    }

    public TaskList build() {
        TaskList list = new TaskList();
        list.setId(id);
        list.setTitle(title);
        list.setUser(user);
        return list;
    }

    public TaskListBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TaskListBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskListBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public TaskListBuilder withUser(UserBuilder userBuilder) {
        this.user = userBuilder.build();
        return this;
    }
}
