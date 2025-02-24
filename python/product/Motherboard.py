from product.Product import Product


class Motherboard(Product):
    def __init__(self, name, producer, category, description, price, chipset, socket, memory_standard, memory_slots,
                 memory_max_capacity, memory_type, supported_memory_frequencies, mb_standard, width, depth, wifi,
                 bluetooth, audio, m2_slots, sata_ports, usb_ports):
        super().__init__(name, producer, category, description, price)
        self.chipset = chipset
        self.socket = socket
        self.memory_standard = memory_standard
        self.memory_slots = memory_slots
        self.memory_max_capacity = memory_max_capacity
        self.memory_type = memory_type
        self.supported_memory_frequencies = supported_memory_frequencies
        self.mb_standard = mb_standard
        self.width = width
        self.depth = depth
        self.wifi = wifi
        self.bluetooth = bluetooth
        self.audio = audio
        self.m2_slots = m2_slots
        self.sata_ports = sata_ports
        self.usb_ports = usb_ports
