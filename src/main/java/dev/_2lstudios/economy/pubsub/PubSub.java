package dev._2lstudios.economy.pubsub;

import dev._2lstudios.economy.config.Configuration;
import dev._2lstudios.economy.pubsub.impl.NonePubSub;
import dev._2lstudios.economy.pubsub.packets.Packet;

public interface PubSub {
    public boolean connect();
    public boolean disconnect();
    public void send(Packet packet);

    public static PubSub create(Configuration config, PubSubHandler handler) {
        String driver = config.getString("pubsub.driver");

        if (driver.equalsIgnoreCase("none")) {
            return new NonePubSub(handler);
        } else {
            return null;
        }
    }
}
