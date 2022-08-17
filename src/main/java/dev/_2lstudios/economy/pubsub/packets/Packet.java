package dev._2lstudios.economy.pubsub.packets;

import com.google.gson.Gson;

public class Packet {
    public String getName() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
