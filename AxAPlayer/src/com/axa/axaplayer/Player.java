package com.axa.axaplayer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Player extends Activity {

	MediaPlayer son_play = new MediaPlayer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		Button playbutton = (Button)findViewById(R.id.playbutton);
		Button playlistbutton = (Button)findViewById(R.id.playlist);
		playlistbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ib = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(ib);
			}
		});
		playbutton.setOnClickListener(new View.OnClickListener() {
			String song_pt = getIntent().getStringExtra("song");
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(son_play.isPlaying()){
					son_play.stop();
					textchanger();
				}else{
					try{
						son_play.reset();
						son_play.setDataSource(song_pt);
						son_play.prepare();
						son_play.start();
						textchanger();
					}catch(Exception ignre){}
				}
			}
		});
	}

	void textchanger(){
		Button playbutton = (Button)findViewById(R.id.playbutton);
		if(son_play.isPlaying()){
			playbutton.setText(R.string.playbutton);
		}else{
			playbutton.setText(R.string.pausebutton);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
