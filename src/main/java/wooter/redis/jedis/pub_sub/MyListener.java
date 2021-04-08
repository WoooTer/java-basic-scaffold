package wooter.redis.jedis.pub_sub;

import redis.clients.jedis.JedisPubSub;

public class MyListener extends JedisPubSub {

    public void onMessage(String channel, String message) {
        System.out.println("sub：" + message + "[channel:" + channel + "]");
    }

    public void onSubscribe(String channel, int subscribedChannels) {
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    public void onPMessage(String pattern, String channel, String message) {
    }

}
