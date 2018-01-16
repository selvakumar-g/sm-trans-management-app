package com.sm.app.transmanage.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author U811403
 *
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {

	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

	private static final Logger LOG = LoggerFactory.getLogger(JsonDateDeserializer.class);

	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
			throws IOException, JsonProcessingException {
		if (paramJsonParser.getText() != null && paramJsonParser.getText().trim().length() > 0) {
			String str = paramJsonParser.getText().trim();
			try {
				return DATEFORMAT.parse(str);
			} catch (ParseException e) {
				LOG.error("error in deseriliztion mvc date", e);
			}
			return paramDeserializationContext.parseDate(str);
		}
		return null;

	}
}
