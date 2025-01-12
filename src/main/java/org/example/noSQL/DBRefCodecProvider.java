package org.example.noSQL;

import com.mongodb.DBRef;
import com.mongodb.DBRefCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class DBRefCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == DBRef.class) {
            return (Codec<T>) new DBRefCodec(registry);
        }
        return null;
    }
}
