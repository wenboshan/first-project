package com.sapling.spiderMans.service.common;

import java.util.logging.Logger;



/**
 * Created by tianjie on 4/4/15.
 */
public final class NcfSerializerFactory {
    private static Logger logger = Logger.getLogger(NcfSerializerFactory.class.getName());
    /**
     * 0->empty
     * 1->hessian
     * 2->kryo
     */
    private static NcfSerializer[] serializers =
            new NcfSerializer[5];

    private NcfSerializerFactory() {
    }

    static {
        serializers[1] = new HessianSerializer();
        serializers[2] = new KryoSerializer();
    }

    /**
     * 获取Serializer
     *
     * @param type
     * @return
     */
    public static NcfSerializer getSerializer(byte type) {
        if (type <= 0 || type > serializers.length
                || serializers[type] == null) {
            //            throw new Exception("不支持的序列化类型");
            logger.info("不支持的序列化类型");
        }
        return serializers[type];
    }
}
