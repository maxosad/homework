package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MonoThreadClientHandler implements Runnable {
    private static final Map<String, String> SAYING_DICT = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    private final Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        this.clientDialog = client;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientDialog.getInputStream());
        DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
             clientDialog) {

            while (!clientDialog.isClosed()) {
                String key = in.readUTF();
                if (key.equalsIgnoreCase("quit")) {
                    break;
                }
                out.writeUTF(SAYING_DICT.get(key));
                out.flush();
            }
        } catch (EOFException ignore) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
