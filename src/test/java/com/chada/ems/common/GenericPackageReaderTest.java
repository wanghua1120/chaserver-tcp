//package com.chada.ems.common;
//
//import com.chada.ems.BaseTest;
//import com.chada.ems.common.domain.traffic.TrafficPackage;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Hua Wang
// * Created On: 2019/4/24 14:24
// */
//public class GenericPackageReaderTest extends BaseTest {
//    @Test
//    public void read() throws Exception {
//    }
//
//    @Autowired
//    private GenericPackageReader packageReader;
//
//    @Test
//    public void getPackageLength() throws Exception {
//        String pg = "0086QN=20190415224851543;ST=91;CN=9021;PW=123456;MN=23040281003022;Flag=5;CP=&&Data=0BBE&&71C0";
//        Integer len = packageReader.pickupPackageLength(pg);
//        assertThat(len).isNotNull();
//    }
//
//    /**
//     * 一个通信包里包含多条指令
//     */
//    @Test
//    public void readMultipulInstructionFromSinglePackage() {
//        String pgs = "##0197ST=32;CN=2051;QN=20190424002721168;PW=123456;MN=23040281003022;Flag=5;CP=&&DataTime=20190416003000;w01018-Max=27.299999,w01018-Avg=27.300000,w01018-Min=27.299999,w01018-Cou=0.000000,w01018-Flag=N&&D081\n" +
//                "##0197ST=32;CN=2051;QN=20190424002721279;PW=123456;MN=23040281003022;Flag=5;CP=&&DataTime=20190416004000;w01018-Max=27.299999,w01018-Avg=27.300000,w01018-Min=27.299999,w01018-Cou=0.000000,w01018-Flag=N&&CB81\n" +
//                "##0197ST=32;CN=2051;QN=20190424002721389;PW=123456;MN=23040281003022;Flag=5;CP=&&DataTime=20190416005000;w01018-Max=27.299999,w01018-Avg=27.300000,w01018-Min=27.299999,w01018-Cou=0.000000,w01018-Flag=N&&8C41\n";
//        List<TrafficPackage> lst = new ArrayList<>();
//        String singleRawText;
//        packageReader.prepare(pgs);
//        while (!StringUtils.isEmpty((singleRawText = packageReader.readInstruction()))) {
//            TrafficPackage tp = TrafficPackage.createPackage(singleRawText, null, null);
//            lst.add(tp);
//        }
//        assertThat(lst).hasSize(3);
//    }
//
//    /**
//     * 一条指令由3个通信包组成，且第三个通信包最后部分是下一个指令的开始
//     */
//    @Test
//    public void readTruncatedPackage() {
//        String pg1 = "##1703ST=32;CN=3020;QN=20190424002722330;PW=123456;MN=23040281003022;Flag=5;CP=&&DataTime=20190424002000;DT=0;PolId=w01018;i11001-Info=//R:4e 26 26 45 30 34 31 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 32 30 31 31 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 37 33 34 30 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3";
//        packageReader.prepare(pg1);
//        boolean isAvaiable = packageReader.isAvailable();
//        assertThat(isAvaiable).isFalse().as("第1条通信包指令不完整，等待下个通信包再处理。");
//
//        String pg2 = "b 43 4e 3d 38 30 31 33 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 45 30 34 31 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 32 30 31 31 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 37 33 34 30 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 38 30 31 33 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 45 30 34 31 0d 0a 23 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 32 30 31 31 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 31 2d 46 6c 61 67 3d 4e 26 26 37 33 34 30 0d 0a 23";
//        packageReader.prepare(pg1);
//        isAvaiable = packageReader.isAvailable();
//        assertThat(isAvaiable).isFalse().as("第1、2条通信包指令不完整，等待下个通信包再处理。");
//
//        String pg3 = " 23 30 30 37 36 53 54 3d 33 32 3b 43 4e 3d 38 30 31 33 3b 50 57 3d 3b 4d 4e 3d 3b 43 50 3d 26 26 44 61 74 61 54 69 6d 65 3d 32 30 31 38 31 32 32 35 30 38 30 30 30 30 3b 30 31 31 2d 52 74 64 3d 32 37 2e 33 2c 30 31 01 //&&3B80##0197ST=32;CN=2061;QN=20190424002722671;PW=123456;MN=23040281003022;Flag=5;CP=&&DataTime=20190416010000;w01018-Max=27.299999,w01018-Avg=27.299999,w01018-Min=27.299999,w01018-Cou=0.000000,w01018-Flag=N&&84C1\n";
//        List<TrafficPackage> trafficPackages = new ArrayList<>();
//        String singleRawText;
//        packageReader.prepare(pg1);
//        while (!StringUtils.isEmpty((singleRawText = packageReader.readInstruction()))) {
//            TrafficPackage tp = TrafficPackage.createPackage(singleRawText, null, null);
//            trafficPackages.add(tp);
//        }
//        assertThat(trafficPackages).hasSize(2);
//    }
//
//}