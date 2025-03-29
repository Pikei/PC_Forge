from product.Product import Product


class RAM(Product):
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 line: str, memory_type: str, total_capacity: int, number_of_modules: int, frequency: int, latency: str,
                 lighting: bool):
        super().__init__(name, producer, category, description, price, producer_code)
        self.line = line
        self.memory_type = memory_type
        self.total_capacity = total_capacity
        self.number_of_modules = number_of_modules
        self.frequency = frequency
        self.latency = latency
        self.lighting = lighting

    def print_product_specs(self):
        super().print_common()
        print("--- SPECS ---")
        print("Line:", self.line)
        print("Memory type:", self.memory_type)
        if self.total_capacity > 0:
            print("Total capacity:", self.total_capacity, "GB")
        else:
            print("Total capacity:", ((-1) * self.total_capacity), "MB")
        print("Number of modules:", self.number_of_modules)
        print("Frequency:", self.frequency, "MHz")
        print("Latency:", self.latency)
        print("Lighting:", self.lighting)
        super().print_end()

    def get_line(self):
        return self.line

    def set_line(self, line: str):
        self.line = line

    def get_memory_type(self):
        return self.memory_type

    def set_memory_type(self, memory_type: str):
        self.memory_type = memory_type

    def get_total_capacity(self):
        return self.total_capacity

    def set_total_capacity(self, total_capacity: int):
        self.total_capacity = total_capacity

    def get_number_of_modules(self):
        return self.number_of_modules

    def set_number_of_modules(self, number_of_modules: int):
        self.number_of_modules = number_of_modules

    def get_frequency(self):
        return self.frequency

    def set_frequency(self, frequency: int):
        self.frequency = frequency

    def get_latency(self):
        return self.latency

    def set_latency(self, latency: str):
        self.latency = latency

    def get_lighting(self):
        return self.lighting

    def set_lighting(self, lighting: bool):
        self.lighting = lighting
