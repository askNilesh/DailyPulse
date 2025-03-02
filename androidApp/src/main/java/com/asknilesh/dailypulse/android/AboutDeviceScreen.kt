package com.asknilesh.dailypulse.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asknilesh.dailypulse.PlatForm

@Composable fun AboutDeviceScreen(onUpButtonClick: () -> Unit) {
  Scaffold(topBar = {
    AboutDeviceTopBar(onUpButtonClick)
  }) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(horizontal = 20.dp)
    ) {
      AboutDeviceInfo()
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class) @Composable
fun AboutDeviceTopBar(onUpButtonClick: () -> Unit) {
  TopAppBar(title = {
    Text("About Device")
  }, navigationIcon = {
    IconButton(onClick = onUpButtonClick) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Up Button",
      )
    }
  })
}

@Composable fun AboutDeviceInfo(modifier: Modifier = Modifier) {
  val items = getDeviceInfoList()
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(items) { row ->
      DeviceInfoItem(
        title = row.first, body = row.second
      )
    }
  }
}

@Composable fun DeviceInfoItem(title: String, body: String) {
  Column(modifier = Modifier.padding(8.dp)) {
    Text(
      text = title, style = TextStyle(
        fontSize = 14.sp, color = Color.Gray
      )
    )
    Text(
      text = body, style = TextStyle(
        fontSize = 16.sp, color = Color.Black
      )
    )
    Spacer(modifier = Modifier.height(3.dp))
    Divider()
  }
}

private fun getDeviceInfoList(): List<Pair<String, String>> {
  val platForm = PlatForm()
  return listOf(
    Pair("Operating System", "${platForm.osName} ${platForm.osVersion}"),
    Pair("Device", "${platForm.deviceModel} "),
    Pair("Density", "${platForm.density} "),
  )
}