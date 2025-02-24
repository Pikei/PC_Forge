from product.Product import Product


class HardDrive(Product):
    def __init__(self, name, producer, category, description, price, size, interface_type, capacity):
        super().__init__(name, producer, category, description, price)
        self.size = size
        self.interface_type = interface_type
        self.capacity = capacity


class HDD(HardDrive):

    def __init__(self, name, producer, category, description, price, size, interface_type, capacity, rotational_speed):
        super().__init__(name, producer, category, description, price, size, interface_type, capacity)
        self.rotational_speed = rotational_speed

class SSD(HardDrive):
    def __init__(self, name, producer, category, description, price, size, interface_type, capacity, read_speed, write_speed):
        super().__init__(name, producer, category, description, price, size, interface_type, capacity)
        self.read_speed = read_speed
        self.write_speed = write_speed