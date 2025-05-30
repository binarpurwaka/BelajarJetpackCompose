package com.binar.belajarjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.binar.belajarjetpackcompose.ui.theme.BelajarJetpackComposeTheme
import kotlin.contracts.contract

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BelajarJetpackComposeTheme {
                NameCounterApp()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameCounterApp() {
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current
    var count by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Demo Jetpack Compose") }) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserProfileCard(modifier=Modifier, "Binar","Programmer",R.drawable.mrwhite,onClick={
                    Toast.makeText(context, "Clicked on Binar's profile", Toast.LENGTH_SHORT).show()
                })
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Masukkan Nama") }
                )
                Button(
                    onClick = { count++ }) {
                    Text("Klik: $count kali")
                }
                Text("Halo, $name! Kamu klik $count kali.")
                Spacer(modifier = Modifier.width(32.dp))
                val context = LocalContext.current
                Button(onClick = {
                    val intent = Intent(context, SecondActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "Pindah Ke Activity 2")
                }
            }
        }
    )
}

@Composable
fun UserProfileCard(
    modifier: Modifier = Modifier,
    userName: String,
    userBio: String,
    profileImage: Int? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(0.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            profileImage?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = userBio,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
