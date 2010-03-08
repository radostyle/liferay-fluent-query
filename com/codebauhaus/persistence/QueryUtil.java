package com.codebauhaus.persistence;

import com.liferay.portal.kernel.dao.orm.SessionFactory;

/**
 * A helper static method to make creating Liferay queries.
 * 
 * To use just import the static methods you want: 
 * <code> 
 * import static com.codebauhaus.portlet.shopping.util.QueryUtil.withSessionFactory;
 * </code>
 * then use it like this
 * 
 * <code> 
 *   withSessionFactory(this).withQuery( "select prod from  " + Product.class.getName() + " as prod where prod.parentId = ?")
 *			.withParameters(parentId).toList();
 * </code>
 *
 */
public class QueryUtil {

	/**
	 * The liferay query classes implement the SessionFactory interface so it can be used like this from a *FinderImpl object:
	 * <code>
	 * withSessionFactory(this)
	 * </code>
	 */
	public static QueryBuilder withSessionFactory(SessionFactory sessionFactory) {
		
		return new QueryBuilder().withSessionFactory(sessionFactory);
		
	}

}
