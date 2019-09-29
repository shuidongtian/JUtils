package com.jm.jutils_core;

import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

import bolts.CancellationToken;
import bolts.CancellationTokenSource;
import bolts.Task;

public class UdpHelper {

    static String TAG="UdpHelper";

    boolean isReceive=true;


    CancellationTokenSource cancellationTokenSource;

    public void startUdpReceiveMsg(final int port, final String liveEventBusKey) throws IOException {

        stopUdpReceiveMsg();
        isReceive=true;

        cancellationTokenSource = new CancellationTokenSource();
        CancellationToken token = cancellationTokenSource.getToken();
        Task.call(new Callable<Boolean>() {
                      @Override
                      public Boolean call() throws Exception {


                          while (isReceive) {



                              DatagramSocket socket = null;
                                  socket = new DatagramSocket(null);
                                  socket.setReuseAddress(true);
                                  socket.bind(new InetSocketAddress(port));


                              byte[] container = new byte[1024];
                              DatagramPacket packet = new DatagramPacket(container, 1024);

                              socket.receive(packet);
                              String result = new String(packet.getData(), packet.getOffset(), packet.getLength()); //packet 转换
//{"type":"0","latitude":"3723.2475","latdirection":"N","longitude":"12158.3416","longdirection":"W"}

//{"type":"1", "compass":"55" }

                              socket.close(); //必须及时关闭 socket，否则会出现 error

                              //Log.v(TAG, "startReceiveMsg:" + result);


                              LiveEventBus.get(liveEventBusKey).post(result);


                              Thread.sleep(500);
                          }


                          return true;
                      }
                  },
                Task.BACKGROUND_EXECUTOR,
                token);



    }


    public void stopUdpReceiveMsg() throws IOException {
            // 取消任务
        if (cancellationTokenSource != null) {
            cancellationTokenSource.cancel();
            isReceive=false;

        }


    }

    public static void sendUdp(final String ipOrDomain, final int port, final byte[] data) throws IOException{


            DatagramSocket socket = null;

            if (socket == null) {
                socket = new DatagramSocket(null);
                socket.setReuseAddress(true);
                socket.bind(new InetSocketAddress(port));
            }


            InetAddress host = InetAddress.getByName(ipOrDomain);


            DatagramPacket request = new DatagramPacket(data, data.length, host, port);
            socket.send(request);


            socket.close();





    }


}
