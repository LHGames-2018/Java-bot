package io.polyhx.lhgames.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.polyhx.lhgames.game.point.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointDeserializerTest {
    private final static String TEST_JSON = "{\"x\":2,\"y\":4}";

    @Test
    public void deserialize() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, new PointDeserializer()).create();
        Point point = gson.fromJson(TEST_JSON, Point.class);

        assertEquals(point.getX(), 2);
        assertEquals(point.getY(), 4);
    }
}