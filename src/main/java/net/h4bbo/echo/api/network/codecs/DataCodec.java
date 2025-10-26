package net.h4bbo.echo.api.network.codecs;

public enum DataCodec {
    VL64_INT(Integer.class),
    BASE64_INT(Integer.class),
    STRING(String.class),
    BOOL(Boolean.class),
    BYTES(byte[].class);

    private final Class<?> type;

    DataCodec(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }
}
