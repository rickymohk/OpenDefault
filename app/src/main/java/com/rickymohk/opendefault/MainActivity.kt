package com.rickymohk.opendefault

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickymohk.opendefault.ui.theme.OpenDefaultTheme

val greet ="""
    Tired of Apps opening links in browser ignoring that you have the dedicated App of the specific website installed?
    
    Share the URL to this App instead and it will open the link with the default App. 
""".trimIndent()

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if(intent.action == Intent.ACTION_SEND)
        {
            intent.extras
                ?.getString(Intent.EXTRA_TEXT)
                ?.let { Uri.parse(it) }
                ?.let { Intent(Intent.ACTION_VIEW,it) }
                .also { startActivity(it) }
            finish()
            return
        }
        setContent {

                OpenDefaultTheme {
                // A surface container using the 'background' color from the theme
                    Scaffold (
                        topBar = {
                            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
                        }
                    ){
                        val appIcon = painterResource(id = R.drawable.ic_launcher_foreground)
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .safeDrawingPadding(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ){
                            Image(modifier= Modifier.background(colorResource(id = R.color.ic_launcher_background)), painter = appIcon, contentDescription = "App icon")
                            Greeting(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = greet,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenDefaultTheme {

    }
}