package turksheadsw;

import java.io.*;
import java.util.Properties;

import org.springframework.util.PropertiesPersister;

public class JsonPropertiesPersister
    implements PropertiesPersister
{
    public void load(Properties props, InputStream is)
        throws IOException
    {
    }

    public void load(Properties props, Reader reader)
    {
    }

    public void store(Properties props, OutputStream os, String header)
        throws IOException
    {
    }

    public void store(Properties props, Writer writer, String header)
        throws IOException
    {
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
