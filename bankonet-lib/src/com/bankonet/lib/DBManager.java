package com.bankonet.lib;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mysql.jdbc.Connection;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;

// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:

public class DBManager {
	public java.sql.Connection connection;
	MongoClient mongoClient;
	public DB db;
	
	public DBManager() {
	
	}

	public void connectMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankonet_db","root", "");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}

	public void connectMongo() {
		
		try {
			mongoClient = new MongoClient( "localhost" );
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = mongoClient.getDB( "bankonet_db" );
	}
}
