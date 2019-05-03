package com.chasegarsee.projectgdp.model;

import java.util.concurrent.atomic.AtomicLong;

public class GDP
{
    private static AtomicLong counter = new AtomicLong();
    private long id;
    private String name;
    private long gdp;

    public GDP()
    {
    }

    public GDP(String name, long gdp)
    {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = gdp;
    }

    public GDP(String name, String gdp)
    {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = Long.valueOf(gdp);
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getGdp()
    {
        return gdp;
    }

    public void setGdp(long gdp)
    {
        this.gdp = gdp;
    }

    @Override
    public String toString()
    {
        return "GDP{" + "id=" + id + ", name='" + name + '\'' + ", gdp='" + gdp + '\'' + '}';
    }
}
