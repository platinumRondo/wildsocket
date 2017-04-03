package org.sunkengrotto.wildsocket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import org.sunkengrotto.wildsocket.echo.EchoWebSocket;

public class WildSocketConfig implements ServerApplicationConfig {

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
		Set<ServerEndpointConfig> result = new HashSet<>();

		ServerEndpointConfig conf = ServerEndpointConfig.Builder.create(EchoWebSocket.class, "/echo").build();
		result.add(conf);

		return result;
	}

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		return scanned;
	}

}
