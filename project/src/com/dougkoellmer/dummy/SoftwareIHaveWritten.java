package com.dougkoellmer.dummy;

import java.io.Writer;

public abstract class SoftwareIHaveWritten
{
    private Document m_out;

    public SoftwareIHaveWritten(Document out)
    {
        m_out = out;
    }

    protected void writeLink(String value, String link)
    {
        m_out.write(value + "\n");
    }

    protected void writeDetails(String value)
    {
        m_out.write(value + "\n\n");
    }
}