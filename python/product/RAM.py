from product.Product import Product

class RAM(Product):
    def __init__(self, name, producer, category, description, price, producer_code, capacity, memory_type, frequency,
                 voltage, delay, number_of_modules):
        super().__init__(name, producer, category, description, price, producer_code)
        self.capacity = capacity
        self.memory_type = memory_type
        self.frequency = frequency
        self.voltage = voltage
        self.delay = delay
        self.number_of_modules = number_of_modules
