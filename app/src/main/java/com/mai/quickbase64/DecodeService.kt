package com.mai.quickbase64

import android.content.ClipData
import android.content.ClipboardManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Base64

class DecodeService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        qsTile.state = Tile.STATE_INACTIVE
        qsTile?.updateTile()
    }

    override fun onClick() {
        super.onClick()
        qsTile.state = Tile.STATE_INACTIVE
        qsTile?.updateTile()
        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var text = ""
        clipboardManager.primaryClip?.let {
            text = clipboardManager.primaryClip!!.getItemAt(0)?.text.toString().trim().replace("=", "")
        }
        clipboardManager.primaryClip =
            ClipData.newPlainText("AfterDecode", String(Base64.decode(text, Base64.NO_PADDING)))
    }

}