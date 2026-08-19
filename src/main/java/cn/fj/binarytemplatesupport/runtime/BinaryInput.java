package cn.fj.loli.binarytemplatesupport.runtime;

public interface BinaryInput {
    long length();

    long revision();

    byte[] read(long offset, int length);

    default int readUnsignedByte(long offset) {
        byte[] value = read(offset, 1);
        return value.length == 0 ? -1 : value[0] & 0xff;
    }
}
