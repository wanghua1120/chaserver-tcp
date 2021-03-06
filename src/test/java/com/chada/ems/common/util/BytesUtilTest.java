package com.chada.ems.common.util;

import com.chada.ems.BaseTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Hua Wang
 *         Created On: 2019/4/29 22:09
 */
public class BytesUtilTest extends BaseTest {

    @Test
    public void hexGenerator() {
        String multipulLine = "##0086QN=20190429153335878;ST=91;CN=9021;PW=123456;MN=23040281003022;Flag=5;CP=&&Data=167E&&5500\n" +
                "##0086QN=20190429153335878;ST=91;CN=9021;PW=123456;MN=23040281003022;Flag=5;CP=&&Data=167E&&5500\n";
        String multipleLineHex = BytesUtil.byte2hex(multipulLine.getBytes());
        String multipleLineHexExpected = "232330303836514E3D32303139303432393135333333353837383B53543D39313B434E3D393032313B50573D3132333435363B4D4E3D32333034303238313030333032323B466C61673D353B43503D2626446174613D313637452626353530300A232330303836514E3D32303139303432393135333333353837383B53543D39313B434E3D393032313B50573D3132333435363B4D4E3D32333034303238313030333032323B466C61673D353B43503D2626446174613D313637452626353530300A";
        assertThat(multipleLineHex).isEqualTo(multipleLineHexExpected);
    }
}