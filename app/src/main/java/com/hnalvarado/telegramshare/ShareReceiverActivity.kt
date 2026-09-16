package com.hnalvarado.telegramshare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

class ShareReceiverActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent?.action != Intent.ACTION_SEND) {
            finish()
            return
        }

        // Bootstrap only: prove Android can route supported shares into this app.
        // Telegram transport is deliberately deferred until the base build is green.
        Toast.makeText(this, "Contenido recibido; envío Telegram aún no integrado", Toast.LENGTH_SHORT).show()
        finish()
    }
}
