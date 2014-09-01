package multithreadedserver;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Grønborg
 */
public class MultiThreadedServer implements Runnable {

    private ServerSocket serverSocket = null;
    private int serverPort = 8014; // default port
    private boolean isStopped = false;
    private Thread runnungThread = null;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    @Override
    public void run() {
        synchronized (this) {
            this.runnungThread = Thread.currentThread();
        }
        System.out.println("Starting server");
        openServerSocket();
        while (!isStopped) {
            Socket clientSocket;
            
            try {
                clientSocket = this.serverSocket.accept();
            } catch (Exception ex) {
                if (isStopped) {
                    System.out.println("Server is stopped");
                    return; // spring ud af loop
                }
                throw new RuntimeException("Error accepting client", ex);
            }
            new Thread(new WorkerRunnable(clientSocket, "multithreaded Server")).start();
        }
        System.out.println("server stopped");
    }

    
    
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (Exception e) {
            System.out.println("Closnig error");
        }
    
    }
    
    
    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket();
            this.serverSocket.bind(new InetSocketAddress("127.0.0.1", serverPort));
        } catch (Exception e) {
            throw new RuntimeException("cannot open default port " + this.serverPort);
        }

    }
}
