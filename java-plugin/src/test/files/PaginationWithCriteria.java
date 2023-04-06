import javax.persistence.criteria;
import java.util.list;


public class PaginationWithCriteria {

    private static final int PAGE_SIZE = 10;
    private static final int FIRST_RESULT = 10;

     public List<Foo> getFirstPage() { //Compliant
         Session session = null;
         Criteria criteria = session.createCriteria(Foo.class);
         criteria.setFirstResult(FIRST_RESULT);
         criteria.setMaxResults(PAGE_SIZE);
         List<Foo> firstPage = criteria.list();
         return firstPage;
     }

     public List<Foo> getList() { //Noncompliant {{set first result and set max result to avoid retrieve all data}}
         Session session = null;
         Criteria criteria = session.createCriteria(Foo.class);
         List<Foo> result = criteria.list();
         return result;
     }


}