"""
AirControl — Desktop Wi-Fi Companion Receiver
Receives mouse movement, clicks, presentations, and reel controls over local Wi-Fi.
Useful if your desktop PC lacks Bluetooth or has Bluetooth HID restricted.

Requirements:
    pip install websockets pyautogui
Run:
    python aircontrol_companion.py
"""

import asyncio
import json
import pyautogui

pyautogui.FAILSAFE = False

async def handle_connection(websocket):
    print(f"[AirControl] Phone connected: {websocket.remote_address}")
    try:
        async for message in websocket:
            try:
                data = json.loads(message)
                msg_type = data.get("type")

                if msg_type == "mouse":
                    dx = data.get("dx", 0)
                    dy = data.get("dy", 0)
                    wheel = data.get("wheel", 0)
                    buttons = data.get("buttons", 0)

                    if dx != 0 or dy != 0:
                        pyautogui.moveRel(dx, dy)

                    if wheel != 0:
                        pyautogui.scroll(wheel * 40)

                    if buttons == 1:  # Left Click
                        pyautogui.click(button="left")
                    elif buttons == 2:  # Right Click
                        pyautogui.click(button="right")

                elif msg_type == "action":
                    action = data.get("action")
                    if action == "presentation_next":
                        pyautogui.press("pagedown")
                    elif action == "presentation_prev":
                        pyautogui.press("pageup")
                    elif action == "presentation_f5":
                        pyautogui.press("f5")
                    elif action == "presentation_black":
                        pyautogui.press("b")
                    elif action == "reel_next":
                        pyautogui.press("down")
                        pyautogui.scroll(-200)
                    elif action == "reel_prev":
                        pyautogui.press("up")
                        pyautogui.scroll(200)
                    elif action == "reel_play_pause":
                        pyautogui.press("space")

            except Exception as e:
                print(f"[Error] Processing command: {e}")

    except Exception as e:
        print(f"[AirControl] Connection closed: {e}")

async def main():
    import websockets
    server = await websockets.serve(handle_connection, "0.0.0.0", 8765)
    print("=======================================================")
    print(" AirControl Desktop Receiver running on port 8765")
    print("=======================================================")
    print(" Connect your phone to this PC on the same Wi-Fi network.")
    await server.wait_closed()

if __name__ == "__main__":
    asyncio.run(main())
