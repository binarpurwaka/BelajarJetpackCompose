package com.binar.belajarjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binar.belajarjetpackcompose.ui.component.AdvancedUserProfileCard
import com.binar.belajarjetpackcompose.ui.component.ReusableOutlinedTextField
import com.binar.belajarjetpackcompose.ui.theme.BelajarJetpackComposeTheme

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

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameCounterApp() {
    var name by remember { mutableStateOf("") }
    var submittedName by remember { mutableStateOf("") }
    val context = LocalContext.current
    var count by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

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
                AdvancedUserProfileCard(
                    modifier = Modifier,
                    "Binar",
                    "Programmer",
                    R.drawable.mrwhite,
                    onClick = {})
                ReusableOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .focusRequester(focusRequester),
                    value = name,
                    onValueChange = { name = it },
                    label = "Masukkan Nama"
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    onClick = {
                        if (name.isNotEmpty()) {
                            submittedName = name
                        }else{
                            Toast.makeText(context, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                            focusRequester.requestFocus()
                        }
                    },
                ) { Text(text = "Submit") }

                AdvancedUserProfileCard(
                    name = submittedName.ifEmpty { "Mr. White" },
                    profession = "UI/UX Designer",
                    profileImageRes = R.drawable.mrwhite,
                    subtitle = "Available for projects",
                    isOnline = true,
                    showOnlineIndicator = true,
                    onClick = {
                        Toast.makeText(
                            context,
                            "Clicked on $submittedName profile",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    },
                    trailingContent = {
                        IconButton(
                            onClick = {
                                Toast.makeText(context, "More options", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                    }
                )
                Button(
                    onClick = { count++ }) {
                    Text("Klik: $count kali")
                }
                Text("Kamu klik $count kali.")
                Spacer(modifier = Modifier.width(32.dp))
                val context = LocalContext.current
                Button(onClick = {
                    val intent = Intent(context, SecondActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "Pindah Ke Activity 2")
                }
                Spacer(modifier = Modifier.width(32.dp))
                Button(onClick = {
                    val intent = Intent(context, ThirdActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = "Pindah Ke Activity 3")
                }
            }
        }
    )
}
