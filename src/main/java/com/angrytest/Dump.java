package com.angrytest;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.jacoco.core.tools.ExecDumpClient;
import org.jacoco.core.tools.ExecFileLoader;

public class Dump {
    public static void main(String[] args) {
        ExecDumpClient client = new ExecDumpClient() {
            protected void onConnecting(InetAddress address, int port) {
                System.out.println(String.format("Connecting to %s:%s", address, port));
            }

            protected void onConnectionFailure(IOException exception) {
                System.out.println(exception.getMessage());
            }
        };
        client.setDump(true);
        client.setReset(false);
        client.setRetryCount(10);

        try {
            ExecFileLoader loader = client.dump(args[0], Integer.parseInt(args[1]));
            loader.save(new File(args[2]), true);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
