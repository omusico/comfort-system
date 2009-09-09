package comfort.persistence.hibernate.test;

import comfort.persistence.front.*;
import comfort.persistence.hibernate.PureHibernateDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class DBTest {
    private static DAO dao = new PureHibernateDAO(DBTest.class.getResource("hibernate.cfg.xml"));

    private static String encloseString(String s) {
        return '"' + s + '"';
    }

    @Test
    public void smokeTest() {
        dao.createDB();
        Assert.assertTrue(dao.findList("from IComfortType").size() > 0);

        String className = "First Entity Class";
        IComfortProperty propHome = dao.
                create(IComfortProperty.class).
                fillName("home").
                setType(dao.findByID(IComfortType.class, IComfortType.REFERENCE));
        dao.save(propHome);
        IComfortClass cmtClass =
                dao.create(IComfortClass.class).
                        fillName(className).
                addProperty(propHome);
        dao.save(cmtClass);
        List<IComfortClass> classes = dao.findList("from IComfortClass");
        Assert.assertTrue(classes.size() == 1);

        IComfortInstance home =
                cmtClass.createInstance().
                        fillName("parent");

        IComfortInstance child =
                cmtClass.createInstance().
                        fillName("child").
                setPropertyValue("home", home);
        dao.save(child);
    }

    
    @Test
    public void perfomanceTest() {
        List<IComfortClass> l = new ArrayList<IComfortClass>();
        for (int i = 0; i < 10000; i++) {
            l.add(dao.<IComfortClass>create(IComfortClass.class).fillName("Class" + i));
        }
        dao.save(l);
    }

    @Test
    public void selectTest() {
        IComfortClass c = dao.findSingle("from IComfortClass where id = 322");
        Assert.assertNotNull(c);
        Assert.assertTrue(c.getName().startsWith("Class"));
    }

}
