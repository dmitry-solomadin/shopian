package me.shopian.shopian3.service;

import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.util.ColumnDirection;

import java.util.List;
import java.util.Map;

public interface BeaconService {
    public void add(Beacon beacon);
    public void update(Beacon beacon);
    public List<Beacon> list();
    public List<Beacon> list(int start, int length , List<ColumnDirection> sortColumns, String search);
    public long count();
    public long count(String search);
    public Beacon getBayUuidMajorMinor(Beacon beacon);
    public Beacon getBayUuidMajorMinor(String uuid,int major, int minor);
    public Beacon get(long id);
    public void delete(long id);
}
