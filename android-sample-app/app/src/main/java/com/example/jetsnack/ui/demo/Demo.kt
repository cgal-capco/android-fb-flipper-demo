package com.example.jetsnack.ui.demo

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetsnack.core.Analytics
import com.example.jetsnack.ui.components.JetsnackButton
import com.example.jetsnack.ui.theme.JetsnackTheme

@Composable
fun Demo(
    modifier: Modifier = Modifier,
    viewModel: DemoViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        Analytics.trackPage("Demo Page")
    }

    val userName = remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        JetsnackButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.crashApp()
            }) {
            Text(
                text = "Crash app",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(24.dp))
        JetsnackButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.seedSqlDatabase()
            }) {
            Text(
                text = "Seed database",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(24.dp))
        JetsnackButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.resetAppSettings()
            }) {
            Text(
                text = "Reset Shared Preferences",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(24.dp))
        JetsnackButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.sync()
            }) {
            Text(
                text = "Sync data",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(24.dp))
        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                viewModel.toggleFirstTimeOpen(it)
            }
        )
        Column() {
            Text(
                text = "Counter",
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                JetsnackButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        viewModel.increaseCounter()
                    }) {
                    Text(
                        text = "+1",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.width(12.dp))
                JetsnackButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        viewModel.decreaseCounter()
                    }) {
                    Text(
                        text = "-1",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Github user",
                modifier = Modifier.fillMaxWidth(),
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userName.value,
                onValueChange = { value ->
                    userName.value = value
                })
            Spacer(Modifier.height(12.dp))
            JetsnackButton(
                onClick = {
                    viewModel.searchGithub(userName.value)
                }) {
                Text(
                    text = "Get Repos",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DemoPreview() {
    JetsnackTheme {
        Demo()
    }
}
