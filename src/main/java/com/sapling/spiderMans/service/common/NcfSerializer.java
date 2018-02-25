package com.sapling.spiderMans.service.common;

/**
 * 1=>hessian<br>
 * 2=>kryo(未实现)<br>
 * Created by tianjie on 4/4/15.
 */
public interface NcfSerializer {
    /**
     * 将数据序列化为对象
     *
     * @param message
     *
     * @return
     */
    <T> byte[] encode(T message);

    /**
     * 将二进制数据序列化为对象
     *
     * @param bytes
     *
     * @return
     */
    <T> T decode(byte[] bytes);

}
