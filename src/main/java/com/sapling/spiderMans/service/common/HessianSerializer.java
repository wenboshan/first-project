package com.sapling.spiderMans.service.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * Created by tianjie on 4/4/15.
 */
public class HessianSerializer implements NcfSerializer {
    private Logger LOGGER = LoggerFactory.getLogger(HessianSerializer.class);

    @Override
    public <T> byte[] encode(T message) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(byteArray);
        try {
            output.writeObject(message);
            output.close();
            return byteArray.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                LOGGER.warn("关闭输出流出错", e);
            }
        }
    }

    @Override
    public <T> T decode(byte[] bytes){
        Hessian2Input input = new Hessian2Input(
                new ByteArrayInputStream(bytes));
        try {
            Object resultObject = input.readObject();
            return (T) resultObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
