package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@Getter
@Setter
public class noSQLProduct {

        private ObjectId id;
        private String name;
        private String details;
        private double price;

        private noSQLCategory category;
        private noSQLManufacturer manufacturer;
        private noSQLSale sale;

        public noSQLProduct(ObjectId id, String name, String details, double price) {
            this.id = id;
            this.name = name;
            this.details = details;
            this.price = price;
        }


    }


