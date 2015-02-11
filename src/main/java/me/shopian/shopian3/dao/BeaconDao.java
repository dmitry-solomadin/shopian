package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.util.ColumnDirection;

import java.util.List;
import java.util.Map;

public interface BeaconDao {
    public void add(Beacon beacon);
    public void update(Beacon beacon);
    public List<Beacon> list();
    public List<Beacon> list(int start, int length , List<ColumnDirection> sortColumns, String search);
    public long count();
    public Beacon getBayUuidMajorMinor(Beacon beacon);
    public Beacon get(long id);
    public void delete(long id);
}
