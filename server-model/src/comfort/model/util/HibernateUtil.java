package comfort.model.util;

import comfort.model.Goods;
import comfort.model.GoodsMenu;
import comfort.model.Job;
import comfort.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: РњРёС€Р°
 * Date: 26.11.2007
 * Time: 22:34:46
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static{
       
        Configuration cfg = new AnnotationConfiguration()
            // Classes
            .addAnnotatedClass(Job.class)
            .addAnnotatedClass(Goods.class)
            .addAnnotatedClass(GoodsMenu.class)
            .addAnnotatedClass(Department.class)
            // Configuring process
            .configure();
        sessionFactory = cfg.buildSessionFactory();
    }


    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
