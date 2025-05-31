package com.binar.belajarjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringArrayResource


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
    //val agamaOptions = listOf("Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Konghucu")
    //var selectedAgama by remember { mutableStateOf(agamaOptions[0]) }
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
                Text(modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                    style = typography.headlineMedium,
                    text = "Tambah Data")
                Spacer(modifier = Modifier.padding(16.dp))
                ReusableOutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = "Nama",
                    value = nama,
                    onValueChange = { nama = it }
                )
                ReusableOutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = "Alamat",
                    value = alamat,
                    onValueChange = { alamat = it }
                )
                ReusableSpinner(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    label = "Agama",
                    options = agamaOptions,
                    selectedOption = selectedAgama,
                    onSelectionChange = { selectedAgama = it }
                )
                ReusableSpinner(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    label = "Jenis Kelamin",
                    options = jenisKelaminOptions,
                    selectedOption = selectedJenisKelamin,
                    onSelectionChange = { selectedJenisKelamin = it }
                )
            }
        })
}

@Composable
fun ReusableOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableSpinner(
    label: String,
    options: List<String>,
    selectedOption: String,
    onSelectionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
