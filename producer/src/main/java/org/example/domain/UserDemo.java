package org.example.domain;

import java.io.Serializable;

/**
 * @author zhang
 */

public class UserDemo implements Serializable {
    String username;
    String password;

    public UserDemo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
