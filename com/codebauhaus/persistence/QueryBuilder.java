/**
 * 
 */
package com.codebauhaus.persistence;

import java.sql.Timestamp;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;

/**
 * Attempt at creating a fluent interface for writing a liferay query.
 */
public class QueryBuilder {
	QueryPos qPos;
	Query q;
	SessionFactory sessionFactory;
	String queryString;
	Object[] params;
	
	class RunQueryAndReturnListSessionCallback implements SessionCallback {
		String query;
		public RunQueryAndReturnListSessionCallback(String query) {
			this.query = query;
		}
		public Object doWithSession(Session session) {
			Query createQuery = createQuery(session, queryString);
			if(params != null)
				addParms(params);
			return q.list();
		}
	}
	
	public QueryBuilder withSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		return this;
	}
	public QueryBuilder withQuery(String query) {
		queryString = query;
		
		return this;
	}
	public QueryBuilder withParameters(Object... params) {
		this.params = params;
		return this;
	}
	 
	private Query createQuery(Session session, String query) {
		q = session.createQuery(query.toString());
		return q;
	}
	
	private void addParms(Object[] params) {
		if(qPos == null) {
			if(q == null)
				throw new RuntimeException("Query has not yet been specified");
            qPos = QueryPos.getInstance(q);
		}
		for (Object object : params) {
			if(object instanceof Boolean) {
				qPos.add((Boolean) object);
				continue;
			}
			if(object instanceof Double) {
				qPos.add((Double) object);
				continue;
			}
			if(object instanceof Float) {
				qPos.add((Float) object);
				continue;
			}
			if(object instanceof Integer) {
				qPos.add((Integer) object);
				continue;
			}
			if(object instanceof Long) {
				qPos.add((Long) object);
				continue;
			}
			if(object instanceof Short) {
				qPos.add((Short) object);
				continue;
			}
			if(object instanceof String) {
				qPos.add((String) object);
				continue;
			}
			if(object instanceof Timestamp) {
				qPos.add((Timestamp) object);
				continue;
			}
			throw new RuntimeException("Can't add parameter: " + object);
		}
	}
	
	public List toList(){
		return (List)new SessionTemplate(sessionFactory).doWithSession(new RunQueryAndReturnListSessionCallback(this.queryString));
	}
	public int toInt(){
		List doWithSession = (List)new SessionTemplate(sessionFactory).doWithSession(new RunQueryAndReturnListSessionCallback(this.queryString));
		return ((Number)doWithSession.get(0)).intValue();
	}
}