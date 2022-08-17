package dev._2lstudios.economy.pubsub.impl;

import dev._2lstudios.economy.pubsub.PubSub;
import dev._2lstudios.economy.pubsub.PubSubHandler;
import dev._2lstudios.economy.pubsub.packets.BalanceSyncPacket;
import dev._2lstudios.economy.pubsub.packets.Packet;

public class NonePubSub implements PubSub {
    private PubSubHandler handler;

    public NonePubSub(PubSubHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public boolean disconnect() {
        return true;
    }

    @Override
    public void send(Packet packet) {
        if (packet instanceof BalanceSyncPacket) {
            this.handler.handle((BalanceSyncPacket) packet);
        }   
    }
}
