package com.example.jetsnack.ui.demo

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetsnack.ui.components.JetsnackButton
import com.example.jetsnack.ui.theme.JetsnackTheme

@Composable
fun Demo(
    modifier: Modifier = Modifier,
    viewModel: DemoViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {
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
