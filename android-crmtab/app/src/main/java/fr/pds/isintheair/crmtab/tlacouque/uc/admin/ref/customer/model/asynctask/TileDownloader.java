package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.MapInfo;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.CustomerService;

/**
 * Created by tlacouque on 04/02/2016.
 */
public class TileDownloader extends AsyncTask<MapInfo,Integer,Boolean> {

    @Override
    protected Boolean doInBackground(MapInfo... params) {
        MapInfo mapInfo = params[0];
        String url = CustomerService.BASE_URL+ FormatValidator.formatUrlTile(mapInfo);
        Bitmap img = null;

        while (img == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               //do something
            }
            img = downloadBitmap (url);
            if(img == null) {
                Log.d("ImgNull","Image est null");
            }
        }

        return saveImage(img,FormatValidator.formatPathTile(mapInfo));


       /**
        * Environment.getExternalStorageDirectory().toString()+
        "/osmdroid/tiles/Mapnik/15/16597/test.png.tile"
        *
        *
        * MapTile mapTile = new MapTile(mapInfo.getX(),mapInfo.getY(),mapInfo.getZ());
        ITileSource tileSource = TileSourceFactory.MAPNIK;
        return  new TileWriter().saveFile(tileSource,mapTile,img);
*/
      //  return true;
    }


    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
           // return inputStream;
           if (inputStream != null) {

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            Log.d("URLCONNECTIONERROR", e.toString());
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();

            }
        }
        return null;
    }


    public boolean saveImage(Bitmap bitmap, String path) {
        OutputStream fOut = null;
        File file = new File(path); // the File to save to
        file.getParentFile().mkdirs();
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // saving the Bitmap to a file compressed as a PNG with 100% compression rate
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
            fOut.close(); // do not forget to close the stream
        } catch (IOException e) {
            e.printStackTrace();
        }
       return true;
    }

}
