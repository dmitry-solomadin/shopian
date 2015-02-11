package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.BeaconDao;
import me.shopian.shopian3.dao.BeaconDaoImpl;
import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.util.ColumnDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BeaconServiceImpl implements BeaconService {


    @Qualifier("beaconDaoImpl")
    @Autowired
    private BeaconDao beaconDao;

    @Override
    @Transactional
    public void add(Beacon beacon) {
        this.beaconDao.add(beacon);
    }

    @Override
    @Transactional
    public void update(Beacon beacon) {
        this.beaconDao.update(beacon);
    }

    @Override
    @Transactional
    public List<Beacon> list() {
        return this.beaconDao.list();
    }

    @Override
    public List<Beacon> list(int start, int length , List<ColumnDirection> sortColumns, String search){
        return this.beaconDao.list(start,length,sortColumns,search);
    }

    @Override
    public long count() {
        return this.beaconDao.count();
    }

    @Override
    public Beacon getBayUuidMajorMinor(Beacon beacon) {
        return this.beaconDao.getBayUuidMajorMinor(beacon);
    }

    @Override
    @Transactional
    public Beacon get(long id) {
        return this.beaconDao.get(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        this.beaconDao.delete(id);
    }
}
