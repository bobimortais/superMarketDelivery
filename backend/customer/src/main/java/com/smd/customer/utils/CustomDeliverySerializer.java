package com.smd.customer.utils;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Class to serialize correctly the onSale attribute. On the API response, it must have
 * the String value ON_SALE when its value is true and its display name must be event
 * @author robsonz
 *
 */
public class CustomDeliverySerializer extends JsonSerializer<String>
{

	@Override
	/**
	 * Method to change the value of property event from a boolean to
	 * Open, Closed or Future
	 */
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException
	{
		if(value.equals("o"))
			value = "Open";
		else if(value.equals("c"))
			value = "Closed";
		else if(value.equals("f"))
			value = "Future";
		gen.writeString(value);
	}	

}
