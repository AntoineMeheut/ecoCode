package fr.greencodeinitiative.java.checks;

import java.util.List;

public class PaginationWithScrollable {

	public List<Employee> getEmployeeWithScrollable(int fetchSize, int row) { //Compliant
		StatelessSession session = ((Session) entityManager.getDelegate()).getSessionFactory().openStatelessSession();
		Query query = session
				.createQuery("SELECT a FROM Employee a ORDER BY a.id");
		query.setFetchSize(Integer.valueOf(fetchSize));
		query.setReadOnly(true);
		query.setLockMode("a", LockMode.NONE);
		
		ScrollableResults results = query.scroll();
		
		results.setRowNumber(row);
		
		List<Employee> resultListEmployee = new ArrayList<>();
		resultListEmployee.add(results.get());
		
		results.close();
		session.close();

		return resultListEmployee;
	}

	public List<Employee> getEmployeeWithoutScrollable() { //Noncompliant {{Use Pagination}}

		StatelessSession session = ((Session) entityManager.getDelegate()).getSessionFactory().openStatelessSession();

		Query query = session
				.createQuery("SELECT a FROM Employee a ORDER BY a.id");
		query.setReadOnly(true);
		query.setLockMode("a", LockMode.NONE);
		
		List<Employee> resultListEmployee = query.getResultList();
		
		session.close();

		return resultListEmployee;
	}


	public class Employee {

	}































	public List<Foo> getFirstPage() { //Compliant
		Criteria criteria = session.createCriteria(Foo.class);
		criteria.setFirstResult(FIRST_RESULT);
		criteria.setMaxResults(PAGE_SIZE);
		List<Foo> firstPage = criteria.list();
		return firstPage;
	}

	public List<Foo> getList() { //Noncompliant {{set first result and set max result to avoid retrieve all data}}
		Criteria criteria = session.createCriteria(Foo.class);
		List<Foo> result = criteria.list();
		return result;
	}

	ReadAllQuery query = new ReadAllQuery();
	query.setReferenceClass(Employee.class);
	query.setSelectionCriteria(new ExpressionBuilder.get("id").greaterThan(100));
	// Set the JDBC fetch size
	query.setFetchSize(50);
	// Configure the query to return results as a ScrollableCursor
	query.useScrollableCursor();
	// Execute the query
	ScrollableCursor cursor = (ScrollableCursor) session.executeQuery(query);
	// Iterate over the results
	while (cursor.hasNext()) {
		System.out.println(cursor.next().toString());
	}
	cursor.close();

}