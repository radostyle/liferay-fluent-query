/**
 * 
 */
package com.codebauhaus.persistence;


interface SessionOperations {
    Object doWithSession(SessionCallback withSession);
}