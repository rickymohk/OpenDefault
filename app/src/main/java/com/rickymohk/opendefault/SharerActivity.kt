package com.rickymohk.opendefault

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity

class SharerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharer)

        if(intent.action == Intent.ACTION_SEND)
        {
            val uri = intent.extras
                ?.getString(Intent.EXTRA_TEXT)
                ?.let { Uri.parse(it) }
            Log.i("OpenDefault","uri: $uri, scheme: ${uri?.scheme}")

            if(uri?.scheme.isNullOrBlank())
            {
                Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
            }
            else
            {
                try{
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
                catch (ex:Throwable){
                    Toast.makeText(this, "Error opening URL", Toast.LENGTH_SHORT).show()
                }
            }
            finish()
            return
        }
    }
}