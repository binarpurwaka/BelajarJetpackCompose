package com.binar.belajarjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binar.belajarjetpackcompose.ui.theme.BelajarJetpackComposeTheme
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import com.binar.belajarjetpackcompose.ui.component.ReusableMultilineTextField
import com.binar.belajarjetpackcompose.ui.component.ReusableOutlinedTextField
import com.binar.belajarjetpackcompose.ui.component.ReusableSpinner


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BelajarJetpackComposeTheme {
                SecondApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SecondApp() {
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var selectedAgama by remember { mutableStateOf("") }
    val agamaOptions = stringArrayResource(R.array.agama).toList()
    var selectedJenisKelamin by remember { mutableStateOf("") }
    val jenisKelaminOptions = stringArrayResource(R.array.jeniskelamin).toList()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Second Activity") }) },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    style = typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    text = "Tambah Data")
                Spacer(modifier = Modifier.height(16.dp))
                ReusableOutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = "Nama",
                    value = nama,
                    onValueChange = { nama = it }
                )
                ReusableMultilineTextField(
                    label = "Alamat",
                    value = alamat,
                    onValueChange = { alamat = it }
                )
                ReusableSpinner(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = "Agama",
                    options = agamaOptions,
                    selectedOption = selectedAgama,
                    onSelectionChange = { selectedAgama = it }
                )
                ReusableSpinner(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = "Jenis Kelamin",
                    options = jenisKelaminOptions,
                    selectedOption = selectedJenisKelamin,
                    onSelectionChange = { selectedJenisKelamin = it }
                )
            }
        })
}


