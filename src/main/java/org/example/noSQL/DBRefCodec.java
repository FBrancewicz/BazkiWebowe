package org.example.noSQL;

import com.mongodb.DBRef;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;


public class DBRefCodec implements Codec<DBRef> {
    @Override
    public DBRef decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        String ref = reader.readString("$ref");
        Object id = reader.readObjectId("$id");
        reader.readEndDocument();
        return new DBRef(ref, id);
    }

    @Override
    public void encode(BsonWriter writer, DBRef value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("$ref", value.getCollectionName());
        writer.writeObjectId("$id", (org.bson.types.ObjectId) value.getId());
        writer.writeEndDocument();
    }

    @Override
    public Class<DBRef> getEncoderClass() {
        return DBRef.class;
    }
}
