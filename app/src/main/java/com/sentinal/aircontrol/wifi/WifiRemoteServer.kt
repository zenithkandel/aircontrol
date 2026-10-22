package com.sentinal.aircontrol.wifi

import android.util.Log
import com.google.gson.Gson
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

/**
 * Embedded WebSocket Server for Local Network Remote Control.
 * Acts as high-speed Wi-Fi fallback when Bluetooth HID is unavailable.
 */
class WifiRemoteServer(port: Int = 8765) : WebSocketServer(InetSocketAddress(port)) {

    companion object {
        private const val TAG = "WifiRemoteServer"
    }

    private val gson = Gson()
    var onCommandReceived: ((Map<String, Any>) -> Unit)? = null
    var onClientCountChanged: ((Int) -> Unit)? = null

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        Log.d(TAG, "Client connected: ${conn?.remoteSocketAddress}")
        onClientCountChanged?.invoke(connections.size)
        // Send welcome banner
        conn?.send("{\"type\":\"hello\",\"server\":\"Sentinal AirControl\",\"version\":\"1.0.0\"}")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        Log.d(TAG, "Client disconnected: $reason")
        onClientCountChanged?.invoke(connections.size)
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        if (message == null) return
        try {
            @Suppress("UNCHECKED_CAST")
            val data = gson.fromJson(message, Map::class.java) as? Map<String, Any>
            data?.let { onCommandReceived?.invoke(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse json message: $message", e)
        }
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Log.e(TAG, "WebSocket server error", ex)
    }

    override fun onStart() {
        Log.d(TAG, "WebSocket server started on port: $port")
    }

    fun broadcastMotion(dx: Byte, dy: Byte, wheel: Byte, buttons: Byte) {
        if (connections.isEmpty()) return
        val json = "{\"type\":\"mouse\",\"dx\":$dx,\"dy\":$dy,\"wheel\":$wheel,\"buttons\":$buttons}"
        broadcast(json)
    }

    fun broadcastKey(action: String) {
        if (connections.isEmpty()) return
        val json = "{\"type\":\"action\",\"action\":\"$action\"}"
        broadcast(json)
    }
}
