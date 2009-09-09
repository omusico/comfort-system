import comfort.model.Goods;
import comfort.model.Department;
import comfort.model.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Миша
 * Date: 28.11.2007
 * Time: 22:16:44
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        Department dep1 = new Department(0, "Кухня");
        Department dep2 = new Department(1, "Бар");
        session.save(dep1);
        session.save(dep2);
        Goods goods = new Goods(10, "Хавчег", 10.90, dep1);
        
        session.save(goods);
        goods = new Goods(11, "Пивасег", 11.90, dep2);
        session.save(goods);
        tx.commit();

        List<Goods> list = session.createQuery("from Goods where department.name = 'Бар'").list();
        for (Goods g: list)
            System.out.println(g.getName());

    }
}