package com.asknilesh.dailypulse

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

actual class PlatForm {
  actual val osName: String
    get() = UIDevice.currentDevice.systemName
  actual val osVersion: String
    get() = UIDevice.currentDevice.systemVersion
  actual val deviceModel: String
    get() = UIDevice.currentDevice.model
  actual val density: Int
    get() = UIScreen.mainScreen.scale.toInt()

  actual fun logSystemInfo() {
    NSLog("Daily Pulse -> ($osName, $osVersion, $deviceModel, $density)")
  }
}