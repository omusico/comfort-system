package comfort.persistence.hibernate;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {
    
    public static Object lookup(String jndiName) throws NamingException {
       return new InitialContext().lookup(jndiName);
    };
}
