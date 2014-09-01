package multithreadedserver;

/**
 *
 * @author Grønborg
 */
public class ServerExecution {

    public static void main(String[] args) {
        MultiThreadedServer server = new MultiThreadedServer(8014);
        new Thread(server).start();

//        try {
//            ServerSocket s = new ServerSocket();
//            s.bind(new InetSocketAddress("127.0.0.1", 8014));
//            s.accept();
//        } catch (IOException ex) {
//            Logger.getLogger(ServerExecution.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
