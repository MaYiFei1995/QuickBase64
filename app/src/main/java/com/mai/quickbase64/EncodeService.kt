package com.mai.quickbase64

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.service.quicksettings.Tile
import android.util.Base64
import android.service.quicksettings.TileService as BaseTitleService

class EncodeService : BaseTitleService() {

    override fun onBind(intent: Intent?): IBinder? {
        BaseTitleService.requestListeningState(this,
            ComponentName(this, EncodeService::class.java)
        )
        return super.onBind(intent)
    }

    override fun onStartListening() {
        super.onStartListening()
        qsTile.state = Tile.STATE_INACTIVE
        qsTile?.updateTile()
    }

    override fun onClick() {
        super.onClick()
        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var text = ""
        clipboardManager.primaryClip?.let {
            text = clipboardManager.primaryClip!!.getItemAt(0)?.text.toString()
        }
        clipboardManager.primaryClip = ClipData.newPlainText(
            "AfterEncode",
            String(Base64.encode(text.toByteArray(), Base64.NO_WRAP))
        )
    }
}