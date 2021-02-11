package anywheresoftware.b4a;

public interface ConnectorConsumer {
    void putTask(byte[] bArr);

    boolean shouldAddPrefix();
}
