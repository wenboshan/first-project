package com.sapling.spiderMans.service.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

/**
 * Created by tianjie on 9/16/15.
 */
public class KryoSerializer implements NcfSerializer {
    KryoPool pool = new KryoPool.Builder(new KryoFactory() {
        @Override
        public Kryo create() {
            Kryo kryo = new Kryo();
            // config here
            return kryo;
        }
    }).softReferences().build();

    @Override
    public <T> byte[] encode(T message){
        if (message == null) return null;

        Kryo kryo = pool.borrow();
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteOutputStream);

        try{
            kryo.writeClassAndObject(output, message);
            return output.getBuffer();
        } finally {
            pool.release(kryo);
            try {
                byteOutputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            output.clear();
        }
    }

    @Override
    public <T> T decode(byte[] bytes) {
        if (bytes == null) return null;
        Kryo kryo = pool.borrow();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(inputStream);
        try {
            return (T) kryo.readClassAndObject(input);
        } finally {
            pool.release(kryo);
            try {
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            input.close();
        }
    }
}
