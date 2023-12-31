package edu.hw2.task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicConnection implements Connection {

    public static final String EXECUTE_CLOSED_MESSAGE = "closed, but execute() was called";
    public static final String CLOSE_CLOSED_MESSAGE = "closed, but close() was called";


    private final static Logger LOGGER = LogManager.getLogger();
    private ConnectionStatus connectionStatus;

    public BasicConnection() {
        connectionStatus = ConnectionStatus.OPEND;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    @Override
    public void execute(String command)  {
        if (connectionStatus.equals(ConnectionStatus.OPEND)) {
            LOGGER.info(command);
        } else {
            throw new ConnectionException(EXECUTE_CLOSED_MESSAGE);
        }
    }

    @Override
    public void close() throws Exception {
        if (connectionStatus.equals(ConnectionStatus.OPEND)) {
            connectionStatus = ConnectionStatus.CLOSED;
        } else {
            throw new ConnectionException(CLOSE_CLOSED_MESSAGE);
        }
    }
}
