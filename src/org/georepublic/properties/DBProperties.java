/* 
 * pgRouting Server
 * Copyright 2012, Georepublic. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.	
 */
package org.georepublic.properties;

import java.util.Properties;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class DBProperties {

	private static String password = null;
	private static String user     = null;
	private static String host     = null;
	private static String port     = null;
	private static String database = null;
	private static String keytable = null;
	
	public static void setProperties() {
	    // XXX make this a proper OSGi service so that it can use OSGi config
	    // and/or use Polymap configuration
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream in = cl.getResourceAsStream( "properties/database.properties" );
        try {
            Properties props = new Properties();
            props.load( in );
            props.putAll( System.getProperties() );
        
            String basename = "routing-service-osgi.db.";
            setUser( props.getProperty( basename + "USER" ) );
            setPassword( props.getProperty( basename + "PASSWORD" ) );
            setHost( props.getProperty( basename + "HOST" ) );
            setDatabase( props.getProperty( basename + "DATABASE" ) );
            setPort( props.getProperty( basename + "PORT" ) );
            //setKeytable( System.getenv( basename + "KEY_TABLE" ) );
        }
        catch (Exception e) {
            throw new RuntimeException( e );
        }
        finally {
            IOUtils.closeQuietly( in );
        }
	    
// start code to write to (polymap) config dir		
//	    String configDirProp = System.getProperty( "pgrouting.config.dir" );
//	    if (configDirProp != null) {
//	        File configDir = new File( configDirProp );
//	        if (!configDir.exists()) {
//	            configDir.mkdirs();
//	            ClassLoader cl = Thread.currentThread().getContextClassLoader();
//	            
//	            String[] configFiles = new String[] { "administration.properties", "database.properties", "routing.properties" };
//	            for (String configFile : configFiles) {
//	                InputStream in = null;
//	                OutputStream out = null;
//	                try {
//	                    in = cl.getResourceAsStream( "properties/" + configFile );
//	                    out = new FileOutputStream( new File( configDir, configFile ) );
//	                    IOUtils.copy( in, out );
//	                }
//	                catch (Exception e) {
//	                    
//	                }
//	                finally {
//	                    IOUtils.closeQuietly( in );
//	                    IOUtils.closeQuietly( out );
//	                }
//	            }
//	        }
//	    }
	    
// original code: file always comes from classpath, right? how to configure then??
//		ResourceBundle resb = 
//				ResourceBundle.getBundle("properties.database");
//		
//		setUser(resb.getString("USER"));
//		setPassword(resb.getString("PASSWORD"));
//	    setHost(resb.getString("HOST"));
//	    setDatabase(resb.getString("DATABASE"));
//	    setPort(resb.getString("PORT"));
//	    //setKeytable(resb.getString("KEY_TABLE"));
	}
	
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		DBProperties.password = password;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		DBProperties.user = user;
	}
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		DBProperties.host = host;
	}
	public static String getPort() {
		return port;
	}
	public static void setPort(String port) {
		DBProperties.port = port;
	}
	public static String getDatabase() {
		return database;
	}
	public static void setDatabase(String database) {
		DBProperties.database = database;
	}

	public static String getKeytable() {
		return keytable;
	}

	public static void setKeytable(String keytable) {
		DBProperties.keytable = keytable;
	}
	
	
}
