package com.axa.axaplayer;



import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

class Mp3Filter implements FilenameFilter
    {
        public boolean accept (File dir, String name)
            {
                return (name.endsWith(".mp3"));
            }
    }

@SuppressLint("SdCardPath")

public class MainActivity extends ListActivity {




//sdcard permission
File sdDir = Environment.getExternalStorageDirectory();

private static final String SD_PATH = new String("/sdcard/Music/");

private List<String> songs = new ArrayList<String>();
private MediaPlayer mp = new MediaPlayer();

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    updatePlayList();

    Button stopPlay = (Button) findViewById(R.id.playbutton);
    stopPlay.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mp.stop();

        }
    });

}

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	/*
	try {
		mp.reset();
		mp.setDataSource(SD_PATH + songs.get(position));
		mp.prepare();
		mp.start();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	String song_path=(SD_PATH+songs.get(position).toString());
	Intent ij = new Intent(this,Player.class);
	ij.putExtra("song", song_path);
	startActivity(ij);
	super.onListItemClick(l, v, position, id);
}

private void updatePlayList() {
    // TODO Auto-generated method stub
    File home = new File(SD_PATH);
    if (home.listFiles(new Mp3Filter()).length>0)
        {
            for (File file : home.listFiles(new Mp3Filter()))
                {
                    songs.add(file.getName());
                }
            ArrayAdapter <String> songList = new ArrayAdapter<String>(this, R.layout.song_item, songs);
            setListAdapter(songList); 
        }
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}


}