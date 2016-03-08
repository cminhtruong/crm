package miage.pds.api.tlacouque.uc.admin.ref.customer.common;

import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by tlacouque on 01/02/2016.
 */
public class TileDownloader {



    public static boolean dwdTile(MapInfo mapInfo) {
        TileDownloaderThread tileDownloaderThread = new TileDownloaderThread(mapInfo);
        Thread threadDownloader = new Thread(tileDownloaderThread);
        threadDownloader.run();
        try {
            threadDownloader.join();
        } catch (InterruptedException e) {
            return false;
        }
        return tileDownloaderThread.isSaveDone();
    }


}
