/**
 * 
 */
package com.codebauhaus.persistence;

import com.liferay.portal.kernel.dao.orm.Session;

/**
 * Provides template method
 * <code>
 * 	public Object doWithSession(Session session);
 * </code>
 * for working with Liferay session.
 * 
 * @see SessionTemplate
 */
interface SessionCallback {
	public Object doWithSession(Session session);
}