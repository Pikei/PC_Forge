from product.Product import Product


class Processor(Product):
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 line: str, model: str, cores: int, threads: int, socket: str, unlocked: bool, frequency: float,
                 max_frequency: float, integrated_graphics_unit: bool, tdp: int, cooler_included: bool, packaging: str):
        super().__init__(name, producer, category, description, price, producer_code)
        self.line: str = line
        self.model: str = model
        self.cores: int = cores
        self.threads: int = threads
        self.socket: str = socket
        self.unlocked: bool = unlocked
        self.frequency: float = frequency
        self.max_frequency: float = max_frequency
        self.integrated_graphics_unit: bool = integrated_graphics_unit
        self.tdp: int = tdp
        self.cooler_included: bool = cooler_included
        self.packaging: str = packaging

    def print_product_specs(self):
        super().print_common()
        print("--- SPECS ---")
        print("Line:", self.line)
        print("Model:", self.model)
        print("Number of cores:", self.cores)
        print("Number of threads:", self.threads)
        print("CPU socket:", self.socket)
        print("Unlocked:", self.unlocked)
        print("Frequency [GHz]:", self.frequency)
        print("Max frequency [GHz]:", self.max_frequency)
        print("Integrated Graphics Unit:", self.integrated_graphics_unit)
        print("TDP [W]:", self.tdp)
        print("Cooler included:", self.cooler_included)
        print("Packaging:", self.packaging)
        super().print_end()

    def get_line(self):
        return self.line

    def set_line(self, line: str):
        self.line = line

    def get_model(self):
        return self.model

    def set_model(self, model: str):
        self.model = model

    def get_cores(self):
        return self.cores

    def set_cores(self, cores: int):
        self.cores = cores

    def get_threads(self):
        return self.threads

    def set_threads(self, threads: int):
        self.threads = threads

    def get_socket(self):
        return self.socket

    def set_socket(self, socket: str):
        self.socket = socket

    def get_unlocked(self):
        return self.unlocked

    def set_unlocked(self, unlocked: bool):
        self.unlocked = unlocked

    def get_frequency(self):
        return self.frequency

    def set_frequency(self, frequency: float):
        self.frequency = frequency

    def get_max_frequency(self):
        return self.max_frequency

    def set_max_frequency(self, max_frequency: float):
        self.max_frequency = max_frequency

    def get_integrated_graphics_unit(self):
        return self.integrated_graphics_unit

    def set_integrated_graphics_unit(self, integrated_graphics_unit: bool):
        self.integrated_graphics_unit = integrated_graphics_unit

    def get_tdp(self):
        return self.tdp

    def set_tdp(self, tdp: int):
        self.tdp = tdp

    def get_cooler_included(self):
        return self.cooler_included

    def set_cooler_included(self, cooler_included: bool):
        self.cooler_included = cooler_included

    def get_packaging(self):
        return self.packaging

    def set_packaging(self, packaging: str):
        self.packaging = packaging
