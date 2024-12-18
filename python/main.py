import keyboard
from keyboard import KEY_DOWN

if __name__ == "__main__":
    print("Hello World!")
    print("Press \"Q\" to exit")
    while True:
        event = keyboard.read_event()

        if event.event_type == KEY_DOWN:
            if event.name == 'q':
                print("Good bye!")
                break
            else:
                print(f"Pressed key: {event.name}")