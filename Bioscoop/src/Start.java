import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 2/11/12
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */
public class Start {
    public static void main(String[] args) {

            Configuration conf = new Configuration();
            try {
                conf.configure("hibernateMySQL.cfg.xml");
                        SchemaExport export = new SchemaExport(conf);
                export.setOutputFile("shema.sql");
                export.create(true, true);
            } catch (HibernateException e) {
                e.printStackTrace();
            }

    }
}
