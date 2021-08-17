package com.chada.ems.common.domain.traffic;

import com.chada.ems.BaseTest;
import org.junit.Test;
import org.springframework.data.util.Pair;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Hua Wang
 * Created On: 2019/5/2 12:34
 */
public class TrafficPackageTest extends BaseTest {
    @Test
    public void getDataRegionValues() throws Exception {
        String singleRawText = "##0262QN=20190430101750562;ST=21;CN=3020;PW=624930;MN=A140100_2006;Flag=9;CP=&&DataTime=20190430101750;PolId=w01019,i12001-Info=0,i12002-Info=0;PolId=w21001,i12001-Info=0,i12002-Info=0;PolId=w21003,i12001-Info=0,i12002-Info=0;PolId=w21011,i12001-Info=0,i12002-Info=0&&FB41";
        List<List<Pair<String, String>>> dataRegionProjects = TrafficPackage.getDataRegionValues(singleRawText);
        assertThat(dataRegionProjects).size().isGreaterThan(0);
    }

}