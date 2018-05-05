package model;

import java.util.ArrayList;
import java.util.List;

public class ResultUsers {
    private List<User> users = new ArrayList<User>();

    public ResultUsers() {
    }

    public ResultUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
