package entities;

import org.javalite.activejdbc.Model;

public class Config extends Model {
    static{
        validatePresenceOf("key", "value");
	    }
}
