package me.antidev.d1proxy.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

public class ProfilesCollection {

    private final MongoCollection<Document> collection;

    ProfilesCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public boolean profileExists(String username) {
        return (collection.find(eq("username", username)).first() != null);
    }

    public void insertNewProfile(String username) {
        Document profile = new Document();
        profile.put("username", username);
        profile.put("joined", System.currentTimeMillis());
        collection.insertOne(profile);
    }

    public Document getProfile(String username) {
        FindIterable<Document> queryResult = collection.find(eq("username", username));
        return queryResult.first();
    }

    public void incrementFightCells(String username) {
        collection.updateOne(eq("username", username), new Document().append("$inc", new Document().append("fightCells", 1)));
    }

    public void incrementMaps(String username) {
        collection.updateOne(eq("username", username), new Document().append("$inc", new Document().append("maps", 1)));
    }
}
