package br.dev.sampaio;

import java.util.HashMap;
import java.util.Map;

public class DataHolder {
    private static final Map<String, Object> map = new HashMap<>();

    private DataHolder() {
    }

    public static void put(String key, Object value) {
        map.put(key, value);
    }

    public static Object get(String key) {
        Object value = map.get(key);

        if (value == null) {
            throw new NullPointerException("O mapa da classe DataHolder não contém a chave: " + key);
        } else {
            return value;
        }
    }
}