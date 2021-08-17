//package com.chada.ems.service.impl;
//
//import com.chada.ems.BaseTest;
//import com.chada.ems.common.domain.traffic.TrafficPackage;
//import com.chada.ems.repository.TrafficPackageRepository;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
///**
// * @author Hua Wang
// * Created On: 2019/4/15 23:22
// */
//public class AnalysisPackageServiceImplTest extends BaseTest {
//    @Autowired
//    private TrafficPackageRepository trafficPackageRepo;
//
//    @Test
//    public void getPackageRawText() throws Exception {
//        PageRequest pageable = PageRequest.of(0, 5);
//        List<TrafficPackage> originalList = trafficPackageRepo.findAll(pageable).getContent();
//    }
//
//}