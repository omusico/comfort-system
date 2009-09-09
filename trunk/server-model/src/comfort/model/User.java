package comfort.model;

import javax.persistence.Table;
import javax.persistence.Entity;


/**
 *
 * Author: Michael Morozov
 * Date: 26.11.2007
 * Time: 20:36:36
 * 
 */
@Entity
@Table(name = "Users")
public class User extends NamedEntity{
    private boolean admin;
    private String login;
    private boolean deleted;


    public User(int id, String name, boolean admin, String login, boolean deleted) {
        super(id, name);
        this.admin = admin;
        this.login = login;
        this.deleted = deleted;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
