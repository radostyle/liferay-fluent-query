/**
 * 
 */
package com.codebauhaus.persistence;

import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;

/**
 * Provides a template object for working with a liferay session so that the try finally boilerplate session
 * handling code is not duplicated and can be handled consistently.
 */
public class SessionTemplate implements SessionOperations {
	SessionFactory sessionFactory;

	public SessionTemplate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Object doWithSession(SessionCallback withSession) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return withSession.doWithSession(session);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			sessionFactory.closeSession(session);
		}
	}

}