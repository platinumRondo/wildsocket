package org.sunkengrotto.wildsocket.echo;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoWebSocket extends Endpoint {
	private static final Logger logger = LoggerFactory.getLogger(EchoWebSocket.class);

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		logger.debug("New connection. ID: {} ", session.getId());
		session.addMessageHandler(String.class, new EchoHandler(session.getAsyncRemote()));
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		logger.debug("Closing connection. ID: {}", session.getId());
		super.onClose(session, closeReason);
	}

	@Override
	public void onError(Session session, Throwable thr) {
		logger.warn("Error on connection.", thr);
		super.onError(session, thr);
	}

	private static class EchoHandler implements MessageHandler.Whole<String> {
		private RemoteEndpoint.Async remote;

		public EchoHandler(RemoteEndpoint.Async remote) {
			this.remote = remote;
		}

		@Override
		public void onMessage(String message) {
			remote.sendText(message);
		}

	}

}
