package com.chada.ems.netty;

import com.chada.ems.common.util.BytesUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
public class ServerHandlerTest {

    @Test
    public void byteBufReadAndWrite() throws Exception {
        // 创建一个16字节的buffer,这里默认是创建heap buffer
        ByteBuf buf = Unpooled.buffer(16);
        // 写数据到buffer
        for (int i = 0; i < 16; i++) {
            buf.writeByte(i);
        }
        // 读数据，这里有一点需要注意的是：通过那些需要一个索引值参数的方法（getByte(i)）之一索引访问byte时不会改变真实的读索引和写索引，
        // 我们可以通过ByteBuf的readerIndex()或则writerIndex()函数来分别推进读索引和写索引。
        System.out.println(BytesUtil.byte2hex(buf.array()));
        for (int i = 0; i < buf.capacity(); i++) {
            System.out.print(buf.getByte(i) + ", ");
        }
    }
}