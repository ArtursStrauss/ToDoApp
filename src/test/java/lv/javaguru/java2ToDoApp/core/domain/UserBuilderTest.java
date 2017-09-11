package lv.javaguru.java2ToDoApp.core.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserBuilderTest {

    @Test
    public void createUserWithBuilderTest() {
        User user = UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("unit-test")
                .withFullName("Unit Test")
                .withPassword("test123")
                .build();

        assertThat(user.getId(), is(new Long(1)));
        assertThat(user.getLogin(), is("unit-test"));
        assertThat(user.getFullName(), is("Unit Test"));
        assertThat(user.getPassword(), is("test123"));
    }
}
