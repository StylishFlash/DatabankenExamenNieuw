import org.hibernate.Session;
import persistence.HibernateUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 2/11/12
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */
public class Start {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
}
