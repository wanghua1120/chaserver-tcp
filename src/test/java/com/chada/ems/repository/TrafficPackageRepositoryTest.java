package com.chada.ems.repository;

import com.chada.ems.BaseTest;
import com.chada.ems.common.domain.traffic.TrafficPackage;
import com.chada.ems.common.util.BytesUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 16:22
 */
public class TrafficPackageRepositoryTest extends BaseTest {
    @Autowired
    private TrafficPackageRepository trafficPackageRepo;

    @Test
    public void saveOneTrafficPackage() {
        // 此包长度超过 1024
        String str = "##1703ST=32;CN=3020;QN=20190424002722330;PW=123456;MN=23040281003022;Flag=5;CP=&&DataTime=20190424002000;DT=0;PolId=w01018;i11001-Info=//R:4e 26 26 45 30 34 31 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 32 30 31 31 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 37 33 34 30 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 38 30 31 33 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 45 30 34 31 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 32 30 31 31 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 37 33 34 30 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 38 30 31 33 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 45 30 34 31 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 32 30 31 31 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 37 33 34 30 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 38 30 31 33 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 01 //&&3B80";
        TrafficPackage tp = TrafficPackage.createGenericPackage(str, null, null);
        trafficPackageRepo.save(tp);
        trafficPackageRepo.delete(tp);
    }

    @Test
    public void saveHttpPackage() {
        String str = "GET / HTTP/1.0\n" +
                "User-Agent: NetSystemsResearch studies the availability of various services across the internet. Our website is netsystemsresearch.com\n" +
                "Accept: */*\n";
        TrafficPackage tp = TrafficPackage.createHttpPackage(str, null, null);
        trafficPackageRepo.save(tp);
        trafficPackageRepo.delete(tp);
    }

}