package turksheadsw;

import java.io.*;
import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.PropertiesPersister;

/**
 * Loads java Properties from a JSON-formatted file instead of a .properties
 * formatted file.
 *
 * NB: Uses the Jackson JSON parser (http://jackson.codehaus.org), which is
 * also used by spring.
 *
 * NB: Calling the *XML() methods will result in UnsupportedOperationException
 * errors.
 */
public class JsonPropertiesPersister
    implements PropertiesPersister
{
    private ObjectMapper mapper = new ObjectMapper();

    public void load(Properties props, InputStream is)
        throws IOException
    {
        this.load(props, new InputStreamReader(is));
    }

    public void load(Properties props, Reader reader)
        throws IOException
    {
        Map<String,String> pmap = mapper.readValue(reader, Map.class);

        for(String key : pmap.keySet()) {
            props.setProperty(key, String.valueOf(pmap.get(key)));
        }
    }

    public void store(Properties props, OutputStream os, String header)
        throws IOException
    {
        this.store(props, new OutputStreamWriter(os), header);
    }

    public void store(Properties props, Writer writer, String header)
        throws IOException
    {
        Map<String,String> pmap = new HashMap<String,String>();
        for(Enumeration e = props.propertyNames(); e.hasMoreElements();) {
            String pname = (String) e.nextElement();
            pmap.put(pname, String.valueOf(props.getProperty(pname, "")));
        }

        this.mapper.writeValue(writer, pmap);
    }

    public void loadFromXml(Properties props, InputStream is)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public void storeToXml(Properties props, OutputStream os, String header)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public void storeToXml(Properties props, OutputStream os, String header,
            String encoding)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }
}
