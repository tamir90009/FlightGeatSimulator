package connect;

import java.io.IOException;

public interface Client {
   void close() throws IOException;

   void set(String var1, Double var2);

   void run();
}
