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

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.net.UnknownHostException;

// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
// if it's a member of a replica set:

public class DBManager {
	
	MongoClient mongoClient;
	public DB db;
	
	public DBManager() {
	try {
		mongoClient = new MongoClient( "localhost" );
		this.connect();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	// or
//	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//	
//	MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
//	                                      new ServerAddress("localhost", 27018),
//	                                      new ServerAddress("localhost", 27019)));

	public void connect() {
		db = mongoClient.getDB( "bankonet_db" );
	}
}
