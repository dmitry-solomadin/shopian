package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.BeaconDao;
import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.util.ColumnDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeaconServiceImpl implements BeaconService {


    @Qualifier("beaconDaoImpl")
    @Autowired
    private BeaconDao beaconDao;

    @Override
    public void add(Beacon beacon) {
        beaconDao.add(beacon);
    }

    @Override
    public void update(Beacon beacon) {
        beaconDao.update(beacon);
    }

    @Override
    public List<Beacon> list() {
        return beaconDao.list();
    }

    @Override
    public List<Beacon> list(int start, int length, List<ColumnDirection> sortColumns, String search) {
        return beaconDao.list(start, length, sortColumns, search);
    }

    @Override
    public long count() {
        return beaconDao.count();
    }

    @Override
    public long count(String search) {
        return beaconDao.count(search);
    }

    @Override
    public Beacon getBayUuidMajorMinor(Beacon beacon) {
        return beaconDao.getBayUuidMajorMinor(beacon);
    }

    @Override
    public Beacon get(long id) {
        return beaconDao.get(id);
    }

    @Override
    public void delete(long id) {
        beaconDao.delete(id);
    }
}
