package org.example.noSQL;

import com.mongodb.ConnectionString;
import com.mongodb.DBRefCodecProvider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistries;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;


public class noSQLDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "noSQLBazaDanych";

    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();
            CodecRegistry customCodecRegistry = CodecRegistries.fromCodecs(new DBRefCodec());
            CodecRegistry combinedRegistry = CodecRegistries.fromRegistries(
                    defaultCodecRegistry,
                    customCodecRegistry
            );

            database = MongoClients.create(CONNECTION_STRING)
                    .getDatabase(DATABASE_NAME)
                    .withCodecRegistry(combinedRegistry);

            System.out.println("DEBUG: Codec Registry Registered: " + combinedRegistry);
        }
        return database;
    }

}
